import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class MP2_DelaPaz extends Frame {

    private void QuadraticBezier(Graphics2D g2d, int x0, int y0, int x1, int y1, int x2, int y2) {
        QuadCurve2D q = new QuadCurve2D.Float();
        q.setCurve(x0, y0, x1, y1, x2, y2);
        g2d.draw(q);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set the stroke for drawing
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.BLACK);

        // Draw the cross using lines and quadratic Bezier curves
        // Upper Curve
        g2d.drawLine(250, 280, 250, 180);
        g2d.drawLine(350, 180, 350, 280);
        g2d.drawLine(290, 140, 310, 140);
        QuadraticBezier(g2d, 250, 180, 250, 140, 290, 140);
        QuadraticBezier(g2d, 310, 140, 350, 140, 350, 180);

        // Right Curve
        g2d.drawLine(350, 280, 450, 280);
        g2d.drawLine(490, 320, 490, 340);
        g2d.drawLine(350, 380, 450, 380);
        QuadraticBezier(g2d, 450, 280, 490, 280, 490, 320);
        QuadraticBezier(g2d, 490, 340, 490, 380, 450, 380);
        
        // Bottom Curve
        g2d.drawLine(250, 380, 250, 480);
        g2d.drawLine(290, 520, 310, 520);
        g2d.drawLine(350, 380, 350, 480);
        QuadraticBezier(g2d, 250, 480, 250, 520, 290, 520);
        QuadraticBezier(g2d, 310, 520, 350, 520, 350, 480);
        
        // Left Curve
        g2d.drawLine(250, 280, 150, 280);
        g2d.drawLine(110, 320, 110, 340);
        g2d.drawLine(250, 380, 150, 380);
        QuadraticBezier(g2d, 150, 280, 110, 280, 110, 320);
        QuadraticBezier(g2d, 110, 340, 110, 380, 150, 380);

    }

    // Main Method

    public static void main(String[] args) {
        Frame frame = new MP2_DelaPaz();
        frame.setTitle("MP2_Dela Paz: Cross");
        frame.setSize(600, 600);
        frame.setBackground(Color.WHITE);
        frame.setForeground(Color.BLACK);
        frame.setVisible(true);
    }
}
