package au.edu.griffith.caffeinatedmap.clustering;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import au.edu.griffith.caffeinatedmap.R;
import au.edu.griffith.caffeinatedmap.markers.CaffeinatedMarkerOptions;

public class ClusterHandler {

    private final WeakReference<GoogleMap> mMapReference;
    private ClusteringSettings mSettings;

    private HashMap<String, CaffeinatedMarkerOptions> mCaffeinatedMarkerOptions;
    private HashMap<String, Marker> mVisibleClusters;
    private HashMap<String, Marker> mVisibleMarkers;
    private List<Clusterable> mClusterables;
    private List<Cluster> mCurrentClusters;
    private float mZoomLevel;

    public ClusterHandler(WeakReference<GoogleMap> mapReference) {
        mMapReference = mapReference;
        mSettings = new ClusteringSettings();

        mCaffeinatedMarkerOptions = new HashMap<String, CaffeinatedMarkerOptions>();
        mClusterables = new ArrayList<Clusterable>();
        mVisibleClusters = new HashMap<String, Marker>();
        mVisibleMarkers = new HashMap<String, Marker>();
        mZoomLevel = 0f;
    }

    public ClusteringSettings getSettings() {
        return mSettings;
    }

    public void addMarker(CaffeinatedMarkerOptions options) {
        if (options != null) {
            mCaffeinatedMarkerOptions.put(options.getKey(), options);
            Clusterable clusterable = new Clusterable(options.getKey(), options.getType());
            clusterable.setPosition(options.getPosition());
            mClusterables.add(clusterable);
        }
    }

    public void addMarkers(List<CaffeinatedMarkerOptions> list) {
        if (list != null) {
            for (CaffeinatedMarkerOptions options : list) {
                addMarker(options);
            }
        }
    }

    public void clearMarkers() {
        removeAllFromMap();
        mClusterables = new ArrayList<Clusterable>();
        mCaffeinatedMarkerOptions = new HashMap<String, CaffeinatedMarkerOptions>();
    }

    public List<Cluster> getClusters() {
        return mCurrentClusters;
    }

    public Marker getMarker(String key) {
        return (mVisibleMarkers.containsKey(key)) ? mVisibleMarkers.get(key) : null;
    }

    public void updateClusters() {
        GoogleMap googleMap = mMapReference.get();
        if (googleMap != null) {
            float zoom = googleMap.getCameraPosition().zoom;
            if ((int) mZoomLevel != (int) zoom) {
                if (mSettings.clusteringListener != null) {
                    mSettings.clusteringListener.onClusteringStart();
                }
                mZoomLevel = zoom;
                ClusterBuildTask.BuildTaskArgs buildTaskArgs = new ClusterBuildTask.BuildTaskArgs();
                buildTaskArgs.settings = mSettings;
                buildTaskArgs.projection = googleMap.getProjection();
                buildTaskArgs.clusterables = mClusterables;
                buildTaskArgs.zoom = (int) mZoomLevel;
                new ClusterBuildTask(new ClusterBuildTask.BuildTaskCallback() {
                    @Override
                    public void onBuildTaskReturn(List<Cluster> clusters) {
                        onClusterBuildTaskReturn(clusters);
                    }
                }).execute(buildTaskArgs);
            } else {
                updateVisibleClusters();
            }
        }
    }

    public void forceUpdate() {
        mZoomLevel = 0f;
        updateClusters();
    }

    private void updateVisibleClusters() {
        GoogleMap googleMap = mMapReference.get();
        if (googleMap != null && mCurrentClusters != null) {
            LatLngBounds visibleBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;

            for (Cluster cluster : mCurrentClusters) {
                if (visibleBounds.contains(cluster.getPosition())) {
                    if (cluster.size() > 1) {
                        addClusterToMap(cluster.getKey(), getClusterMakerOptions(cluster));
                    } else if (cluster.size() == 1) {
                        String cmoKey = cluster.getClusterableAtIndex(0).getCMOKey();
                        addMarkerToMap(cmoKey, getCMO(cmoKey));
                    }
                } else {
                    if (cluster.size() > 1) {
                        removeClusterFromMap(cluster.getKey());
                    } else if (cluster.size() == 1) {
                        removeMarkerFromMap(cluster.getClusterableAtIndex(0).getCMOKey());
                    }
                }
            }
        }
    }

    private MarkerOptions getCMO(String key) {
        if (mCaffeinatedMarkerOptions.containsKey(key)) {
            return mCaffeinatedMarkerOptions.get(key).getMarkerOptions();
        }
        return null;
    }

    private MarkerOptions getClusterMakerOptions(Cluster cluster) {
        MarkerOptions options = new MarkerOptions();
        options.icon((mSettings.clusterIconSelect != null) ? mSettings.clusterIconSelect.selectIconFor(cluster) : BitmapDescriptorFactory.fromResource(R.drawable.ic_default_cluster));
        options.anchor(0.5f, 0.5f);
        options.position(cluster.getPosition());
        options.title(cluster.getKey());
        return options;
    }

    private void addClusterToMap(String key, MarkerOptions options) {
        GoogleMap googleMap = mMapReference.get();
        if (googleMap != null && options != null && !mVisibleClusters.containsKey(key)) {
            mVisibleClusters.put(key, googleMap.addMarker(options));
        }
    }

    private void addMarkerToMap(String key, MarkerOptions options) {
        GoogleMap googleMap = mMapReference.get();
        if (googleMap != null && options != null && !mVisibleMarkers.containsKey(key)) {
            mVisibleMarkers.put(key, googleMap.addMarker(options));
            cmoVisibilityCall(key, true);
        }
    }

    private void removeClusterFromMap(String key) {
        if (mVisibleClusters.containsKey(key)) {
            mVisibleClusters.get(key).remove();
            mVisibleClusters.remove(key);
        }
    }

    private void removeMarkerFromMap(String key) {
        if (mVisibleMarkers.containsKey(key)) {
            cmoVisibilityCall(key, false);
            mVisibleMarkers.get(key).remove();
            mVisibleMarkers.remove(key);
        }
    }

    private void removeAllFromMap() {
        for (Marker marker : mVisibleClusters.values()) {
            marker.remove();
        }
        mVisibleClusters = new HashMap<String, Marker>();

        for (String key : mVisibleMarkers.keySet()) {
            cmoVisibilityCall(key, false);
            mVisibleMarkers.get(key).remove();
        }
        mVisibleMarkers = new HashMap<String, Marker>();
    }

    private void cmoVisibilityCall(String cmoKey, boolean visible) {
        if (mSettings.cmoVisibilityChange != null) {
            if (visible) {
                mSettings.cmoVisibilityChange.onCMOVisible(cmoKey);
            } else {
                mSettings.cmoVisibilityChange.onCMOHidden(cmoKey);
            }
        }
    }

    private void onClusterBuildTaskReturn(List<Cluster> clusters) {
        mCurrentClusters = clusters;
        removeAllFromMap();
        if (mSettings.clusteringListener != null) {
            mSettings.clusteringListener.onClusteringFinish();
        }
        updateVisibleClusters();
    }

}
