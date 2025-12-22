public abstract class Advertisement {
    public String adTitle;
    public Advertisement(String title) { this.adTitle = title; }
    public abstract void display();
}

class PreRollAd extends Advertisement {
    public PreRollAd(String title) { super(title); }
    @Override public void display() { System.out.println(">> [廣告] 播放片頭廣告：" + adTitle + " (15秒)"); }
}