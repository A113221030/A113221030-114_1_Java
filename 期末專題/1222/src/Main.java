import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. 建立使用者與內容
        User user = new User("小明", 20, "Taiwan", new PremiumPlan());

        Series op = new Series("航海王", 12, "Taiwan", false, new ArrayList<>());
        op.addCategory("冒險");
        op.addCategory("熱血");

        Documentary doc = new Documentary("我們的星球", 0, "Taiwan", false, "自然");
        doc.addCategory("自然");

        // 2. 模擬觀看過程並記錄
        System.out.println("====== 📺 觀看流程啟動 ======");

        // 觀看航海王
        playAndRecord(user, op);

        // 3. 展示觀看紀錄
        System.out.println("\n--- 📜 您的觀看紀錄 ---");
        for (Content c : user.getWatchHistory()) {
            System.out.println("已觀看：" + c.title);
        }

        // 4. 推薦系統演示
        System.out.println("\n--- 💡 系統推薦分數 ---");
        System.out.println(doc.title + " 的推薦指數：" + doc.getRecommendationScore(user) + " / 10.0");
    }

    public static void playAndRecord(User user, Content content) {
        try {
            if (content.isAccessibleBy(user)) {
                content.play(user);
                user.addToHistory(content); // 自動加入紀錄
                System.out.println(">> [系統] 已將 " + content.title + " 加入您的觀看紀錄");
            }
        } catch (Exception e) {
            System.out.println("播放失敗：" + e.getMessage());
        }
    }
}