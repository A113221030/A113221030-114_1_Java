public class Documentary extends Content {
    public String topic;

    public Documentary(String title, int ageRating, String region, boolean isPremium, String topic) {
        super(title, ageRating, region, isPremium);
        this.topic = topic;
    }

    @Override
    public void play(User user) throws Exception {
        super.play(user); // 權限檢查
        System.out.println("🎥 正在播放紀錄片：" + this.title + " (主題：" + this.topic + ")");
    }

    @Override public void pause() { System.out.println("紀錄片已暫停"); }
    @Override public void resume() { System.out.println("紀錄片已恢復"); }
    @Override public void seek(int pos) { System.out.println("紀錄片跳轉至 " + pos + " 秒"); }
}