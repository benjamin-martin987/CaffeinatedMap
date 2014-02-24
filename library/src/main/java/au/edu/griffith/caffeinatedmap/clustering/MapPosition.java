package au.edu.griffith.caffeinatedmap.clustering;

import android.graphics.Point;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;

public abstract class MapPosition {

    private String mId;
    private LatLng mPosition;
    private Point mScreenPosition;

    protected MapPosition(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public LatLng getPosition() {
        return mPosition;
    }

    public void setPosition(LatLng position) {
        mPosition = position;
    }

    public Point getScreenPosition() {
        return mScreenPosition;
    }

    public void setScreenPosition(Projection projection) {
        if (projection != null && mPosition != null) {
            mScreenPosition = projection.toScreenLocation(mPosition);
        }
    }

    public void clearScreenPosition() {
        mScreenPosition = null;
    }

    public double getPixelDistanceFrom(MapPosition other) {
        if (mScreenPosition != null && other != null) {
            Point otherPosition = other.getScreenPosition();
            if (otherPosition != null) {
                return getHypotenuse(mScreenPosition.x, mScreenPosition.y, otherPosition.x, otherPosition.y);
            }
        }
        return 0.0;
    }

    private double getHypotenuse(double x1, double y1, double x2, double y2) {
        double x = getAbsolute(x1 - x2);
        double y = getAbsolute(y1 - y2);
        return Math.sqrt(x * x + y * y);
    }

    private double getAbsolute(double value) {
        return (value <= 0.0) ? 0.0 - value : value;
    }

}
