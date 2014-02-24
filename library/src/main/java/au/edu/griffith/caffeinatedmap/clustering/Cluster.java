package au.edu.griffith.caffeinatedmap.clustering;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

public class Cluster extends MapPosition {

    private static final String ID_PREFIX = "C";
    private static int ID_NUMBER = 1;

    private List<Clusterable> mClusterables;

    public Cluster(Clusterable clusterable, Projection projection) {
        super(ID_PREFIX + ID_NUMBER++);
        mClusterables = new ArrayList<Clusterable>();

        // TODO TypeCount?

        if (clusterable != null && projection != null) {
            setPosition(clusterable.getPosition());
            add(clusterable);
            setScreenPosition(projection);
        }
    }

    public void add(Clusterable clusterable) {
        if (clusterable != null) {
            mClusterables.add(clusterable);
            // TODO Add to TypeCount?
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

    // TODO Get TypeCount?

    public int size() {
        return mClusterables.size();
    }

    @Override
    public void clearScreenPosition() {
        super.clearScreenPosition();
        for (Clusterable clusterable : mClusterables) {
            clusterable.clearScreenPosition();
        }
    }

    public void updateClusterPosition() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Clusterable clusterable : mClusterables) {
            builder.include(clusterable.getPosition());
        }
        setPosition(builder.build().getCenter());
    }
}
