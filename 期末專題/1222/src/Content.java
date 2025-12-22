import java.util.ArrayList;

public abstract class Content implements Playable, Categorizable, Recommendable, Restrictable {
    public String title;
    public int ageRating;
    public String region;
    public boolean isPremiumOnly;
    public ArrayList<String> categories = new ArrayList<>();

    public Content(String title, int ageRating, String region, boolean isPremiumOnly) {
        this.title = title;
        this.ageRating = ageRating;
        this.region = region;
        this.isPremiumOnly = isPremiumOnly;
    }

    @Override public int getAgeRating() { return ageRating; }
    @Override public boolean isAccessibleBy(User user) { return user.age >= this.ageRating; }
    @Override public ArrayList<String> getRegionRestrictions() {
        ArrayList<String> r = new ArrayList<>(); r.add(region); return r;
    }

    // 核心播放檢查 (中間功能)
    @Override
    public void play(User user) throws Exception {
        if (!isAccessibleBy(user)) throw new Exception("年齡不符，禁止播放");
        if (this.isPremiumOnly && user.subscription instanceof FreeTier) throw new Exception("豪華內容限 VIP 觀看");
        // 成功後不印訊息，交由子類印出
    }

    @Override public void addCategory(String c) { categories.add(c); }
    @Override public ArrayList<String> getCategories() { return categories; }
    @Override public boolean matchesCategory(String c) { return categories.contains(c); }
    @Override public ArrayList<Content> getSimilarContent() { return new ArrayList<>(); }
    @Override public double getRecommendationScore(User user) { return 8.0; }
}