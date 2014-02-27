package au.edu.griffith.caffeinatedmap.utils;

public class Distance {

    public static double getHypotenuse(double x1, double y1, double x2, double y2) {
        double x = getAbsolute(x1 - x2);
        double y = getAbsolute(y1 - y2);
        return Math.sqrt(x * x + y * y);
    }

    private static double getAbsolute(double value) {
        return (value <= 0.0) ? 0.0 - value : value;
    }

}
