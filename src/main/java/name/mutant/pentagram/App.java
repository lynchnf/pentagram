package name.mutant.pentagram;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JApplet;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import name.mutant.pentagram.geom.MyLine;
import name.mutant.pentagram.geom.MyPoint;
import name.mutant.pentagram.geom.MySegment;
import name.mutant.pentagram.swing.MyApplet;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final int DELAY_MILLIS = 500;
    private static final int HEIGHT = 600; // Pixals
    private static final int WIDTH = 800; // Pixals
    private static final int MARGIN = 10; // Pixals
    private static final int RADIUS = 100;// Meters
    private static final int PATH_WIDTH = 2; // Meters

    public static void main(String[] args) {
        logger.debug("Starting App");

        List<MyPoint> points = new ArrayList<>();
        List<MyLine> lines = new ArrayList<>();
        List<MySegment> segments = new ArrayList<>();

        // Five main points.
        for (int i = 0; i < 5; i++) {
            MyPoint point = MyPoint.polar(RADIUS, 90 - i * 72);
            points.add(point);
        }

        // Lines.
        for (int i = 0; i < 5; i++) {
            int j = (i + 1) % 5;
            int k = (i + 2) % 5;
            MyPoint a = points.get(i);
            MyPoint b = points.get(j);
            MyPoint c = points.get(k);
            lines.add(MyLine.twoPoints(a, b));
            lines.add(MyLine.twoPoints(a, c));
            segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), b.x(), b.y()));
            segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), c.x(), c.y()));
        }

        // Secondary points.
        for (int i = 0; i < 5; i++) {
            int j = (i + 4) % 5;
            int ii = i * 2 + 1;
            int jj = j * 2 + 1;
            MyLine ab = lines.get(ii);
            MyLine cd = lines.get(jj);
            points.add(MyPoint.intersection(ab, cd));
        }

        // Secondary lines.
        for (int i = 0; i < 5; i++) {
            int j = (i + 2) % 5;
            int ii = i + 5;
            int jj = j + 5;
            MyPoint a = points.get(ii);
            MyPoint b = points.get(jj);
            lines.add(MyLine.twoPoints(a, b));
            // segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), b.x(), b.y()));
        }

        // Tertiary points.
        for (int i = 0; i < 5; i++) {
            int k = (i + 3) % 5;
            int ii = i * 2;
            int jj = i + 10;
            int kk = k + 10;
            MyLine ab = lines.get(ii);
            MyLine cd = lines.get(jj);
            MyLine ef = lines.get(kk);
            points.add(MyPoint.intersection(ab, cd));
            points.add(MyPoint.intersection(ab, ef));
        }

        // Tertiary lines.
        for (int i = 0; i < 5; i++) {
            int j = (i + 4) % 5;
            int k = (i + 3) % 5;
            int ii = i * 2 + 10;
            int jj = j * 2 + 11;
            int kk = k * 2 + 11;
            int ll = i + 5;
            int mm = i * 2 + 11;
            MyPoint a = points.get(ii);
            MyPoint b = points.get(jj);
            MyPoint c = points.get(kk);
            MyPoint d = points.get(ll);
            MyPoint e = points.get(mm);
            segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), b.x(), b.y()));
            segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), c.x(), c.y()));
            lines.add(MyLine.twoPoints(a, c));
            segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), d.x(), d.y()));
            segments.add(new MySegment(PATH_WIDTH, d.x(), d.y(), e.x(), e.y()));
        }

        // Quaternary points.
        for (int i = 0; i < 5; i++) {
            int k = (i + 4) % 5;
            int l = (i + 2) % 5;
            int ii = i * 2 + 1;
            int jj = i + 15;
            int kk = k * 2 + 1;
            int ll = l + 15;
            MyLine ab = lines.get(ii);
            MyLine cd = lines.get(jj);
            MyLine ef = lines.get(kk);
            MyLine gh = lines.get(ll);
            points.add(MyPoint.intersection(ab, cd));
            points.add(MyPoint.intersection(ef, gh));
        }

        // Last segments.
        for (int i = 0; i < 5; i++) {
            int ii = i * 2 + 20;
            int jj = i * 2 + 21;
            int kk = i * 2 + 10;
            int ll = i * 2 + 11;
            MyPoint a = points.get(ii);
            MyPoint b = points.get(jj);
            MyPoint c = points.get(kk);
            MyPoint d = points.get(ll);
            segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), b.x(), b.y()));
            segments.add(new MySegment(PATH_WIDTH, a.x(), a.y(), d.x(), d.y()));
            segments.add(new MySegment(PATH_WIDTH, b.x(), b.y(), c.x(), c.y()));
        }

        JFrame f = new JFrame("My Demo");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JApplet applet = new MyApplet(DELAY_MILLIS, MARGIN, RADIUS, segments);
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(HEIGHT, WIDTH));
        f.setVisible(true);

        logger.debug("Finished App");
    }
}
