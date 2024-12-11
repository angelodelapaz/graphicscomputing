
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class Quiz2 extends Frame {

    public void drawSimpleCoordinateSystem(int x, int y, Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, 0, x, 0);
        g2d.drawLine(0, 0, 0, y);
        g2d.drawLine(0, 0, -x, 0);
        g2d.drawLine(0, 0, 0, -y);
        for (int i = 0; i < x; i += 20) {
            g2d.drawLine(i, -5, i, 5);
        }
        for (int i = 0; i < y; i += 20) {
            g2d.drawLine(-5, i, 5, i);
        }
        for (int i = 0; i > -x; i -= 20) {
            g2d.drawLine(i, -5, i, 5);
        }
        for (int i = 0; i > -y; i -= 20) {
            g2d.drawLine(-5, i, 5, i);
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // cast parameter g to Graphics2D class
        // affine transform to invert x-axis
        AffineTransform yUp = new AffineTransform();
        yUp.setToScale(1, -1);
        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(40, 80);
        yUp.preConcatenate(translate);
        g2d.transform(yUp);
        // Create a grid
        g2d.setStroke(new BasicStroke(1.0f));

        //Draw a coordinate system using an external method
        drawSimpleCoordinateSystem(1000, 1000, g2d);

        GeneralPath gp = new GeneralPath();
        gp.moveTo(60, 120);
        gp.lineTo(80, 120); 
        gp.quadTo(90, 140, 100, 120); 
        gp.lineTo(160, 120); 
        gp.quadTo(170, 140, 180, 120);
        gp.lineTo(200, 120); //rear underbody
        gp.curveTo(195, 100, 200, 80, 160, 80); //rear
        gp.lineTo(110, 80); //roof
        gp.lineTo(90, 100); //windscreen
        gp.lineTo(60, 100); //bonnet
        gp.lineTo(60, 120); //front
        g2d.draw(gp);
// Set stroke to broken lines
        g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 8.0f,
                new float[]{50.0f, 10.0f}, 4.0f));
//a. Scaling (Sx = Sy = 1/3) - Color: blue
        g2d.setColor(Color.BLUE);
        AffineTransform scale1 = new AffineTransform();
        scale1.setToScale(1.0 / 3, 1.0 / 3);
        g2d.draw(scale1.createTransformedShape(gp));
        //b. Scaling (Sx = 2, Sy = 1) - Color: yellow
        g2d.setColor(Color.YELLOW);
        AffineTransform scale2 = new AffineTransform();
        scale2.setToScale(2, 1);
        g2d.draw(scale2.createTransformedShape(gp));
    }

    public static void main(String[] args) {
        Frame frame = new Quiz2();
        frame.setTitle("Quiz 2: Scaling");
        frame.setSize(400, 400);
        frame.setBackground(Color.WHITE);
        frame.setForeground(Color.BLACK);
        frame.setVisible(true);
    }
}
