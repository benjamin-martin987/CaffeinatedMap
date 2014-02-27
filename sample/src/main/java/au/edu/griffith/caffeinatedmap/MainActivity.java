package au.edu.griffith.caffeinatedmap;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;

public class MainActivity extends Activity {

    private static final String MAP_FRAGMENT_TAG = "caffeinatedmap";

    private CaffeinatedMapFragment mMapFragment;
    private CaffeinatedMap mCaffeinatedMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpMapFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        RandomMarkers randomMarkers = new RandomMarkers(1000);
        mCaffeinatedMap.addClusterableMarkers(randomMarkers.getCMOList());
    }

    private void setUpMapFragment() {
        mMapFragment = (CaffeinatedMapFragment) getFragmentManager().findFragmentByTag(MAP_FRAGMENT_TAG);
        if (mMapFragment == null) {
            mMapFragment = CaffeinatedMapFragment.newInstance();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, mMapFragment, MAP_FRAGMENT_TAG).commit();
        }
    }

    private void setUpMapIfNeeded() {
        if (mCaffeinatedMap == null) {
            mCaffeinatedMap = mMapFragment.getCaffeinatedMap();
            if (mCaffeinatedMap != null) {
                mCaffeinatedMap.setMyLocationEnabled(true);
                mCaffeinatedMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {
                        Toast.makeText(getApplication(), "OnCameraChangeOverride", Toast.LENGTH_SHORT).show();
                    }
                });
                mCaffeinatedMap.getClusteringSettings().setClusterSize(400);
            }
        }
    }

}
