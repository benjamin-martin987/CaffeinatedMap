package au.edu.griffith.caffeinatedmap;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

import java.lang.ref.WeakReference;
import java.util.List;

import au.edu.griffith.caffeinatedmap.clustering.ClusterHandler;
import au.edu.griffith.caffeinatedmap.clustering.Clusterable;

public class CaffeinatedMap extends BaseCaffeinatedMap implements IGoogleMap.OnCameraChangeListener {

    private OnCameraChangeListener mCameraChangeListener;

    private ClusterHandler mClusterHandler;

    public CaffeinatedMap(WeakReference<GoogleMap> mapReference) {
        super(mapReference);
        mClusterHandler = new ClusterHandler(mapReference);

        GoogleMap googleMap = mMapReference.get();
        if (googleMap != null) {
            googleMap.setOnCameraChangeListener(this);
        }
    }

    public void addClusterableMarkers(List<Clusterable> markers) {
        mClusterHandler.addClusterableList(markers);
    }

    public void setOnCameraChangeListener(OnCameraChangeListener listener) {
        mCameraChangeListener = listener;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        mClusterHandler.updateClusters();
        if (mCameraChangeListener != null) {
            mCameraChangeListener.onCameraChange(cameraPosition);
        }
    }

    public static interface OnCameraChangeListener {
        public abstract void onCameraChange(CameraPosition position);
    }

}
