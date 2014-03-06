package au.edu.griffith.caffeinatedmap;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.HashMap;

import au.edu.griffith.caffeinatedmap.clustering.Cluster;
import au.edu.griffith.caffeinatedmap.clustering.ClusteringSettings;
import au.edu.griffith.caffeinatedmap.markers.CaffeinatedClusterIcon;

public class MapActivity extends Activity {

    private static final String MAP_FRAGMENT_TAG = "caffeinatedmap";

    private CaffeinatedMapFragment mMapFragment;
    private CaffeinatedMap mCaffeinatedMap;
    private ProgressBar progressBar;

    private int mNumberOfCMO;
    private int mClusterSize;
    private boolean mCaffeinatedClusterIconEnabled;
    private boolean mTypeCountingEnabled;
    private boolean mCMOVisibilityChangeEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        progressBar = (ProgressBar) findViewById(R.id.progress);

        getArguments();
        setUpMapFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        loadSettings();
    }

    private void getArguments() {
        Bundle args = getIntent().getExtras();
        mNumberOfCMO = args.getInt(MainActivity.NUMBER_OF_CMO_TAG);
        mClusterSize = args.getInt(MainActivity.CLUSTER_SIZE_TAG);
        mCaffeinatedClusterIconEnabled = args.getBoolean(MainActivity.CAFFEINATED_CLUSTER_ICON_TAG);
        mTypeCountingEnabled = args.getBoolean(MainActivity.TYPE_COUNTING_TAG);
        mCMOVisibilityChangeEnabled = args.getBoolean(MainActivity.CMO_VISIBILITY_CHANGE_TAG);
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
            }
        }
    }

    private void loadSettings() {
        RandomMarkers randomMarkers = new RandomMarkers(mNumberOfCMO);
        mCaffeinatedMap.getClusteringSettings().setClusterSize(mClusterSize);

        if (mCaffeinatedClusterIconEnabled) {
            mCaffeinatedMap.getClusteringSettings().setTypeCountingEnabled(mTypeCountingEnabled);

            final HashMap<String, Integer> colours = new HashMap<String, Integer>();
            colours.put("Fred", getResources().getColor(R.color.red));
            colours.put("Jim", getResources().getColor(R.color.green));
            colours.put("Sally", getResources().getColor(R.color.blue));
            colours.put("Haley", getResources().getColor(R.color.pink));
            colours.put("Unknown", getResources().getColor(R.color.black));

            mCaffeinatedMap.getClusteringSettings().setOnClusterIconSelect(new ClusteringSettings.OnClusterIconSelect() {
                @Override
                public BitmapDescriptor selectIconFor(Cluster cluster) {
                    String[] typeList = cluster.getTypeList();
                    int[] typeCount = new int[typeList.length];
                    int[] typeColours = new int[typeCount.length];
                    for (int i = 0; i < typeList.length; i++) {
                        typeCount[i] = cluster.getTypeCount(typeList[i]);
                        typeColours[i] = colours.get(typeList[i]);
                    }
                    CaffeinatedClusterIcon caffeinatedClusterIcon = new CaffeinatedClusterIcon(getApplicationContext(), null, typeCount, typeColours);
                    return BitmapDescriptorFactory.fromBitmap(caffeinatedClusterIcon.toBitmap());
                }
            });
        }

        if (mCMOVisibilityChangeEnabled) {
            mCaffeinatedMap.getClusteringSettings().setOnCMOVisibilityChange(new ClusteringSettings.OnCMOVisibilityChange() {
                @Override
                public void onCMOVisible(String key) {
                    Toast.makeText(getApplicationContext(), "Visible: " + key, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCMOHidden(String key) {
                    Toast.makeText(getApplicationContext(), "Hidden: " + key, Toast.LENGTH_SHORT).show();
                }
            });
        }

        mCaffeinatedMap.getClusteringSettings().setOnClusteringListener(new ClusteringSettings.OnClusteringListener() {
            @Override
            public void onClusteringStart() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onClusteringFinish() {
                progressBar.setVisibility(View.GONE);
            }
        });

        mCaffeinatedMap.addClusterableMarkers(randomMarkers.getCMOList());
    }

}
