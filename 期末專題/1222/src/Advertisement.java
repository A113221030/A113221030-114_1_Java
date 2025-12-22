public abstract class Advertisement {
    public String adTitle;
    public int duration;

    public Advertisement(String title, int duration) {
        this.adTitle = title;
        this.duration = duration;
    }

    public abstract void display();
}

// 片頭廣告
class PreRollAd extends Advertisement {
    public PreRollAd(String title) { super(title, 15); }

    @Override
    public void display() {
        System.out.println(">> [廣告播放中] 📺 片頭廣告：" + adTitle + " (" + duration + "秒)");
        System.out.println(">> 廣告剩餘 5...4...3...2...1... [可跳過]");
    }
}