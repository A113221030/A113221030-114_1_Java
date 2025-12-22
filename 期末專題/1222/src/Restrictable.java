import java.util.ArrayList;
public interface Restrictable {
    int getAgeRating();
    ArrayList<String> getRegionRestrictions();
    boolean isAccessibleBy(User user);
}