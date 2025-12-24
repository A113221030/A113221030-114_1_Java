package com.example.streaming.content;

import java.util.*;
import com.example.streaming.model.User;

public class Series extends Content {
    private List<List<Episode>> seasons;
    public Series(String t, com.example.streaming.model.AgeRating r, List<String> reg, List<List<Episode>> data) {
        super(t, r, reg, true);
        this.seasons = data;
    }

    public Episode getNextEpisode(Episode current, User user) {
        Episode nextCandidate = calculateRawNext(current);
        while (nextCandidate != null && user.getWatchHistory().isCompleted(nextCandidate.title)) {
            System.out.println("   (跳過已看完集數: " + nextCandidate.title + ")");
            nextCandidate = calculateRawNext(nextCandidate);
        }
        return nextCandidate;
    }

    private Episode calculateRawNext(Episode current) {
        int sIdx = current.sNum - 1;
        int eIdx = current.eNum - 1;
        if (eIdx + 1 < seasons.get(sIdx).size()) return seasons.get(sIdx).get(eIdx + 1);
        else if (sIdx + 1 < seasons.size()) return seasons.get(sIdx + 1).get(0);
        return null;
    }
}
