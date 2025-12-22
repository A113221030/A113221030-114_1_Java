import java.util.ArrayList;

public interface WatchHistoryTrackable {
    void addToHistory(Content content);
    ArrayList<Content> getWatchHistory();
    void clearHistory();
}