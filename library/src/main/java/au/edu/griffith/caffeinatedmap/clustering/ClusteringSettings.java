package au.edu.griffith.caffeinatedmap.clustering;

public class ClusteringSettings {

    private static final double DEFAULT_CLUSTER_SIZE = 300;

    private ClusterOptions mClusterOptions = null;

    private double mClusterSize = DEFAULT_CLUSTER_SIZE;

    public ClusteringSettings setClusterOptions(ClusterOptions options) {
        mClusterOptions = options;
        return this;
    }

    public ClusterOptions getClusterOptions() {
        return mClusterOptions;
    }

    public ClusteringSettings setClusterSize(double size) {
        mClusterSize = size;
        return this;
    }

    public double getClusterSize() {
        return mClusterSize;
    }

}
