package name.mutant.pentagram.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import javax.swing.JApplet;
import name.mutant.pentagram.geom.MySegment;

public class MyApplet extends JApplet {
    private final static Color bg = Color.white;
    private final static Color fg = Color.black;
    private final static double LITTLE_CIRCLE_RADIUS = 5;
    private int delayMillis;
    private int margin;
    private int radius;
    private List<MySegment> segments;

    public MyApplet(int delayMillis, int margin, int radius, List<MySegment> segments) throws HeadlessException {
        this.delayMillis = delayMillis;
        this.margin = margin;
        this.radius = radius;
        this.segments = segments;
    }

    @Override
    public void init() {
        // Initialize drawing colors
        setBackground(bg);
        setForeground(fg);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();
        double drawLeft = margin;
        double drawTop = margin;
        double drawRadius;
        if (d.height > d.width) {
            drawTop = (d.height - d.width) / 2 + margin;
            drawRadius = d.width / 2 - margin;
        } else {
            drawLeft = (d.width - d.height) / 2 + margin;
            drawRadius = d.height / 2 - margin;
        }
        Ellipse2D.Double circle = new Ellipse2D.Double(drawLeft, drawTop, drawRadius * 2, drawRadius * 2);
        g2.draw(circle);

        for (MySegment segment : segments) {
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                throw new RuntimeException("Sleep failed", e);
            }
            double x1 = drawLeft + drawRadius + segment.x1() * drawRadius / radius;
            double y1 = drawTop + drawRadius - segment.y1() * drawRadius / radius;
            double x2 = drawLeft + drawRadius + segment.x2() * drawRadius / radius;
            double y2 = drawTop + drawRadius - segment.y2() * drawRadius / radius;
            float lineWidth = (float) (segment.width() * drawRadius / radius);
            Stroke stroke = new BasicStroke(lineWidth);
            g2.setStroke(stroke);
            Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
            g2.draw(line);
        }
    }
}
