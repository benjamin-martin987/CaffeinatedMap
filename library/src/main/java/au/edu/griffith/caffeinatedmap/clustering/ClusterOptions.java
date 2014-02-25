package au.edu.griffith.caffeinatedmap.clustering;

public class ClusterOptions {

    private static final boolean DEFAULT_COUNT_CLUSTERABLE_TYPES = false;

    private boolean mTypeCounting = DEFAULT_COUNT_CLUSTERABLE_TYPES;

    public ClusterOptions setTypeCounting(boolean enabled) {
        mTypeCounting = enabled;
        return this;
    }

    public boolean isTypeCountingEnabled() {
        return mTypeCounting;
    }

}
