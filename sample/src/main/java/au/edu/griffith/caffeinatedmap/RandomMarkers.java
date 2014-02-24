package au.edu.griffith.caffeinatedmap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.edu.griffith.caffeinatedmap.clustering.Clusterable;

public class RandomMarkers {

    private List<MarkerOptions> mMarkers;
    private List<Clusterable> mClusterables;
    private Random random;

    public RandomMarkers(int numOfMarkers) {
        mMarkers = new ArrayList<MarkerOptions>();
        mClusterables = new ArrayList<Clusterable>();
        random = new Random();

        for (int i = 0; i < numOfMarkers; i++) {
            LatLng latLng = new LatLng(getRandomInRange(-85, 85), getRandomInRange(-180, 180));

            mMarkers.add(new MarkerOptions().position(latLng).title("" + i));

            Marker marker = new Marker();
            marker.setPosition(latLng);
            mClusterables.add(marker);
        }
    }

    public List<MarkerOptions> getMarkers() {
        return mMarkers;
    }

    public List<Clusterable> getClusterables() {
        return mClusterables;
    }

    private double getRandomInRange(double from, double to) {
        return (random.nextDouble() * (to - from) + from);
    }

    private class Marker extends Clusterable {

    }

}
