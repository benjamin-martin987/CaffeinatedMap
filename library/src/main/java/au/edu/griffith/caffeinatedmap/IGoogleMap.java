package au.edu.griffith.caffeinatedmap;

import android.graphics.Bitmap;
import android.view.View;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public interface IGoogleMap {

    int MAP_TYPE_HYBRID = GoogleMap.MAP_TYPE_HYBRID;
    int MAP_TYPE_NONE = GoogleMap.MAP_TYPE_NONE;
    int MAP_TYPE_NORMAL = GoogleMap.MAP_TYPE_NORMAL;
    int MAP_TYPE_SATELLITE = GoogleMap.MAP_TYPE_SATELLITE;
    int MAP_TYPE_TERRAIN = GoogleMap.MAP_TYPE_TERRAIN;

    Circle addCircle(CircleOptions options);

    GroundOverlay addGroundOverlay(GroundOverlayOptions options);

    Marker addMarker(MarkerOptions options);

    Polygon addPolygon(PolygonOptions options);

    Polyline addPolyline(PolylineOptions options);

    TileOverlay addTileOverlay(TileOverlayOptions options);

    void animateCamera(CameraUpdate update, int durationMs, GoogleMap.CancelableCallback callback);

    void animateCamera(CameraUpdate update, GoogleMap.CancelableCallback callback);

    void animateCamera(CameraUpdate update);

    void clear();

    CameraPosition getCameraPosition();

    int getMapType();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    Projection getProjection();

    UiSettings getUiSettings();

    boolean isBuildingsEnabled();

    boolean isIndoorEnabled();

    boolean isMyLocationEnabled();

    boolean isTrafficEnabled();

    void moveCamera(CameraUpdate update);

    void setBuildingsEnabled(boolean enabled);

    boolean setIndoorEnabled(boolean enabled);

    void setInfoWindowAdapter(GoogleMap.InfoWindowAdapter adapter);

    void setLocationSource(LocationSource source);

    void setMapType(int type);

    void setMyLocationEnabled(boolean enabled);

    void setOnCameraChangeListener(GoogleMap.OnCameraChangeListener listener);

    void setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener listener);

    void setOnMapClickListener(GoogleMap.OnMapClickListener listener);

    void setOnMapLoadedCallback(GoogleMap.OnMapLoadedCallback callback);

    void setOnMapLongClickListener(GoogleMap.OnMapLongClickListener listener);

    void setOnMarkerClickListener(GoogleMap.OnMarkerClickListener listener);

    void setOnMarkerDragListener(GoogleMap.OnMarkerDragListener listener);

    void setOnMyLocationButtonClickListener(GoogleMap.OnMyLocationButtonClickListener listener);

    void setPadding(int left, int top, int right, int bottom);

    void setTrafficEnabled(boolean enabled);

    void snapshot(GoogleMap.SnapshotReadyCallback callback, Bitmap bitmap);

    void snapshot(GoogleMap.SnapshotReadyCallback callback);

    void stopAnimation();

    interface CancelableCallback extends GoogleMap.CancelableCallback {
        @Override
        void onCancel();

        @Override
        void onFinish();
    }

    interface InfoWindowAdapter extends GoogleMap.InfoWindowAdapter {
        @Override
        View getInfoContents(Marker marker);

        @Override
        View getInfoWindow(Marker marker);
    }

    interface OnCameraChangeListener extends GoogleMap.OnCameraChangeListener {
        @Override
        void onCameraChange(CameraPosition cameraPosition);
    }

    interface OnInfoWindowClickListener extends GoogleMap.OnInfoWindowClickListener {
        @Override
        void onInfoWindowClick(Marker marker);
    }

    interface OnMapClickListener extends GoogleMap.OnMapClickListener {

        @Override
        void onMapClick(LatLng position);
    }

    interface OnMapLoadedCallback extends GoogleMap.OnMapLoadedCallback {

        @Override
        void onMapLoaded();
    }

    interface OnMapLongClickListener extends GoogleMap.OnMapLongClickListener {

        @Override
        void onMapLongClick(LatLng position);
    }

    interface OnMarkerClickListener extends GoogleMap.OnMarkerClickListener {
        @Override
        boolean onMarkerClick(Marker marker);
    }

    interface OnMarkerDragListener extends GoogleMap.OnMarkerDragListener {
        @Override
        void onMarkerDrag(Marker marker);

        @Override
        void onMarkerDragEnd(Marker marker);

        @Override
        void onMarkerDragStart(Marker marker);
    }

    interface OnMyLocationButtonClickListener extends GoogleMap.OnMyLocationButtonClickListener {
        @Override
        boolean onMyLocationButtonClick();
    }

    interface SnapshotReadyCallback extends GoogleMap.SnapshotReadyCallback {
        @Override
        void onSnapshotReady(Bitmap snapshot);
    }

}
