package com.example.streaming.content;

public class Episode {
    public int sNum, eNum;
    public String title;
    // 可選：所屬影集名稱
    public String seriesTitle;

    public Episode(int s, int e, String t) { this(s, e, t, null); }
    public Episode(int s, int e, String t, String seriesTitle) { this.sNum = s; this.eNum = e; this.title = t; this.seriesTitle = seriesTitle; }

    public String getSeriesTitle() { return this.seriesTitle; }

    @Override
    public String toString() { return (seriesTitle != null ? (seriesTitle + " - ") : "") + "第 " + sNum + " 季 第 " + eNum + " 集: " + title; }
}
