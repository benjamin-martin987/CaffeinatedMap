package au.edu.griffith.caffeinatedmap;

import com.google.android.gms.maps.GoogleMap;

import java.lang.ref.WeakReference;

public class CaffeinatedMap extends BaseCaffeinatedMap {

    public CaffeinatedMap(WeakReference<GoogleMap> mapReference) {
        super(mapReference);
    }

}
