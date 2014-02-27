package au.edu.griffith.caffeinatedmap.markers;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

abstract class BaseMarkerOptions implements IMarkerOptions {

    protected MarkerOptions mMarkerOptions;

    protected BaseMarkerOptions() {
        mMarkerOptions = new MarkerOptions();
    }

    public MarkerOptions getMarkerOptions() {
        return mMarkerOptions;
    }

    @Override
    public MarkerOptions alpha(float alpha) {
        return mMarkerOptions.alpha(alpha);
    }

    @Override
    public MarkerOptions anchor(float u, float v) {
        return mMarkerOptions.anchor(u, v);
    }

    @Override
    public MarkerOptions draggable(boolean draggable) {
        return mMarkerOptions.draggable(draggable);
    }

    @Override
    public MarkerOptions flat(boolean flat) {
        return mMarkerOptions.flat(flat);
    }

    @Override
    public float getAlpha() {
        return mMarkerOptions.getAlpha();
    }

    @Override
    public float getAnchorU() {
        return mMarkerOptions.getAnchorU();
    }

    @Override
    public float getAnchorV() {
        return mMarkerOptions.getAnchorV();
    }

    @Override
    public BitmapDescriptor getIcon() {
        return mMarkerOptions.getIcon();
    }

    @Override
    public float getInfoWindowAnchorU() {
        return mMarkerOptions.getInfoWindowAnchorU();
    }

    @Override
    public float getInfoWindowAnchorV() {
        return mMarkerOptions.getInfoWindowAnchorV();
    }

    @Override
    public LatLng getPosition() {
        return mMarkerOptions.getPosition();
    }

    @Override
    public float getRotation() {
        return mMarkerOptions.getRotation();
    }

    @Override
    public String getSnippet() {
        return mMarkerOptions.getSnippet();
    }

    @Override
    public String getTitle() {
        return mMarkerOptions.getTitle();
    }

    @Override
    public MarkerOptions icon(BitmapDescriptor icon) {
        return mMarkerOptions.icon(icon);
    }

    @Override
    public MarkerOptions infoWindowAnchor(float u, float v) {
        return mMarkerOptions.infoWindowAnchor(u, v);
    }

    @Override
    public boolean isDraggable() {
        return mMarkerOptions.isDraggable();
    }

    @Override
    public boolean isFlat() {
        return mMarkerOptions.isFlat();
    }

    @Override
    public boolean isVisible() {
        return mMarkerOptions.isVisible();
    }

    @Override
    public MarkerOptions position(LatLng position) {
        return mMarkerOptions.position(position);
    }

    @Override
    public MarkerOptions rotation(float rotation) {
        return mMarkerOptions.rotation(rotation);
    }

    @Override
    public MarkerOptions snippet(String snippet) {
        return mMarkerOptions.snippet(snippet);
    }

    @Override
    public MarkerOptions title(String title) {
        return mMarkerOptions.title(title);
    }

    @Override
    public MarkerOptions visible(boolean visible) {
        return mMarkerOptions.visible(visible);
    }
}
