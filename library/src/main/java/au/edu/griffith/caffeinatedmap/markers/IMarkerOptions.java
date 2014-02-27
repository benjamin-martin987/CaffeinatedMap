package au.edu.griffith.caffeinatedmap.markers;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

interface IMarkerOptions {

    MarkerOptions alpha(float alpha);

    MarkerOptions anchor(float u, float v);

    MarkerOptions draggable(boolean draggable);

    MarkerOptions flat(boolean flat);

    float getAlpha();

    float getAnchorU();

    float getAnchorV();

    BitmapDescriptor getIcon();

    float getInfoWindowAnchorU();

    float getInfoWindowAnchorV();

    LatLng getPosition();

    float getRotation();

    String getSnippet();

    String getTitle();

    MarkerOptions icon(BitmapDescriptor icon);

    MarkerOptions infoWindowAnchor(float u, float v);

    boolean isDraggable();

    boolean isFlat();

    boolean isVisible();

    MarkerOptions position(LatLng position);

    MarkerOptions rotation(float rotation);

    MarkerOptions snippet(String snippet);

    MarkerOptions title(String title);

    MarkerOptions visible(boolean visible);

}
