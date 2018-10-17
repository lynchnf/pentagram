package name.mutant.pentagram.geom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyPointTest {
    @Test
    public void polar1() {
        MyPoint p = MyPoint.polar(5, 36.87);
        assertEquals(4, p.x(), 0.01);
        assertEquals(3, p.y(), 0.01);
    }

    @Test
    public void polar2() {
        MyPoint p = MyPoint.polar(5, -36.87);
        assertEquals(4, p.x(), 0.01);
        assertEquals(-3, p.y(), 0.01);
    }

    @Test
    public void polar3() {
        MyPoint p = MyPoint.polar(5, 323.13);
        assertEquals(4, p.x(), 0.01);
        assertEquals(-3, p.y(), 0.01);
    }

    @Test
    public void polar4() {
        MyPoint p = MyPoint.polar(5, 143.13);
        assertEquals(-4, p.x(), 0.01);
        assertEquals(3, p.y(), 0.01);
    }

    @Test
    public void intersection() {
        MyLine line1 = MyLine.slopeIntercept(0.5, 0);
        MyLine line2 = MyLine.slopeIntercept(-2, 5);
        MyPoint p = MyPoint.intersection(line1, line2);
        assertEquals(2, p.x(), 0.01);
        assertEquals(1, p.y(), 0.01);
    }
}
