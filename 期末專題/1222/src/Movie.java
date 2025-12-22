import java.util.ArrayList;
import java.util.List;

public class Movie extends Content {
    public Movie(String title, int ageRating, String region, boolean isPremiumOnly) {
        super(title, ageRating, region, isPremiumOnly);
    }

    @Override
    public void play(User user) throws Exception {
        super.play(user);
        System.out.println("🎥 正在播放電影：" + this.title);
    }

    @Override public void pause() { System.out.println("電影暫停"); }
    @Override public void resume() { System.out.println("電影恢復"); }
    @Override public void seek(int pos) { System.out.println("電影跳轉至 " + pos + " 秒"); }
}