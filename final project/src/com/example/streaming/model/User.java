package com.example.streaming.model;

import java.util.*;

public class User {
    public String name;
    public int age;
    public String region;
    public boolean isPremium;
    public Subscription subscription;
    public int activeStreams = 0;
    // 使用 WatchHistory 封裝觀看進度與已完成集數
    public WatchHistory watchHistory = new WatchHistory();

    public User(String n, int a, String r, boolean p) {
        name = n; age = a; region = r;
        // 根據傳入的 boolean 決定預設方案：true -> PremiumPlan, false -> FreeTier
        if (p) {
            this.subscription = new PremiumPlan();
        } else {
            this.subscription = new FreeTier();
        }
        this.isPremium = this.subscription.isPremium();
    }

    public void setSubscription(Subscription newSub) {
        if (newSub == null) return;
        this.subscription = newSub;
        this.isPremium = newSub.isPremium();
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public WatchHistory getWatchHistory() { return this.watchHistory; }
}
