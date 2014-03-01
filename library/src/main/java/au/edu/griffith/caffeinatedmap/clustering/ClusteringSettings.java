package au.edu.griffith.caffeinatedmap.clustering;

public class ClusteringSettings {

    private static final double DEFAULT_CLUSTER_SIZE = 300;
    private static final boolean DEFAULT_COUNT_CLUSTERABLE_TYPES = false;

    private ClusterIconSelector mCIS = null;

    private double mClusterSize = DEFAULT_CLUSTER_SIZE;
    private boolean mTypeCounting = DEFAULT_COUNT_CLUSTERABLE_TYPES;

    public ClusterIconSelector getCIS() {
        return mCIS;
    }

    public void setCIS(ClusterIconSelector cis) {
        mCIS = cis;
    }

    public double getClusterSize() {
        return mClusterSize;
    }

    public ClusteringSettings setClusterSize(double size) {
        mClusterSize = size;
        return this;
    }

    public boolean isTypeCountingEnabled() {
        return mTypeCounting;
    }

    public ClusteringSettings setTypeCountingEnabled(boolean enabled) {
        mTypeCounting = enabled;
        return this;
    }

}
