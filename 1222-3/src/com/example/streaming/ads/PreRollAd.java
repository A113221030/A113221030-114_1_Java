package com.example.streaming.ads;

public class PreRollAd extends Advertisement {
    public PreRollAd(String content) { super(content, 0); }
    @Override
    public void display() {
        System.out.println("🎬 [片頭廣告] (00:00) 正在播放: " + content + " - 廣告結束後開始正片");
    }
}

