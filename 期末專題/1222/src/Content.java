import java.util.ArrayList;

// 實作所有規格書要求的介面
public abstract class Content implements Playable, Categorizable, Recommendable, Restrictable {
    public String title;
    public int ageRating;
    public String region;
    public boolean isPremiumOnly;
    protected ArrayList<String> categories = new ArrayList<>(); // 存放分類標籤

    public Content(String title, int ageRating, String region, boolean isPremiumOnly) {
        this.title = title;
        this.ageRating = ageRating;
        this.region = region;
        this.isPremiumOnly = isPremiumOnly;
    }

    // --- 實作 Playable 介面 (由子類別決定具體播放內容，但在此定義基礎權限) ---
    @Override
    public void play(User user) throws Exception {
        if (!isAccessibleBy(user)) {
            throw new Exception("權限不足：此內容分級為 " + ageRating + "+");
        }
    }

    // --- 實作 Restrictable 介面 (年齡與地區檢查) ---
    @Override
    public boolean isAccessibleBy(User user) {
        // 檢查年齡
        if (user.age < this.ageRating) return false;
        // 檢查地區 (簡單示範：區域必須相符)
        if (!user.region.equals(this.region)) return false;
        // 檢查 Premium 權限
        if (this.isPremiumOnly && !(user.subscription instanceof PremiumPlan)) {
            System.out.println("⚠️ 此內容僅限豪華方案(Premium)觀看");
            return false;
        }
        return true;
    }

    @Override
    public int getAgeRating() {
        return this.ageRating;
    }

    // --- 實作 Categorizable 介面 (修正你遇到的 matchesCategory 錯誤) ---
    @Override
    public void addCategory(String category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    @Override
    public ArrayList<String> getCategories() {
        return this.categories;
    }

    @Override
    public boolean matchesCategory(String category) {
        return this.categories.contains(category);
    }

    // --- 實作 Recommendable 介面 (推薦系統邏輯) ---
    @Override
    public double getRecommendationScore(User user) {
        double score = 5.0; // 基礎分
        ArrayList<Content> history = user.getWatchHistory();

        // 根據觀看紀錄中的分類重複次數來加分
        for (Content watched : history) {
            for (String cat : watched.getCategories()) {
                if (this.matchesCategory(cat)) {
                    score += 1.5; // 分類契合，加分
                }
            }
        }
        return Math.min(score, 10.0); // 最高 10 分
    }
    // 在 Content 類別內補上這個方法
    @Override
    public ArrayList<Content> getSimilarContent(List<Content> allContent) {
        ArrayList<Content> similar = new ArrayList<>();
        for (Content other : allContent) {
            // 排除掉自己
            if (other == this) continue;

            // 檢查是否有共同的分類標籤
            for (String cat : this.categories) {
                if (other.matchesCategory(cat)) {
                    similar.add(other);
                    break; // 只要有一個分類中就加入，跳到下一個物件
                }
            }
        }
        return similar;
    }
}