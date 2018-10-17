package name.mutant.pentagram;

public class MyUtil {
    private static final long ROUND_TO = 10000;
    private static final double THRESHOLD = 0.001;

    public static double round(double value) {
        double multUp = value * ROUND_TO;
        double round = Math.round(multUp);
        double down = round / ROUND_TO;
        return down;
    }

    public static boolean almostEqual(double value1, double value2) {
        double diff = value1 - value2;
        double abs = Math.abs(diff);
        return abs < THRESHOLD;
    }
}
