package au.edu.griffith.caffeinatedmap;

import au.edu.griffith.caffeinatedmap.clustering.ClusteringSettings;

public class CaffeinatedOptions {

    private static final boolean DEFAULT_CLUSTERING_ENABLED = false;

    private ClusteringSettings mClusteringSettings = null;

    private boolean mClusteringEnabled = DEFAULT_CLUSTERING_ENABLED;

    public CaffeinatedOptions clusteringSettings(ClusteringSettings settings) {
        mClusteringSettings = settings;
        return this;
    }

    public ClusteringSettings getClusteringSetting() {
        return mClusteringSettings;
    }

    public CaffeinatedOptions clusteringEnabled(Boolean enabled) {
        mClusteringEnabled = enabled;
        return this;
    }

    public boolean isClusteringEnabled() {
        return mClusteringEnabled;
    }

}
