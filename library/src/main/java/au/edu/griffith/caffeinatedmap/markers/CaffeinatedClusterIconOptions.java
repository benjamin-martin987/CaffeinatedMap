package au.edu.griffith.caffeinatedmap.markers;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import au.edu.griffith.caffeinatedmap.R;

public class CaffeinatedClusterIconOptions {

    private static final int DEFAULT_DIVIDER_DEGREE_SIZE = 5;

    private Context mContext;
    float size;
    float innerSize;
    int baseColour;
    int innerColour;
    int textColour;
    int dividerDegreeSize = DEFAULT_DIVIDER_DEGREE_SIZE;

    public CaffeinatedClusterIconOptions(Context context) {
        mContext = context;
        Resources resources = mContext.getResources();
        size = resources.getDimensionPixelSize(R.dimen.default_cluster_size);
        innerSize = size / 100 * 80;
        baseColour = resources.getColor(R.color.black);
        innerColour = resources.getColor(R.color.white);
        textColour = resources.getColor(R.color.black);
    }

    public float getSizePX() {
        return size;
    }

    public float getSizeDP() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, mContext.getResources().getDisplayMetrics());
    }

    public void setSizeDP(float size) {
        float density = mContext.getResources().getDisplayMetrics().density;
        this.size = Math.round(size * density);
    }

    public int getBaseColour() {
        return baseColour;
    }

    public void setBaseColour(int baseColour) {
        this.baseColour = baseColour;
    }

    public float getInnerSizePX() {
        return innerSize;
    }

    public float getInnerSizeDP() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, innerSize, mContext.getResources().getDisplayMetrics());
    }

    public void setInnerSizeDP(float innerSize) {
        float density = mContext.getResources().getDisplayMetrics().density;
        this.innerSize = Math.round(innerSize * density);
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
