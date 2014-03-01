package au.edu.griffith.caffeinatedmap.markers;

public class CaffeinatedMarkerOptions extends BaseMarkerOptions {

    private static final String KEY_PREFIX = "CMO";
    private static int KEY_NUMBER = 1;

    private final String mKey;

    private String mType;

    public CaffeinatedMarkerOptions() {
        this("");
    }

    public CaffeinatedMarkerOptions(String type) {
        super();
        mKey = KEY_PREFIX + KEY_NUMBER++;
        mType = type;
    }

    public String getKey() {
        return mKey;
    }

    public String getType() {
        return mType;
    }

    public void type(String type) {
        mType = type;
    }

}
