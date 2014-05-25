package au.edu.griffith.caffeinatedmap.sample;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.edu.griffith.caffeinatedmap.markers.CaffeinatedMarkerOptions;

public class RandomMarkers {

    private List<MarkerOptions> mMarkers;
    private List<CaffeinatedMarkerOptions> mCMOList;
    private Random mRandom;
    private String[] mTypes;

    public RandomMarkers(int numOfMarkers) {
        mMarkers = new ArrayList<MarkerOptions>();
        mCMOList = new ArrayList<CaffeinatedMarkerOptions>();
        mRandom = new Random();
        mTypes = new String[]{"Fred", "Jim", "Sally", "Haley"};

        /** Random Markers */
        for (int i = 0; i < numOfMarkers; i++) {
            LatLng latLng = new LatLng(getRandomInRange(-85, 85), getRandomInRange(-180, 180));

            mMarkers.add(new MarkerOptions().position(latLng).title("" + i));

            CaffeinatedMarkerOptions marker = new CaffeinatedMarkerOptions();
            marker.position(latLng);
            marker.title("" + i);
            marker.type(mTypes[mRandom.nextInt(4)]);
            mCMOList.add(marker);
        }

        /** Static Markers *//*
        LatLng[] latLng = StaticLatLngs.getStaticLatLngs();
        for (int i = 0; i < 1978; i++) {
            mMarkers.add(new MarkerOptions().position(latLng[i]).title("" + i));

            CaffeinatedMarkerOptions marker = new CaffeinatedMarkerOptions();
            marker.position(latLng[i]);
            marker.title("" + i);
            marker.type(mTypes[mRandom.nextInt(4)]);
            mCMOList.add(marker);
        }*/
    }

    public List<MarkerOptions> getMarkers() {
        return mMarkers;
    }

    public List<CaffeinatedMarkerOptions> getCMOList() {
        return mCMOList;
    }

    private double getRandomInRange(double from, double to) {
        return (mRandom.nextDouble() * (to - from) + from);
    }

}
