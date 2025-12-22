import java.util.List;

class Series extends Content {
    List<Episode> episodes;

    public Series(String title, int minAge, String region, boolean isPremium, List<Episode> episodes) {
        super(title, minAge, region, isPremium);
        this.episodes = episodes;
    }

    public Episode getNextEpisode(Episode current) {
        int currentIndex = episodes.indexOf(current);

        // 如果還有下一集
        if (currentIndex < episodes.size() - 1) {
            return episodes.get(currentIndex + 1);
        }

        // 如果是最後一集了
        System.out.println("這已經是本季最後一集囉！");
        return null;
    }
}

class Episode {
    String title;
    int episodeNumber;

    public Episode(String title, int number) {
        this.title = title;
        this.episodeNumber = number;
    }
}