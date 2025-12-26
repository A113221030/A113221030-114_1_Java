package com.example.streaming.playback;

import java.util.*;
import com.example.streaming.ads.*;

public class PlaybackSession {
    public String title;
    public long startPosition;
    public boolean adsScheduled = false;
    public List<Advertisement> adSchedule = new ArrayList<>();

    public PlaybackSession(String title, long pos) {
        this.title = title;
        this.startPosition = pos;
    }

    public void scheduleAds() {
        this.adsScheduled = true;
        // 預設廣告排程
        adSchedule.add(new PreRollAd("2025 全新影集預告"));
        adSchedule.add(new com.example.streaming.ads.BannerAd("訂閱 Premium 享無廣告體驗", 1));
        adSchedule.add(new MidRollAd("品牌贊助中斷廣告", 5));
    }

    public void start() {
        System.out.println("✅ 播放啟動成功: [" + title + "]");
        System.out.println("   - 從上次位置繼續: " + startPosition + " 秒");

        if (adsScheduled) {
            System.out.println("   - 播放模式: 免費含廣告 (系統已根據進度過濾廣告)");
            long startMinute = startPosition / 60;
            for (Advertisement ad : adSchedule) {
                // 只播放當前進度之後的廣告
                if (ad.timestampMinute >= startMinute) {
                    ad.display();
                }
            }
        } else {
            System.out.println("   - 播放模式: ✨ Premium 純淨模式");
        }
    }
}

