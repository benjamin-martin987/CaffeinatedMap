package au.edu.griffith.caffeinatedmap.clustering;

import com.google.android.gms.maps.model.BitmapDescriptor;

public class ClusteringSettings {

    private static final double DEFAULT_CLUSTER_SIZE = 300;
    private static final boolean DEFAULT_COUNT_CLUSTERABLE_TYPES = false;

    OnClusterIconSelect clusterIconSelect = null;
    OnCMOVisibilityChange cmoVisibilityChange = null;

    private double mClusterSize = DEFAULT_CLUSTER_SIZE;
    private boolean mTypeCounting = DEFAULT_COUNT_CLUSTERABLE_TYPES;

    public ClusteringSettings setOnClusterIconSelect(OnClusterIconSelect oCIS) {
        clusterIconSelect = oCIS;
        return this;
    }

    public ClusteringSettings setOnCMOVisibilityChange(OnCMOVisibilityChange oCVC) {
        cmoVisibilityChange = oCVC;
        return this;
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

    public interface OnClusterIconSelect {
        BitmapDescriptor selectIconFor(Cluster cluster);
    }

    public interface OnCMOVisibilityChange {
        void onCMOVisible(String key);

        void onCMOHidden(String key);
    }

}
