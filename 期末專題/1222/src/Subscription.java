public abstract class Subscription {
    public String planName;
    public boolean hasAds;
    public Subscription(String name, boolean ads) { this.planName = name; this.hasAds = ads; }
}

class FreeTier extends Subscription { public FreeTier() { super("免費方案", true); } }
class PremiumPlan extends Subscription { public PremiumPlan() { super("豪華方案", false); } }