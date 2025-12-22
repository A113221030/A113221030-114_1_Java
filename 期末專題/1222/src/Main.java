import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. 建立使用者 (Premium 方案可以看所有內容且無廣告)
        User user = new User("小明", 20, "Taiwan", new PremiumPlan());

        // --- 2. 讓航海王回來 (影集) ---
        List<Episode> opEps = new ArrayList<>();
        opEps.add(new Episode("第一集：起源", 45));
        Series onePiece = new Series("航海王", 12, "Taiwan", false, opEps);

        // --- 3. 建立紀錄片 ---
        Documentary doc = new Documentary("我們的星球", 0, "Taiwan", false, "自然科學");

        // --- 4. 建立直播內容 ---
        LiveStream live = new LiveStream("2026 跨年演唱會", 12, "Taiwan", true, 50000);

        // --- 開始執行播放流程 ---
        System.out.println("====== 串流平台測試開始 ======");

        // 測試 1: 航海王
        processPlay(user, onePiece);

        // 測試 2: 紀錄片
        processPlay(user, doc);

        // 測試 3: 直播
        processPlay(user, live);
    }

    // 建立一個統一的播放處理方法，減少重複程式碼
    public static void processPlay(User user, Content content) {
        System.out.println("\n--- 正在載入: " + content.title + " ---");
        try {
            if (content.isAccessibleBy(user)) {
                // 檢查廣告 (如果方案有廣告才播)
                if (user.subscription.hasAds) {
                    new PreRollAd("特價商品廣告").display();
                }
                content.play(user);
            } else {
                System.out.println("❌ 無法播放：未達分級年齡 " + content.ageRating + "+");
            }
        } catch (Exception e) {
            System.out.println("❌ 系統異常：" + e.getMessage());
        }
    }
}