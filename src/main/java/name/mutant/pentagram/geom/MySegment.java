package name.mutant.pentagram.geom;

import static name.mutant.pentagram.MyUtil.round;

public class MySegment {
    private double width;
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public MySegment(double width, double x1, double y1, double x2, double y2) {
        this.width = round(width);
        this.x1 = round(x1);
        this.y1 = round(y1);
        this.x2 = round(x2);
        this.y2 = round(y2);
    }

    public double width() {
        return width;
    }

    public double x1() {
        return x1;
    }

    public double y1() {
        return y1;
    }

    public double x2() {
        return x2;
    }

    public double y2() {
        return y2;
    }

    public double degrees() {
        double x = x1 - x2;
        double y = y1 - y2;
        double rads = Math.atan2(y, x);
        double degrees = Math.toDegrees(rads);
        return round((degrees + 360) % 180);
    }

    @Override
    public String toString() {
        return "MySegment{" + "x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 + '}';
    }
}
