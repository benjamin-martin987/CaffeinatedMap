package au.edu.griffith.caffeinatedmap.clustering;

import java.util.HashMap;

public class InClusterTypeCounter {

    private static final String UNKNOWN_TYPE = "Unknown";

    private HashMap<String, Integer> mTypeCounter;

    public InClusterTypeCounter() {
        mTypeCounter = new HashMap<String, Integer>();
    }

    void incrementType(String type) {
        type = (type != null && !type.equals("")) ? type : UNKNOWN_TYPE;
        mTypeCounter.put(type, (mTypeCounter.containsKey(type)) ? mTypeCounter.get(type) + 1 : 1);
    }

    public int getType(String type) {
        return (mTypeCounter.containsKey(type)) ? mTypeCounter.get(type) : 0;
    }

    public String[] getListOfTypes() {
        String[] myTypes = new String[mTypeCounter.size()];
        return mTypeCounter.keySet().toArray(myTypes);
    }

    public boolean contains(String type) {
        return mTypeCounter.containsKey(type);
    }

}
