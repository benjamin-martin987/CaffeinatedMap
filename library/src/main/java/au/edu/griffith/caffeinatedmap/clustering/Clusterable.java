package au.edu.griffith.caffeinatedmap.clustering;

public abstract class Clusterable extends MapPosition {

    private static final String ID_PREFIX = "M";
    private static int ID_NUMBER = 1;

    private String mType;

    protected Clusterable() {
        this("");
    }

    protected Clusterable(String type) {
        super(ID_PREFIX + ID_NUMBER++);
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
