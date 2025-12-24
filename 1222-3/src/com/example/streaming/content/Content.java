package com.example.streaming.content;

import java.util.*;
import com.example.streaming.model.AgeRating;
import com.example.streaming.model.User;
import com.example.streaming.playback.PlaybackSession;

public abstract class Content {
    public String title;
    public AgeRating rating;
    public List<String> allowedRegions;
    public boolean isPremiumContent;

    public Content(String t, AgeRating r, List<String> reg, boolean p) {
        title = t; rating = r; allowedRegions = reg; isPremiumContent = p;
    }

    public PlaybackSession play(User user) throws Exception {
        if (user == null) throw new IllegalStateException("User must be logged in");
        if (user.age < rating.minAge) throw new IllegalAccessException("Age limit error: Content is " + rating);
        if (!allowedRegions.contains(user.region)) throw new IllegalAccessException("Region error: Not available in " + user.region);
        if (this.isPremiumContent && !user.isPremium) throw new IllegalAccessException("Premium upgrade required");

        int limit = user.isPremium ? 4 : 1;
        if (user.activeStreams >= limit) throw new IllegalStateException("Max simultaneous streams reached");

        long lastPos = user.getWatchHistory().getLastPosition(this.title);
        PlaybackSession session = new PlaybackSession(this.title, lastPos);

        if (!user.isPremium) session.scheduleAds();

        user.activeStreams++;
        session.start();
        return session;
    }
}
