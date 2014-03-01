package au.edu.griffith.caffeinatedmap.markers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class CaffeinatedClusterIcon extends View {

    private float[] mDegreeValues;
    private int[] mDegreeColours;
    private CaffeinatedClusterIconOptions mOptions;
    private Paint mPaint;
    private RectF mRectF;
    private int mNumber;
    private int mNumberOfSeparators;
    private boolean mUsingTypeCount = false;

    public CaffeinatedClusterIcon(Context context, CaffeinatedClusterIconOptions options, int number) {
        super(context);
        mOptions = (options != null) ? options : new CaffeinatedClusterIconOptions(context);
        mNumber = number;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public CaffeinatedClusterIcon(Context context, CaffeinatedClusterIconOptions options, int[] typeCount, int[] colours) {
        this(context, options, 0);
        if (typeCount != null) {
            mRectF = new RectF(0, 0, mOptions.size, mOptions.size);

            calculateDegreeValues(typeCount);
            mDegreeColours = new int[mDegreeValues.length];
            for (int i = 0; i < mDegreeColours.length; i++) {
                mDegreeColours[i] = (i < colours.length) ? colours[i] : mOptions.baseColour;
            }

            mUsingTypeCount = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = mOptions.size / 2;

        mPaint.setColor(mOptions.baseColour);
        canvas.drawCircle(radius, radius, radius, mPaint);

        if (mUsingTypeCount) {
            int startAngle = 0;
            for (int i = 0; i < mDegreeValues.length; i++) {
                if (mDegreeValues[i] > 0) {
                    mPaint.setColor(mDegreeColours[i]);
                    canvas.drawArc(mRectF, startAngle, mDegreeValues[i], true, mPaint);
                    if (mNumberOfSeparators > 1) {
                        startAngle += ((int) mDegreeValues[i]) + mOptions.dividerDegreeSize;
                    }
                }
            }
        }

        mPaint.setColor(mOptions.innerColour);
        float innerRadius = mOptions.innerSize / 2;
        float innerSize = (radius > innerRadius) ? innerRadius : (radius / 5 * 4);
        canvas.drawCircle(radius, radius, innerSize, mPaint);

        mPaint.setColor(mOptions.textColour);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize((mNumber <= 990) ? (mOptions.size / 3) : (mOptions.size / 4));
        float textYOffset = (mPaint.descent() + mPaint.ascent()) / 2;
        canvas.drawText(String.valueOf(mNumber), radius, radius - textYOffset, mPaint);
    }

    public Bitmap toBitmap() {
        int iconSize = (int) mOptions.size;
        Bitmap bitmap = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }

    private void calculateDegreeValues(int[] typeCount) {
        mDegreeValues = new float[typeCount.length];

        mNumberOfSeparators = 0;
        mNumber = 0;
        for (int value : typeCount) {
            if (value > 0) {
                mNumber += value;
                mNumberOfSeparators++;
            }
        }

        int availableDegrees = 360 - (((mNumberOfSeparators > 1) ? mNumberOfSeparators : 0) * mOptions.dividerDegreeSize);

        for (int i = 0; i < mDegreeValues.length; i++) {
            mDegreeValues[i] = availableDegrees * (typeCount[i] / (float) mNumber);
            if (mDegreeValues[i] > 0 && mDegreeValues[i] < 1) {
                mDegreeValues[i] = 1;
            }
        }
    }
}
