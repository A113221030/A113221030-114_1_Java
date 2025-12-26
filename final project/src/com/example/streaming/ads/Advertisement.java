package com.example.streaming.ads;

public abstract class Advertisement {
    String content;
    public int timestampMinute; // 廣告顯示的時間點（分鐘）

    public Advertisement(String content, int minute) {
        this.content = content;
        this.timestampMinute = minute;
    }
    public abstract void display();
}

