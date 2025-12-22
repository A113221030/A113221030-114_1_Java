import java.util.ArrayList;
public interface Categorizable {
    void addCategory(String category);
    ArrayList<String> getCategories();
    boolean matchesCategory(String category);
}