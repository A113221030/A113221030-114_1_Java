package com.example.streaming.content;

import java.util.*;
import com.example.streaming.model.AgeRating;

public class LiveStream extends Content {
    private Date startTime;
    private boolean isLive;

    public LiveStream(String title, AgeRating rating, List<String> regions, boolean isPremium, Date startTime, boolean isLive) {
        super(title, rating, regions, isPremium);
        this.startTime = startTime;
        this.isLive = isLive;
    }

    public Date getStartTime() { return startTime; }
    public boolean isLive() { return isLive; }

    @Override
    public String toString() {
        return "直播: " + title + " (" + (isLive ? "正在直播" : "預定直播") + ")";
    }
}

