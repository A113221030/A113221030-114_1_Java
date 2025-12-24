package com.example.streaming.recommendation;

import java.util.*;
import com.example.streaming.content.*;
import com.example.streaming.model.*;

/**
 * 簡單推薦引擎（啟發式）：
 * - 過濾使用者不可見內容（年齡、地區）
 * - 對非 premium 使用者降低 premium content 分數
 * - 根據使用者歷史（繼續觀看）與已看集數加分
 * - 支援傳入 current（目前播放或優先項目），可將其提升至優先位置
 */
public class RecommendationEngine {

    // 主要推薦方法：回傳 top N
    public static List<Content> recommendForUser(User user, List<Content> catalog, int limit) {
        if (user == null || catalog == null || limit <= 0) return Collections.emptyList();

        Map<Content, Integer> scores = new HashMap<>();

        for (Content c : catalog) {
            // 基本 null guard
            if (c == null) continue;

            // 可見性檢查
            if (user.age < c.rating.minAge) continue;
            if (c.allowedRegions == null || !c.allowedRegions.contains(user.region)) continue;

            int score = 0;

            // premium 對非 premium 扣分
            if (c.isPremiumContent && !user.isPremium) score -= 25;

            // 歷史繼續觀看加分（使用 WatchHistory）
            long lastPos = user.getWatchHistory().getLastPosition(c.title);
            if (lastPos > 0) score += 40;

            // 若是影集且使用者已看過該影集的其他集數，加分（使用 WatchHistory 的 completed set）
            if (c instanceof Series) {
                Series s = (Series) c;
                boolean related = false;
                Set<String> completed = user.getWatchHistory().getCompletedSet();
                if (completed != null && s.title != null) {
                    for (String seen : completed) {
                        if (seen != null && !seen.isEmpty() && seen.contains(s.title)) { related = true; break; }
                    }
                }
                if (related) score += 30;
            }

            // 標題關鍵字簡易加權（可擴充）
            if (c.title != null) {
                String t = c.title.toLowerCase();
                if (t.contains("新") || t.contains("202")) score += 5;
                score += Math.min(8, t.length() % 11);
            }

            scores.put(c, score);
        }

        // 排序並取 top N
        List<Map.Entry<Content, Integer>> list = new ArrayList<>(scores.entrySet());
        list.sort((a,b) -> Integer.compare(b.getValue(), a.getValue()));

        List<Content> out = new ArrayList<>();
        for (Map.Entry<Content,Integer> e : list) {
            if (out.size() >= limit) break;
            out.add(e.getKey());
        }
        return out;
    }

    // 重載：接受一個 current，將 current 優先放入推薦清單（若可見）
    public static List<Content> recommendForUser(User user, List<Content> catalog, int limit, Content current) {
        if (user == null || catalog == null || limit <= 0) return Collections.emptyList();
        List<Content> base = recommendForUser(user, catalog, limit);
        if (current == null) return base;

        // 檢查 current 的可見性
        if (user.age < current.rating.minAge) return base;
        if (current.allowedRegions == null || !current.allowedRegions.contains(user.region)) return base;

        // 準備結果，將 current 放最前面（若可見）並補齊
        String curTitle = current.title != null ? current.title : "";
        List<Content> out = new ArrayList<>();
        out.add(current);

        for (Content c : base) {
            if (out.size() >= limit) break;
            if (c.title != null && c.title.equals(curTitle)) continue;
            out.add(c);
        }

        // 若不足，從 catalog 補齊（可見性檢查）
        if (out.size() < limit) {
            for (Content c : catalog) {
                if (out.size() >= limit) break;
                if (c == null) continue;
                if (c.title != null && c.title.equals(curTitle)) continue;
                if (base.contains(c)) continue;
                if (user.age < c.rating.minAge) continue;
                if (c.allowedRegions == null || !c.allowedRegions.contains(user.region)) continue;
                out.add(c);
            }
        }

        return out;
    }

    // 額外輔助：以 ContentType 過濾 catalog 再推薦（可選）
    public static List<Content> recommendByType(User user, List<Content> catalog, ContentType type, int limit) {
        if (catalog == null) return Collections.emptyList();
        List<Content> filtered = new ArrayList<>();
        for (Content c : catalog) {
            if (c == null) continue;
            ContentType t = ContentClassifier.classify(c);
            if (t == type) filtered.add(c);
        }
        return recommendForUser(user, filtered, limit);
    }
}
