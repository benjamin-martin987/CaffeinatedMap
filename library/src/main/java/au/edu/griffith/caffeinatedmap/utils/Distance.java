package au.edu.griffith.caffeinatedmap.utils;

public class Distance {

    public static double getHypotenuse(int x1, int y1, int x2, int y2) {
        int x = getAbsolute(x1 - x2);
        int y = getAbsolute(y1 - y2);
        return Math.sqrt(x * x + y * y);
    }

    private static int getAbsolute(int value) {
        return (value <= 0) ? 0 - value : value;
    }

}
