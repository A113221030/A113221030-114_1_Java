public abstract class Subscription {
    public String planName;
    public String resolution;
    public int maxDevices;
    public boolean hasAds;

    public Subscription(String planName, String resolution, int maxDevices, boolean hasAds) {
        this.planName = planName;
        this.resolution = resolution;
        this.maxDevices = maxDevices;
        this.hasAds = hasAds;
    }
}