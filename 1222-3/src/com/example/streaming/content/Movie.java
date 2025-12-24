package com.example.streaming.content;

import com.example.streaming.model.AgeRating;
import java.util.List;

public class Movie extends Content {
    public Movie(String t, AgeRating r, List<String> reg, boolean p) { super(t, r, reg, p); }
}

