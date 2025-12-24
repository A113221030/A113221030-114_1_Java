import java.util.*;

import com.example.streaming.content.*;
import com.example.streaming.model.*;
import com.example.streaming.recommendation.RecommendationEngine;

public class StreamingSystem {
    private static final String CORRECT_PASSWORD = "0000";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 初始化測試數據
        Movie movieR = new Movie("奧本海默", AgeRating.R, Collections.singletonList("TW"), true);
        Movie movieG = new Movie("免費短片", AgeRating.G, Arrays.asList("TW", "US"), false);

        List<List<Episode>> seriesData = Arrays.asList(
                Arrays.asList(new Episode(1,1,"序章"), new Episode(1,2,"中轉站"), new Episode(1,3,"第一季終")),
                Arrays.asList(new Episode(2,1,"新世界"), new Episode(2,2,"大結局"))
        );
        Series series = new Series("進擊的 Java", AgeRating.PG, Collections.singletonList("TW"), seriesData);

        // --- 階段 1: 登入 ---
        System.out.println(">>> [ 階段 1: 登入驗證 ]");
        System.out.print("請輸入登入密碼: ");
        if (!CORRECT_PASSWORD.equals(scanner.nextLine())) {
            System.out.println("❌ 登入失敗：User must be logged in");
            return;
        }
        System.out.println("🔓 登入成功！");

        // --- 階段 2: 年齡 ---
        System.out.println("\n>>> [ 階段 2: 年齡限制驗證 ]");
        System.out.print("請輸入您的年齡: ");
        int inputAge = Integer.parseInt(scanner.nextLine());
        User sessionUser = new User("測試員", inputAge, "UNKNOWN", false);

        if (sessionUser.age < movieR.rating.minAge) {
            System.out.println("❌ 權限錯誤: Content is rated " + movieR.rating + ", user must be " + movieR.rating.minAge + " or older");
            return;
        }

        // --- 階段 3: 地區 (手動驗證) ---
        System.out.println("\n>>> [ 階段 3: 地區限制驗證 ]");
        System.out.print("請輸入您所在的地區: ");
        String inputRegion = scanner.nextLine().trim();
        if (!inputRegion.equalsIgnoreCase("Taiwan")) {
            System.out.println("❌ 地區限制錯誤: Content is not available in your region");
            return;
        }
        sessionUser.region = "TW";
        System.out.println("✅ 地區驗證成功！");

        // --- 階段 4: 廣告時間點與播放測試 ---
        runStage("階段 4: 廣告時間點測試 (從第 2 分鐘開始觀看免費內容)", () -> {
            // 設為免費方案以示範廣告與畫質限制
            sessionUser.setSubscription(new FreeTier());
            printSubscription(sessionUser);
            sessionUser.activeStreams = 0;
            sessionUser.getWatchHistory().recordProgress("免費短片", 125L); // 2 分 5 秒
            movieG.play(sessionUser);

            // 示範使用 WatchHistory 的輔助方法以避免未使用警告
            System.out.println("最近觀看: " + sessionUser.getWatchHistory().getRecentlyWatched(5));
            System.out.println("觀看紀錄 map: " + sessionUser.getWatchHistory().getHistoryMap());
        });

        // --- 階段 5: 方案與裝置限制測試 ---
        runStage("階段 5: 方案限制驗證 (嘗試播放 Premium 內容)", () -> {
            sessionUser.activeStreams = 0;
            System.out.println("當前方案: " + sessionUser.getSubscription().getName());
            try {
                // 嘗試用目前方案播放 Premium 內容，若失敗示範升級流程
                movieR.play(sessionUser);
            } catch (Exception e) {
                System.out.println("❌ 播放失敗（預期）: " + e.getMessage());
                System.out.println("升級至 PremiumPlan 並重試播放...");
                sessionUser.setSubscription(new PremiumPlan());
                printSubscription(sessionUser);
                sessionUser.activeStreams = 0;
                movieR.play(sessionUser);
            }
        });

