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

public class ClusterHandler implements ClusterBuildTask.BuildTaskCallback {

    private final WeakReference<GoogleMap> mMapReference;
    private ClusteringSettings mSettings;

    private List<Clusterable> mClusterables;
    private List<Cluster> mCurrentClusters;
    private HashMap<String, Marker> mVisibleClusters;
    private float mZoomLevel;

    public ClusterHandler(WeakReference<GoogleMap> mapReference, ClusteringSettings settings) {
        mMapReference = mapReference;
        mSettings = (settings != null) ? settings : new ClusteringSettings().setClusterOptions(new ClusterOptions());

        mClusterables = new ArrayList<Clusterable>();
        mVisibleClusters = new HashMap<String, Marker>();
        mZoomLevel = 0f;

        // TODO Clusterable Types?
    }

    public void addClusterable(Clusterable clusterable) {
        if (clusterable != null) {
            mClusterables.add(clusterable);
        }
    }

    public void addClusterableList(List<Clusterable> clusterables) {
        if (clusterables != null) {
            mClusterables.addAll(clusterables);
        }
    }

    public void clearClusterables() {
        mClusterables = new ArrayList<Clusterable>();
    }

    public List<Cluster> getClusters() {
        return mCurrentClusters;
    }

    public void updateClusters() {
        GoogleMap googleMap = mMapReference.get();
        if (googleMap != null) {
            float zoom = googleMap.getCameraPosition().zoom;
            if ((int) mZoomLevel != (int) zoom) {
                mZoomLevel = zoom;
                ClusterBuildTask.BuildTaskArgs buildTaskArgs = new ClusterBuildTask.BuildTaskArgs();
                buildTaskArgs.settings = mSettings;
                buildTaskArgs.projection = googleMap.getProjection();
                buildTaskArgs.clusterables = mClusterables;
                new ClusterBuildTask(this).execute(buildTaskArgs);
            } else {
                updateVisibleClusters();
            }
        }
    }

    public void forceUpdate() {
        mZoomLevel = 0f;
        updateClusters();
    }

    public void updateVisibleClusters() {
        GoogleMap googleMap = mMapReference.get();
        if (googleMap != null && mCurrentClusters != null) {
            LatLngBounds visibleBounds = googleMap.getProjection().getVisibleRegion().latLngBounds;

            for (Cluster cluster : mCurrentClusters) {
                if (visibleBounds.contains(cluster.getPosition())) {
                    MarkerOptions options = new MarkerOptions();
                    options.position(cluster.getPosition());

                    if (cluster.size() > 1) {
                        // TODO Icons
                        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                        options.title(cluster.getId());
                    } else if (cluster.size() == 1) {
                        options.title(cluster.getClusterableAtIndex(0).getId());
                        // TODO Polygons
                    }

                    addClusterToMap(cluster.getId(), options);
                } else {
                    removeClusterFromMap(cluster.getId());
                }
            }
        }
    }

    private void addClusterToMap(String id, MarkerOptions options) {
        if (!mVisibleClusters.containsKey(id)) {
            GoogleMap googleMap = mMapReference.get();
            if (googleMap != null) {
                mVisibleClusters.put(id, googleMap.addMarker(options));
            }
        }
    }

    private void removeClusterFromMap(String id) {
        if (mVisibleClusters.containsKey(id)) {
            mVisibleClusters.get(id).remove();
            mVisibleClusters.remove(id);
        }
    }

    private void removeAllClustersFromMap() {
        for (Marker marker : mVisibleClusters.values()) {
            marker.remove();
        }
        mVisibleClusters = new HashMap<String, Marker>();
    }

    @Override
    public void onBuildTaskReturn(List<Cluster> clusters) {
        mCurrentClusters = clusters;
        removeAllClustersFromMap();
        updateVisibleClusters();
    }
}
