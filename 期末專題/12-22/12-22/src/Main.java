import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Content> library = new ArrayList<>();

        Movie movie = new Movie("奧本海默", 15, true);
        movie.addCategory("歷史");
        library.add(movie);

        LiveStream live = new LiveStream("跨年晚會", 0);
        live.addCategory("娛樂");
        library.add(live);

        User user = new User("小明", 20, "Taiwan", new PremiumPlan());

        System.out.println("=== 串流平台正式啟動 ===");
        try {
            movie.play(user);
            user.addToHistory(movie);

            System.out.println("\n--- 推薦系統 ---");
            ArrayList<Content> recs = movie.getSimilarContent(library);
            for (Content c : recs) System.out.println("推薦您觀看: " + c.title);

        } catch (Exception e) {
            System.out.println("無法播放: " + e.getMessage());
        }
    }
}a