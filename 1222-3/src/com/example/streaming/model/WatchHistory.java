package com.example.streaming.model;

import java.util.*;

public class WatchHistory {
    private final Map<String, Long> history = new HashMap<>(); // title -> last position (seconds)
    private final Set<String> completed = new HashSet<>();    // 已完成的集數標題

    public synchronized void recordProgress(String title, long seconds) {
        if (title == null || title.isEmpty()) return;
        if (seconds <= 0) {
            history.put(title, 0L);
        } else {
            history.put(title, seconds);
        }
    }

    public synchronized long getLastPosition(String title) {
        if (title == null) return 0L;
        return history.getOrDefault(title, 0L);
    }

    public synchronized void markCompleted(String title) {
        if (title == null || title.isEmpty()) return;
        completed.add(title);
        history.put(title, 0L);
    }

    public synchronized boolean isCompleted(String title) {
        if (title == null) return false;
        return completed.contains(title);
    }

    public synchronized Map<String, Long> getHistoryMap() {
        return history;
    }

    public synchronized Set<String> getCompletedSet() {
        return completed;
    }

    public synchronized List<String> getRecentlyWatched(int limit) {
        List<Map.Entry<String, Long>> list = new ArrayList<>(history.entrySet());
        list.sort((a,b) -> Long.compare(b.getValue(), a.getValue()));
        List<String> out = new ArrayList<>();
        for (Map.Entry<String, Long> e : list) {
            if (out.size() >= limit) break;
            out.add(e.getKey());
        }
        return out;
    }

    @Override
    public String toString() {
        return "WatchHistory{history=" + history.size() + ", completed=" + completed.size() + '}';
    }
}

