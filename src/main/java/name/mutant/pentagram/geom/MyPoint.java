package name.mutant.pentagram.geom;

import static name.mutant.pentagram.MyUtil.almostEqual;
import static name.mutant.pentagram.MyUtil.round;

import java.util.Objects;

public class MyPoint {
    private double radius;
    private double degrees;

    private MyPoint(double radius, double degrees) {
        this.radius = round(radius);
        this.degrees = round((degrees + 360) % 360);
    }

    public static MyPoint polar(double radius, double degrees) {
        return new MyPoint(radius, degrees);
    }

    public static MyPoint cartesian(double x, double y) {
        double radius = Math.hypot(x, y);
        double rads = Math.atan2(y, x);
        double degrees = Math.toDegrees(rads);
        return new MyPoint(radius, degrees);
    }

    public static MyPoint intersection(MyLine ab, MyLine cd) {
        if (ab.isParallel(cd)) {
            throw new RuntimeException("Lines are parallel");
        }
        double x;
        double y;
        if (ab.isVertical()) {
            x = ab.intercept();
            y = x * cd.slope() + cd.intercept();
        } else if (cd.isVertical()) {
            x = cd.intercept();
            y = x * ab.slope() + ab.intercept();
        } else {
            x = (ab.intercept() - cd.intercept()) / (cd.slope() - ab.slope());
            y = x * ab.slope() + ab.intercept();
        }
        double radius = Math.hypot(x, y);
        double rads = Math.atan2(y, x);
        double degrees = Math.toDegrees(rads);
        return new MyPoint(radius, degrees);
    }

    public double radius() {
        return radius;
    }

    public double degrees() {
        return degrees;
    }

    public double x() {
        double rads = Math.toRadians(degrees);
        return radius * Math.cos(rads);
    }

    public double y() {
        double rads = Math.toRadians(degrees);
        return radius * Math.sin(rads);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPoint point = (MyPoint) o;
        return almostEqual(point.radius, radius) && almostEqual(point.degrees, degrees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, degrees);
    }

    @Override
    public String toString() {
        return "MyPoint{" + "radius=" + radius + ", degrees=" + degrees + ", x=" + round(x()) + ", y=" + round(y())
                + '}';
    }
}
