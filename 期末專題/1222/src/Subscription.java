// 這是訂閱方案的父類別
public abstract class Subscription {
    public String planName;
    public String resolution;
    public boolean hasAds;
    public int deviceLimit;

    public Subscription(String name, String res, boolean ads, int limit) {
        this.planName = name;
        this.resolution = res;
        this.hasAds = ads;
        this.deviceLimit = limit;
    }
}

// 以下是規格書要求的所有方案實作
class FreeTier extends Subscription {
    public FreeTier() {
        super("免費方案", "SD", true, 1);
    }
}

class BasicPlan extends Subscription {
    public BasicPlan() {
        super("基本方案", "HD", false, 1);
    }
}

class StandardPlan extends Subscription {
    public StandardPlan() {
        super("標準方案", "FHD", false, 2);
    }
}

class PremiumPlan extends Subscription {
    public PremiumPlan() {
        super("豪華方案", "4K", false, 4);
    }
}