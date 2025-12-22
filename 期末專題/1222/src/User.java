import java.util.ArrayList;

public class User implements WatchHistoryTrackable {
    public String name;
    public int age;
    public String region;
    public Subscription subscription;
    private ArrayList<Content> watchHistory = new ArrayList<>(); // 觀看紀錄儲存處

    public User(String name, int age, String region, Subscription subscription) {
        this.name = name;
        this.age = age;
        this.region = region;
        this.subscription = subscription;
    }

    // --- 實作 WatchHistoryTrackable ---
    @Override
    public void addToHistory(Content content) {
        if (!watchHistory.contains(content)) {
            watchHistory.add(content);
        }
    }

    @Override
    public ArrayList<Content> getWatchHistory() {
        return watchHistory;
    }

    @Override
    public void clearHistory() {
        watchHistory.clear();
    }
}