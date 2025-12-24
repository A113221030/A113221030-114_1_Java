package com.example.streaming.model;

public abstract class Subscription {
    public enum Quality { SD, HD, FHD, UHD }

    protected final String name;
    protected final int maxDevices;
    protected final Quality quality;
    protected final boolean hasAds;

    protected Subscription(String name, int maxDevices, Quality quality, boolean hasAds) {
        this.name = name;
        this.maxDevices = maxDevices;
        this.quality = quality;
        this.hasAds = hasAds;
    }

    public String getName() { return name; }
    public int getMaxDevices() { return maxDevices; }
    public Quality getQuality() { return quality; }
    public boolean hasAds() { return hasAds; }

    public boolean isPremium() {
        // 定義 Premium 為能觀看 UHD 或不含廣告的方案
        return !hasAds && (quality == Quality.UHD || maxDevices >= 4);
    }
}

