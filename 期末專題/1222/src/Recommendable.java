import java.util.ArrayList;
public interface Recommendable {
    ArrayList<Content> getSimilarContent(); // 必須指明 <Content>
    double getRecommendationScore(User user);
}