package com.example.streaming.model;

public enum AgeRating {
    G(0), PG(12), R(18);
    public final int minAge;
    AgeRating(int age) { this.minAge = age; }
}

