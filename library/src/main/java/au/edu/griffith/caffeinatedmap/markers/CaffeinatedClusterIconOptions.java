package au.edu.griffith.caffeinatedmap.markers;

import android.content.Context;
import android.content.res.Resources;

import au.edu.griffith.caffeinatedmap.R;

public class CaffeinatedClusterIconOptions {

    private static final int DEFAULT_DIVIDER_DEGREE_SIZE = 5;

    float size;
    float innerSize;
    int baseColour;
    int innerColour;
    int textColour;
    int dividerDegreeSize = DEFAULT_DIVIDER_DEGREE_SIZE;

    public CaffeinatedClusterIconOptions(Context context) {
        Resources resources = context.getResources();
        size = resources.getDimensionPixelSize(R.dimen.default_cluster_size);
        innerSize = size / 100 * 80;
        baseColour = resources.getColor(R.color.black);
        innerColour = resources.getColor(R.color.white);
        textColour = resources.getColor(R.color.black);
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getBaseColour() {
        return baseColour;
    }

    public void setBaseColour(int baseColour) {
        this.baseColour = baseColour;
    }

    public float getInnerSize() {
        return innerSize;
    }

    public void setInnerSize(float innerSize) {
        this.innerSize = innerSize;
    }

    public int getInnerColour() {
        return innerColour;
    }

    public void setInnerColour(int innerColour) {
        this.innerColour = innerColour;
    }

    public int getTextColour() {
        return textColour;
    }

    public void setTextColour(int textColour) {
        this.textColour = textColour;
    }

    public int getDividerDegreeSize() {
        return dividerDegreeSize;
    }

    public void setDividerDegreeSize(int dividerDegreeSize) {
        this.dividerDegreeSize = dividerDegreeSize;
    }

}
