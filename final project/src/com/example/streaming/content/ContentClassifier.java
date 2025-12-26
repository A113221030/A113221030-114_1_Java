package com.example.streaming.content;

public class ContentClassifier {
    public static ContentType classify(Object o) {
        if (o == null) return ContentType.UNKNOWN;
        if (o instanceof Episode) return ContentType.EPISODE;
        if (o instanceof Movie) return ContentType.MOVIE;
        if (o instanceof Series) return ContentType.SERIES;
        if (o instanceof Documentary) return ContentType.DOCUMENTARY;
        if (o instanceof LiveStream) return ContentType.LIVESTREAM;
        if (o instanceof Content) return ContentType.UNKNOWN;
        return ContentType.UNKNOWN;
    }
}

