import java.util.*;

import com.example.streaming.content.*;
import com.example.streaming.model.*;
import com.example.streaming.recommendation.RecommendationEngine;

/**
 * StreamingSystem 主程式（示範用）
 * - 此程式模擬一個簡化的影音串流系統測試流程，包含：
 *   1. 登入驗證 (password check)
 *   2. 年齡限制驗證 (age gating)
 *   3. 地區限制驗證 (geo-restriction)
 *   4. 廣告/播放示範 (根據方案決定是否插廣告，及畫質限制)
 *   5. 訂閱方案與裝置限制測試 (示範升級流程)
 *   6. 影集自動跳集與權限檢查
 *   7. Content 類別自動分類示範
 *   8. 推薦系統示範
 *
 * 注意：此為教學/示範程式，許多邏輯皆為簡化實作以利示範。
 */
public class StreamingSystem {
    // 範例用靜態密碼：實際系統不可明碼寫在程式中
    private static final String CORRECT_PASSWORD = "0000";
    // 單一 Scanner 實例供整個示範程式使用（簡化互動輸入）
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 初始化測試數據：建立不同分級與可用地區的 Content 物件
        // 這裡建立一個 R 級電影 (需要年齡通過)、一個免費 G 級短片 (示範廣告)
        Movie movieR = new Movie("奧本海默", AgeRating.R, Collections.singletonList("TW"), true);
        Movie movieG = new Movie("免費短片", AgeRating.G, Arrays.asList("TW", "US"), false);

        List<List<Episode>> seriesData = Arrays.asList(
                Arrays.asList(new Episode(1,1,"序章"), new Episode(1,2,"中轉站"), new Episode(1,3,"第一季終")),
                Arrays.asList(new Episode(2,1,"新世界"), new Episode(2,2,"大結局"))
        );
        Series series = new Series("進擊的 Java", AgeRating.PG, Collections.singletonList("TW"), seriesData);

        // --- 階段 1: 登入 ---
        // 說明：以最簡單的密碼比對示範登入流程；若登入失敗則直接結束程式
        System.out.println(">>> [ 階段 1: 登入驗證 ]");
        System.out.print("請輸入登入密碼: ");
        if (!CORRECT_PASSWORD.equals(scanner.nextLine())) {
            System.out.println("❌ 登入失敗：User must be logged in");
            return;
        }
        System.out.println("🔓 登入成功！");

        // --- 階段 2: 年齡 ---
        // 說明：讀取使用者年齡並與高分級內容比較，若不符合則拒絕存取
        System.out.println("\n>>> [ 階段 2: 年齡限制驗證 ]");
        System.out.print("請輸入您的年齡: ");
        int inputAge = Integer.parseInt(scanner.nextLine());
        User sessionUser = new User("測試員", inputAge, "UNKNOWN", false);

        if (sessionUser.age < movieR.rating.minAge) {
            System.out.println("❌ 權限錯誤: Content is rated " + movieR.rating + ", user must be " + movieR.rating.minAge + " or older");
            return;
        }

        // --- 階段 3: 地區 (手動驗證) ---
        // 說明：示範簡單的地區檢查（範例只接受 Taiwan），通過後將使用者 region 設為 TW
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
        // 說明：將使用者設為 FreeTier，示範插廣告、畫質限制與 WatchHistory 的使用
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
        // 說明：示範當使用者方案無法播放特定內容時，拋出例外並演示升級流程
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
        // 說明：示範影集播放與自動跳下一集的邏輯；同時示範如何使用 WatchHistory 標記已完成集數
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
        // 說明：使用 ContentClassifier 依型別回傳分類字串，方便在 UI 或日誌上顯示內容種類
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
        // 說明：建立一個簡單目錄 (catalog) 並呼叫 RecommendationEngine，示範透過使用者屬性與當前內容來產生推薦清單
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

    /**
     * runStage 幫助方法：統一列印階段標題並封裝例外處理。
     * 每個測試階段傳入一個 TestAction 實作；若內部丟出例外會被捕捉並顯示錯誤訊息，
     * 使示範流程能繼續執行其他階段而不會整個程式崩潰。
     */
    private static void runStage(String label, TestAction action) {
        System.out.println("\n>>> [ " + label + " ]");
        try {
            action.run();
        } catch (Exception e) {
            // 捕捉所有例外並以友善訊息顯示（方便示範流程繼續）
            System.out.println("❌ 攔截預期錯誤: " + e.getMessage());
        }
    }

    /**
     * printSubscription 輔助方法：列印目前使用者訂閱方案的相關資訊。
     * 可快速查看方案名稱、畫質、最多同時裝置與是否有廣告等屬性，方便在示範過程中驗證行為差異。
     */
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

    // Functional interface：每個測試階段可實作 run，並可丟出 Exception
    interface TestAction { void run() throws Exception; }
}