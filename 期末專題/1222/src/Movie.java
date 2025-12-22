public class Movie extends Content {
    public Movie(String title, int ageRating, String region, boolean isPremiumOnly) {
        super(title, ageRating, region, isPremiumOnly);
    }

    @Override
    public void play(User user) throws Exception {
        // 這裡可以做進階檢查
        System.out.println("🎬 成功播放電影：" + this.title + " (畫質：" + user.subscription.resolution + ")");
    }

    // --- 補上遺失的 Categorizable 介面方法 ---
    @Override
    public boolean matchesCategory(String category) {
        return this.categories.contains(category);
    }

    @Override public void pause() { System.out.println("暫停電影"); }
    @Override public void resume() { System.out.println("繼續電影"); }
    @Override public void seek(int pos) { System.out.println("跳轉至 " + pos + " 秒"); }
}