public abstract class Content {
    public String title;
    public int ageRating;
    public String region;
    public boolean isPremiumOnly;

    public Content(String title, int ageRating, String region, boolean isPremiumOnly) {
        this.title = title;
        this.ageRating = ageRating;
        this.region = region;
        this.isPremiumOnly = isPremiumOnly;
    }

    public void play(User user) throws Exception {
        if (!user.isLoggedIn) throw new IllegalStateException("User must be logged in");
        if (user.age < this.ageRating) throw new IllegalAccessException("年齡不符");
        if (!user.currentRegion.equals(this.region)) throw new IllegalAccessException("地區限制");
        if (this.isPremiumOnly && user.subscription instanceof FreeTier) throw new IllegalAccessException("此為豪華內容");
        if (user.currentActiveDevices >= user.subscription.maxDevices) throw new IllegalStateException("超過裝置限制");

        System.out.println("✅ 成功播放：" + this.title + " (畫質：" + user.subscription.resolution + ")");
    }
}