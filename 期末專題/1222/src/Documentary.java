public class Documentary extends Content {
    public String topic;

    public Documentary(String title, int ageRating, String region, boolean isPremium, String topic) {
        super(title, ageRating, region, isPremium);
        this.topic = topic;
    }

    @Override
    public void play(User user) throws Exception {
        super.play(user); // 執行 Content 的檢查
        System.out.println("🎥 正在播放紀錄片：" + this.title);
        System.out.println(">> 主題標籤：#" + this.topic + " 【教育性內容】");
    }

    @Override public void pause() { System.out.println("紀錄片暫停"); }
    @Override public void resume() { System.out.println("紀錄片恢復"); }
    @Override public void seek(int pos) { System.out.println("跳轉至 " + pos + " 秒"); }
}