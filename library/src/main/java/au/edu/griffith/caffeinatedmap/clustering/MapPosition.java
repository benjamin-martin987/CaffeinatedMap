package au.edu.griffith.caffeinatedmap.clustering;

import android.graphics.Point;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;

import au.edu.griffith.caffeinatedmap.utils.Distance;

abstract class MapPosition {

    private LatLng mPosition;
    private Point mScreenPosition;

    public LatLng getPosition() {
        return mPosition;
    }

    void setPosition(LatLng position) {
        mPosition = position;
    }

    public Point getScreenPosition() {
        return mScreenPosition;
    }

    void setScreenPosition(Projection projection) {
        if (projection != null && mPosition != null) {
            mScreenPosition = projection.toScreenLocation(mPosition);
        }
    }

    void clearScreenPosition() {
        mScreenPosition = null;
    }

    public double getPixelDistanceFrom(MapPosition other) {
        if (mScreenPosition != null && other != null) {
            Point otherPosition = other.getScreenPosition();
            if (otherPosition != null) {
                return Distance.getHypotenuse(mScreenPosition.x, mScreenPosition.y, otherPosition.x, otherPosition.y);
            }
        }
        return 0.0;
    }

}
