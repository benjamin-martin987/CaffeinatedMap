package au.edu.griffith.caffeinatedmap;

import android.graphics.Bitmap;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

import java.lang.ref.WeakReference;

abstract class BaseCaffeinatedMap implements IGoogleMap {

    protected final WeakReference<GoogleMap> mMapReference;

    protected BaseCaffeinatedMap(WeakReference<GoogleMap> mapReference) {
        mMapReference = mapReference;
    }

    @Override
    public Circle addCircle(CircleOptions options) {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.addCircle(options);
    }

    @Override
    public GroundOverlay addGroundOverlay(GroundOverlayOptions options) {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.addGroundOverlay(options);
    }

    @Override
    public Marker addMarker(MarkerOptions options) {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.addMarker(options);
    }

    @Override
    public Polygon addPolygon(PolygonOptions options) {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.addPolygon(options);
    }

    @Override
    public Polyline addPolyline(PolylineOptions options) {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.addPolyline(options);
    }

    @Override
    public TileOverlay addTileOverlay(TileOverlayOptions options) {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.addTileOverlay(options);
    }

    @Override
    public void animateCamera(CameraUpdate update, int durationMs, GoogleMap.CancelableCallback callback) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.animateCamera(update, durationMs, callback);
        }
    }

    @Override
    public void animateCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.animateCamera(update, callback);
        }
    }

    @Override
    public void animateCamera(CameraUpdate update) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.animateCamera(update);
        }
    }

    @Override
    public void clear() {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.clear();
        }
    }

    @Override
    public CameraPosition getCameraPosition() {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.getCameraPosition();
    }

    @Override
    public int getMapType() {
        GoogleMap map = mMapReference.get();
        return (map == null) ? 0 : map.getMapType();
    }

    @Override
    public float getMaxZoomLevel() {
        GoogleMap map = mMapReference.get();
        return (map == null) ? 0 : map.getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        GoogleMap map = mMapReference.get();
        return (map == null) ? 0 : map.getMinZoomLevel();
    }

    @Override
    public Projection getProjection() {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.getProjection();
    }

    @Override
    public UiSettings getUiSettings() {
        GoogleMap map = mMapReference.get();
        return (map == null) ? null : map.getUiSettings();
    }

    @Override
    public boolean isBuildingsEnabled() {
        GoogleMap map = mMapReference.get();
        return map != null && map.isBuildingsEnabled();
    }

    @Override
    public boolean isIndoorEnabled() {
        GoogleMap map = mMapReference.get();
        return map != null && map.isIndoorEnabled();
    }

    @Override
    public boolean isMyLocationEnabled() {
        GoogleMap map = mMapReference.get();
        return map != null && map.isMyLocationEnabled();
    }

    @Override
    public boolean isTrafficEnabled() {
        GoogleMap map = mMapReference.get();
        return map != null && map.isTrafficEnabled();
    }

    @Override
    public void moveCamera(CameraUpdate update) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.moveCamera(update);
        }
    }

    @Override
    public void setBuildingsEnabled(boolean enabled) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setBuildingsEnabled(enabled);
        }
    }

    @Override
    public boolean setIndoorEnabled(boolean enabled) {
        GoogleMap map = mMapReference.get();
        return map != null && map.isIndoorEnabled();
    }

    @Override
    public void setInfoWindowAdapter(GoogleMap.InfoWindowAdapter adapter) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setInfoWindowAdapter(adapter);
        }
    }

    @Override
    public void setLocationSource(LocationSource source) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setLocationSource(source);
        }
    }

    @Override
    public void setMapType(int type) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setMapType(type);
        }
    }

    @Override
    public void setMyLocationEnabled(boolean enabled) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setMyLocationEnabled(enabled);
        }
    }

    @Override
    public void setOnCameraChangeListener(GoogleMap.OnCameraChangeListener listener) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnCameraChangeListener(listener);
        }
    }

    @Override
    public void setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener listener) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnInfoWindowClickListener(listener);
        }
    }

    @Override
    public void setOnMapClickListener(GoogleMap.OnMapClickListener listener) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnMapClickListener(listener);
        }
    }

    @Override
    public void setOnMapLoadedCallback(GoogleMap.OnMapLoadedCallback callback) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnMapLoadedCallback(callback);
        }
    }

    @Override
    public void setOnMapLongClickListener(GoogleMap.OnMapLongClickListener listener) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnMapLongClickListener(listener);
        }
    }

    @Override
    public void setOnMarkerClickListener(GoogleMap.OnMarkerClickListener listener) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnMarkerClickListener(listener);
        }
    }

    @Override
    public void setOnMarkerDragListener(GoogleMap.OnMarkerDragListener listener) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnMarkerDragListener(listener);
        }
    }

    @Override
    public void setOnMyLocationButtonClickListener(GoogleMap.OnMyLocationButtonClickListener listener) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setOnMyLocationButtonClickListener(listener);
        }
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setPadding(left, top, right, bottom);
        }
    }

    @Override
    public void setTrafficEnabled(boolean enabled) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.setTrafficEnabled(enabled);
        }
    }

    @Override
    public void snapshot(GoogleMap.SnapshotReadyCallback callback, Bitmap bitmap) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.snapshot(callback, bitmap);
        }
    }

    @Override
    public void snapshot(GoogleMap.SnapshotReadyCallback callback) {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.snapshot(callback);
        }
    }

    @Override
    public void stopAnimation() {
        GoogleMap map = mMapReference.get();
        if (map != null) {
            map.stopAnimation();
        }
    }

}
