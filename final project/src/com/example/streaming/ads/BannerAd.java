package com.example.streaming.ads;

public class BannerAd extends Advertisement {
    public BannerAd(String content, int minute) { super(content, minute); }
    @Override
    public void display() {
        System.out.println("🖼️ [橫幅廣告] (" + timestampMinute + ":00) 螢幕下方顯示: " + content);
    }
}

