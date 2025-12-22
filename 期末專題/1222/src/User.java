import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public int age;
    public String currentRegion;
    public Subscription subscription;
    public boolean isLoggedIn = true;
    public int currentActiveDevices = 0;
    // 紀錄看過的影片 ID
    public List<String> watchHistory = new ArrayList<>();

    public User(String name, int age, String region, Subscription sub) {
        this.name = name;
        this.age = age;
        this.currentRegion = region;
        this.subscription = sub;
    }
}