package name.mutant.pentagram.geom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyLineTest {
    @Test
    public void twoPoints1() {
        MyPoint p1 = MyPoint.cartesian(1, 0);
        MyPoint p2 = MyPoint.cartesian(0, 1);
        MyLine line = MyLine.twoPoints(p1, p2);
        assertEquals(-1, line.slope(), 0.01);
        assertEquals(1, line.intercept(), 0.01);
    }

    @Test
    public void twoPoints2() {
        MyPoint p1 = MyPoint.cartesian(2, 3);
        MyPoint p2 = MyPoint.cartesian(4, 5);
        MyLine line = MyLine.twoPoints(p1, p2);
        assertEquals(1, line.slope(), 0.01);
        assertEquals(1, line.intercept(), 0.01);
    }

    @Test
    public void twoPoints3() {
        MyPoint p1 = MyPoint.cartesian(3, 0);
        MyPoint p2 = MyPoint.cartesian(-1, -2);
        MyLine line = MyLine.twoPoints(p1, p2);
        assertEquals(0.5, line.slope(), 0.01);
        assertEquals(-1.5, line.intercept(), 0.01);
    }
}
