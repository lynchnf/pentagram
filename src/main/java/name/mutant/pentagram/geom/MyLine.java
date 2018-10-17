package name.mutant.pentagram.geom;

import static name.mutant.pentagram.MyUtil.almostEqual;
import static name.mutant.pentagram.MyUtil.round;

import java.util.Objects;

public class MyLine {
    private double degrees;
    private double intercept;

    private MyLine(double degrees, double intercept) {
        this.degrees = round((degrees + 360) % 180);
        this.intercept = round(intercept);
    }

    public static MyLine slopeIntercept(double slope, int intercept) {
        double rads = Math.atan(slope);
        double degrees = Math.toDegrees(rads);
        return new MyLine(degrees, intercept);
    }

    public static MyLine twoPoints(MyPoint a, MyPoint b) {
        double x = a.x() - b.x();
        double y = a.y() - b.y();
        double rads = Math.atan2(y, x);
        double degrees = Math.toDegrees(rads);

        // If 90 degrees, line is vertical and intercept will be the X intercept;
        if (isVertical(degrees)) {
            double intercept = a.x();
            return new MyLine(degrees, intercept);
        } else {
            double slope = Math.tan(rads);
            double intercept = a.y() - a.x() * slope;
            return new MyLine(degrees, intercept);
        }
    }

    private static boolean isVertical(double degrees) {
        return almostEqual(degrees, 90);
    }

    public boolean isParallel(MyLine line) {
        return almostEqual(degrees, line.degrees());
    }

    public boolean isVertical() {
        return isVertical(degrees);
    }

    public double degrees() {
        return degrees;
    }

    public double intercept() {
        return intercept;
    }

    public double slope() {
        if (isVertical()) throw new RuntimeException("Cannot get slope of vertical line.");
        double rads = Math.toRadians(degrees);
        return Math.tan(rads);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLine line = (MyLine) o;
        return almostEqual(line.degrees, degrees) && almostEqual(line.intercept, intercept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degrees, intercept);
    }

    @Override
    public String toString() {
        return "MyLine{" + "intercept=" + intercept + ", degrees=" + degrees + '}';
    }
}
