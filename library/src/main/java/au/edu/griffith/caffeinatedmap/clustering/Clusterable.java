package au.edu.griffith.caffeinatedmap.clustering;

public class Clusterable extends MapPosition {

    private final String mCMOKey;
    private final String mCMOType;

    protected Clusterable(String cmoKey, String cmoType) {
        mCMOKey = cmoKey;
        mCMOType = cmoType;
    }

    public String getCMOKey() {
        return mCMOKey;
    }

    public String getCMOType() {
        return mCMOType;
    }

}
