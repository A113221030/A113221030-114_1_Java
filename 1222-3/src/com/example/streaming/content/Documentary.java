package com.example.streaming.content;

import java.util.*;
import com.example.streaming.model.AgeRating;

public class Documentary extends Content {
    private String subject;

    public Documentary(String title, AgeRating rating, List<String> regions, boolean isPremium, String subject) {
        super(title, rating, regions, isPremium);
        this.subject = subject;
    }

    public String getSubject() { return subject; }

    @Override
    public String toString() {
        return "紀錄片: " + title + " (主題: " + subject + ")";
    }
}

