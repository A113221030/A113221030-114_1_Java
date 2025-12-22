public class LiveStream extends Content {
    public int viewerCount;

    public LiveStream(String title, int ageRating, String region, boolean isPremium, int viewers) {
        super(title, ageRating, region, isPremium);
        this.viewerCount = viewers;
    }

    @Override
    public void play(User user) throws Exception {
        super.play(user);
        System.out.println("🔴 正在直播：" + this.title + " (🔥 目前人數：" + this.viewerCount + ")");
    }

    @Override public void pause() { System.out.println("❌ 直播無法暫停"); }
    @Override public void resume() { System.out.println("直播畫面同步中"); }
    @Override public void seek(int pos) { System.out.println("⚠️ 直播不支援進度跳轉"); }
}