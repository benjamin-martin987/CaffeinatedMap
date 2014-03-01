package au.edu.griffith.caffeinatedmap.clustering;

import com.google.android.gms.maps.model.BitmapDescriptor;

public abstract class ClusterIconSelector {

    public abstract BitmapDescriptor selectIconFor(Cluster cluster);

}
