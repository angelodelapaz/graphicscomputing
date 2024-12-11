
import java.awt.*;
import java.awt.geom.GeneralPath;

public class MP2_Cross extends Frame {

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(10.0f);
        g2d.setStroke(bs);

        GeneralPath cross = new GeneralPath();

        // Start
        cross.moveTo(250, 280); // A

        // 
        cross.lineTo(150, 280); 
        cross.quadTo(110, 280, 110, 320); 
        cross.lineTo(110, 340); 
        cross.quadTo(110, 380, 150, 380); 
        cross.lineTo(250, 380); 

        // Bottom Arm
        cross.lineTo(250, 480); 
        cross.quadTo(250, 520, 290, 520); 
        cross.lineTo(310, 520); 
        cross.quadTo(350, 520, 350, 480); 
        cross.lineTo(350, 380); 

        // Right Arm
        cross.lineTo(450, 380); 
        cross.quadTo(490, 380, 490, 340); 
        cross.lineTo(490, 320);
        cross.quadTo(490, 280, 450, 280); 
        cross.lineTo(350, 280); 

       
        cross.lineTo(350, 180);
        cross.quadTo(350, 140, 310, 140);
        cross.lineTo(290, 140);
        cross.quadTo(250, 140, 250, 180); 
        cross.lineTo(250, 280); 

        g2d.draw(cross);

    }

    public static void main(String[] args) {
        Frame frame = new MP2_Cross();
        frame.setTitle("MP2: Cross");
        frame.setSize(600, 600);
        frame.setBackground(Color.WHITE);
        frame.setForeground(Color.BLACK);
        frame.setVisible(true);
    }
}
