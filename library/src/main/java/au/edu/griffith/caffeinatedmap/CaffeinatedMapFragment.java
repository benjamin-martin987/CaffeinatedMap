package au.edu.griffith.caffeinatedmap;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;

import java.lang.ref.WeakReference;

public class CaffeinatedMapFragment extends MapFragment {

    private static final String MAP_OPTIONS = "MapOptions";

    private CaffeinatedMap mCaffeinatedMap;

    public static CaffeinatedMapFragment newInstance() {
        return new CaffeinatedMapFragment();
    }

    public static CaffeinatedMapFragment newInstance(GoogleMapOptions options) {
        CaffeinatedMapFragment mapFragment = new CaffeinatedMapFragment();
        Bundle args = new Bundle();
        args.putParcelable(MAP_OPTIONS, options);
        mapFragment.setArguments(args);
        return mapFragment;
    }

    public CaffeinatedMap getCaffeinatedMap() {
        if (mCaffeinatedMap == null) {
            GoogleMap googleMap = getMap();
            if (googleMap != null) {
                mCaffeinatedMap = new CaffeinatedMap(new WeakReference<GoogleMap>(googleMap));
            }
        }
        return mCaffeinatedMap;
    }

}
