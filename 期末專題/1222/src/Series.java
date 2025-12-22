import java.util.List;
public class Series extends Content {
    List<Episode> episodes;

    public Series(String title, int minAge, String region, boolean isPremium, List<Episode> episodes) {
        super(title, minAge, region, isPremium);
        this.episodes = episodes;
    }

    @Override
    public void play(User user) throws Exception {
        super.play(user); // 執行 Content 的檢查
        System.out.println("✅ 正在播放影集：" + this.title);
        if (episodes != null && !episodes.isEmpty()) {
            System.out.println(">> 即將播放第 1 集：" + episodes.get(0).title);
        }
    }

    @Override public void pause() { System.out.println("影集暫停"); }
    @Override public void resume() { System.out.println("影集恢復"); }
    @Override public void seek(int pos) { System.out.println("影集跳轉至 " + pos + " 秒"); }
}