        // --- 階段 6: 影集跳轉與權限測試 ---
        runStage("階段 6: 影集跳轉與權限驗證", () -> {
            // 使用訂閱系統切換為 PremiumPlan
            sessionUser.setSubscription(new PremiumPlan());
            printSubscription(sessionUser);
            sessionUser.activeStreams = 0;
            sessionUser.getWatchHistory().markCompleted("中轉站");
            sessionUser.getWatchHistory().markCompleted("第一季終");

            series.play(sessionUser);

            Episode current = seriesData.get(0).get(0); // S1E1
            System.out.println("當前播放完畢: " + current);
            Episode next = series.getNextEpisode(current, sessionUser);
            System.out.println(">>> 系統自動撥放下一集 -> " + next);
        });



        // --- 階段 7: 自動分類示範 ---
        runStage("階段 7: Content 自動分類示範", () -> {
            java.util.List<Object> items = Arrays.asList(
                    movieR,
                    series,
                    new Episode(1,1,"序章","進擊的 Java"),
                    new Documentary("地球紀事", AgeRating.G, Collections.singletonList("TW"), false, "地球與生態"),
                    new LiveStream("新年晚會", AgeRating.G, Collections.singletonList("TW"), false, new java.util.Date(), true)
            );

            System.out.println("--- 自動分類結果 ---");
            for (Object o : items) {
                String name;
                if (o instanceof Content) name = ((Content) o).title;
                else name = o.toString();
                System.out.println(name + " -> 類別: " + ContentClassifier.classify(o));
            }
            System.out.println("--------------------");
        });

        // --- 階段 8: 推薦系統示範 ---
        runStage("階段 8: 推薦系統示範", () -> {
            List<Content> catalog = new ArrayList<>();
            // 視為範例目錄（可擴充）
            catalog.add(movieR);
            catalog.add(movieG);
            catalog.add(series);
            catalog.add(new Documentary("地球紀事", AgeRating.G, Collections.singletonList("TW"), false, "地球與生態"));
            catalog.add(new LiveStream("新年晚會", AgeRating.G, Collections.singletonList("TW"), false, new Date(), true));

            // 原始推薦
            List<Content> recs = RecommendationEngine.recommendForUser(sessionUser, catalog, 5);

            System.out.println("--- 推薦結果 (top " + recs.size() + ") ---");
            for (Content c : recs) {
                System.out.println("- " + c.title + " -> 類別: " + ContentClassifier.classify(c));
            }
            System.out.println("--------------------");

            // 示範：直接用 movieG 作為 current 傳入（移除冗餘變數）
            List<Content> recsWithCurrent = RecommendationEngine.recommendForUser(sessionUser, catalog, 5, movieG);

            System.out.println("--- 推薦結果（優先: " + (movieG.title != null ? movieG.title : "current") + "）---");
            for (Content c : recsWithCurrent) {
                System.out.println("- " + c.title + " -> 類別: " + ContentClassifier.classify(c));
            }
            System.out.println("--------------------");

            // 示範使用 recommendByType
            List<Content> movieOnly = RecommendationEngine.recommendByType(sessionUser, catalog, ContentType.MOVIE, 3);
            System.out.println("--- Movie 類型推薦 (top " + movieOnly.size() + ") ---");
            for (Content c : movieOnly) System.out.println("- " + c.title);
            System.out.println("--------------------");
        });

    }

    private static void runStage(String label, TestAction action) {
        System.out.println("\n>>> [ " + label + " ]");
        try {
            action.run();
        } catch (Exception e) {
            System.out.println("❌ 攔截預期錯誤: " + e.getMessage());
        }
    }

    private static void printSubscription(User u) {
        Subscription s = u.getSubscription();
        System.out.println("--- 訂閱方案資訊 ---");
        System.out.println("方案: " + s.getName());
        System.out.println("畫質: " + s.getQuality());
        System.out.println("最多裝置: " + s.getMaxDevices());
        System.out.println("有廣告: " + s.hasAds());
        System.out.println("isPremium 標記: " + u.isPremium);
        System.out.println("--------------------");
    }

    interface TestAction { void run() throws Exception; }
}