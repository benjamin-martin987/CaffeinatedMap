package au.edu.griffith.caffeinatedmap.clustering;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

public class Cluster extends MapPosition {

    private static final String KEY_PREFIX = "C";
    private static int KEY_NUMBER = 1;

    private final ClusteringSettings mOptions;
    private final String mKey;

    private List<Clusterable> mClusterables;
    private InClusterTypeCounter mTypeCounter;

    public Cluster(ClusteringSettings options, Clusterable clusterable, Projection projection) {
        mKey = KEY_PREFIX + KEY_NUMBER++;
        mOptions = (options != null) ? options : new ClusteringSettings();
        mClusterables = new ArrayList<Clusterable>();

        if (mOptions.isTypeCountingEnabled()) {
            mTypeCounter = new InClusterTypeCounter();
        }

        if (clusterable != null && projection != null) {
            setPosition(clusterable.getPosition());
            add(clusterable);
            setScreenPosition(projection);
        }
    }

    public String getKey() {
        return mKey;
    }

    void add(Clusterable clusterable) {
        if (clusterable != null) {
            mClusterables.add(clusterable);
            if (mTypeCounter != null) {
                mTypeCounter.incrementType(clusterable.getCMOType());
            }
        }
    }

    public List<Clusterable> getClusterables() {
        return mClusterables;
    }

    public Clusterable getClusterableAtIndex(int index) {
        if (index < mClusterables.size()) {
            return mClusterables.get(index);
        }
        return null;
    }

    public String[] getTypeList() {
        return (mTypeCounter != null) ? mTypeCounter.getListOfTypes() : null;
    }

    public int getTypeCount(String type) {
        return (mTypeCounter != null) ? mTypeCounter.getType(type) : 0;
    }

    public int size() {
        return mClusterables.size();
    }

    @Override
    void clearScreenPosition() {
        super.clearScreenPosition();
        for (Clusterable clusterable : mClusterables) {
            clusterable.clearScreenPosition();
        }
    }

    void updateClusterPosition() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Clusterable clusterable : mClusterables) {
            builder.include(clusterable.getPosition());
        }
        setPosition(builder.build().getCenter());
    }
}
