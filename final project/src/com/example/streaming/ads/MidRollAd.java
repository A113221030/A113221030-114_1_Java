package com.example.streaming.ads;

public class MidRollAd extends Advertisement {
    public MidRollAd(String content, int minute) { super(content, minute); }
    @Override
    public void display() {
        System.out.println("⏳ [中插廣告] (" + timestampMinute + ":00) 節目插播: " + content);
    }
}

