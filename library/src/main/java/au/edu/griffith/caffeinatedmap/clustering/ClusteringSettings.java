package au.edu.griffith.caffeinatedmap.clustering;

import com.google.android.gms.maps.model.BitmapDescriptor;

public class ClusteringSettings {

    private static final double DEFAULT_CLUSTER_SIZE = 300;
    private static final boolean DEFAULT_COUNT_CLUSTERABLE_TYPES = false;
    private static final int DEFAULT_DISABLED_AT_ZOOM_LEVEL = 16;

    OnClusterIconSelect clusterIconSelect = null;
    OnCMOVisibilityChange cmoVisibilityChange = null;
    OnClusteringListener clusteringListener = null;

    private double mClusterSize = DEFAULT_CLUSTER_SIZE;
    private boolean mTypeCounting = DEFAULT_COUNT_CLUSTERABLE_TYPES;
    private int mDisabledAtZoom = DEFAULT_DISABLED_AT_ZOOM_LEVEL;

    public ClusteringSettings setOnClusterIconSelect(OnClusterIconSelect oCIS) {
        clusterIconSelect = oCIS;
        return this;
    }

    public ClusteringSettings setOnCMOVisibilityChange(OnCMOVisibilityChange oCVC) {
        cmoVisibilityChange = oCVC;
        return this;
    }

    public ClusteringSettings setOnClusteringListener(OnClusteringListener oCL) {
        clusteringListener = oCL;
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

    public int getDisabledZoomLevel() {
        return mDisabledAtZoom;
    }

    public ClusteringSettings setDisabledAtZoom(int zoom) {
        mDisabledAtZoom = zoom;
        return this;
    }

    public interface OnClusterIconSelect {
        BitmapDescriptor selectIconFor(Cluster cluster);
    }

    public interface OnCMOVisibilityChange {
        void onCMOVisible(String key);

        void onCMOHidden(String key);
    }

    public interface OnClusteringListener {
        void onClusteringStart();

        void onClusteringFinish();
    }

}
