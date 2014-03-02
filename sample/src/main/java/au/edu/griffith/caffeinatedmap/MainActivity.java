package au.edu.griffith.caffeinatedmap;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.HashMap;

import au.edu.griffith.caffeinatedmap.clustering.Cluster;
import au.edu.griffith.caffeinatedmap.clustering.ClusteringSettings;
import au.edu.griffith.caffeinatedmap.markers.CaffeinatedClusterIcon;

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
                mCaffeinatedMap.getClusteringSettings().setTypeCountingEnabled(true);

                final HashMap<String, Integer> colours = new HashMap<String, Integer>();
                colours.put("Fred", getResources().getColor(R.color.red));
                colours.put("Jim", getResources().getColor(R.color.green));
                colours.put("Sally", getResources().getColor(R.color.blue));
                colours.put("Haley", getResources().getColor(R.color.pink));
                colours.put("Unknown", getResources().getColor(R.color.black));
                mCaffeinatedMap.getClusteringSettings().setOnClusterIconSelect(new ClusteringSettings.OnClusterIconSelect() {
                    @Override
                    public BitmapDescriptor selectIconFor(Cluster cluster) {
                        String[] type = cluster.getTypeList();
                        int[] d = new int[type.length];
                        int[] c = new int[d.length];
                        int i = 0;
                        for (String s : type) {
                            d[i] = cluster.getTypeCount(s);
                            c[i] = colours.get(s);
                            i++;
                        }
                        CaffeinatedClusterIcon caffeinatedClusterIcon = new CaffeinatedClusterIcon(getApplicationContext(), null, d, c);
                        return BitmapDescriptorFactory.fromBitmap(caffeinatedClusterIcon.toBitmap());
                    }
                });

                mCaffeinatedMap.getClusteringSettings().setOnCMOVisibilityChange(new ClusteringSettings.OnCMOVisibilityChange() {
                    @Override
                    public void onCMOVisible(String key) {
                        Log.e("au.edu.griffith.caffeinatedmap", "Visible: " + key);
                    }

                    @Override
                    public void onCMOHidden(String key) {
                        Log.e("au.edu.griffith.caffeinatedmap", "Hidden: " + key);
                    }
                });
            }
        }
    }

}
