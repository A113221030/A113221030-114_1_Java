public class Main {
    public static void main(String[] args) {
        // 1. 創立方案 (這裡要確保這些變數在 try 外面定義，後面才讀得到)
        Subscription free = new FreeTier();
        Subscription vip = new PremiumPlan();

        // 2. 創立使用者
        User user = new User("小明", 15, "Taiwan", free);

        // 3. 創立內容
        Movie movie = new Movie("恐怖電影", 18, "Taiwan", true);

        System.out.println("--- 第一次嘗試播放 ---");
        try {
            movie.play(user); // 這裡會因為小明才 15 歲而失敗
        } catch (Exception e) {
            System.out.println("❌ 播放失敗：" + e.getMessage());
        }

        System.out.println("\n--- 升級成 VIP 並長大後再試 ---");
        user.age = 20;            // 修改年齡
        user.subscription = vip;   // 修改方案

        try {
            movie.play(user); // 這次就會成功
        } catch (Exception e) {
            System.out.println("❌ 播放失敗：" + e.getMessage());
        }
    }
}
