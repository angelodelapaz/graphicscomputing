import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

public class Tree extends Frame {

    public void paint (Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(10.0f);
        g2d.setStroke(bs);

        GeneralPath base = new GeneralPath();
        base.moveTo(300, 550);
        base.lineTo(315, 550);
        base.lineTo(315, 580);
        base.lineTo(285, 580);
        base.lineTo(285, 550);
        base.lineTo(300, 550);
        g2d.setPaint(Color.GRAY);
        g2d.fill(base);
        g2d.draw(base);

        GeneralPath tree = new GeneralPath();
        tree.moveTo(300, 100);
        tree.lineTo(250, 250);
        tree.lineTo(270, 250);
        tree.lineTo(200, 400);
        tree.lineTo(220, 400);
        tree.lineTo(150, 550);
        tree.lineTo(300, 550);
        tree.moveTo(300, 100);
        tree.lineTo(350, 250);
        tree.lineTo(330, 250);
        tree.lineTo(400, 400);
        tree.lineTo(380, 400);
        tree.lineTo(450, 550);
        tree.lineTo(300, 550);
        g2d.setPaint(Color.GREEN);
        g2d.fill(tree);
        g2d.draw(tree);

        
        GeneralPath star = new GeneralPath();
        star.moveTo(300, 90);
        star.lineTo(280, 70);
        star.lineTo(300, 50);
        star.lineTo(320, 70);
        star.lineTo(300, 90);
        g2d.setPaint(Color.ORANGE);
        g2d.fill(star);
        g2d.draw(star);

        

        // Define the cross shape
        GeneralPath cross = new GeneralPath();

        // Start
        cross.moveTo(250, 280); // A

        // Left Arm
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

        // Top Arm
        cross.lineTo(350, 180);
        cross.quadTo(350, 140, 310, 140);
        cross.lineTo(290, 140);
        cross.quadTo(250, 140, 250, 180);
        cross.lineTo(250, 280);

        // Convert Tree to Area
        Area treeArea = new Area(base);
        treeArea.add(new Area(tree));
        treeArea.add(new Area(star));

        // Convert cross to Area
        Area crossArea = new Area(cross);

        // Perform the union operation
        Area unionArea = new Area(treeArea);
        unionArea.add(crossArea);


    }

    

    public static void main (String[] args){
        Frame frame = new Tree();
        frame.setTitle("MP2: Tree");
        frame.setSize(600, 600);
        frame.setBackground(Color.WHITE);
        frame.setForeground(Color.BLACK);
        frame.setVisible(true);
    }
}


class Cross extends Frame {

   
    public void paintCross(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(10.0f);
        g2d.setStroke(bs);

        GeneralPath cross = new GeneralPath();

        // Start
        cross.moveTo(250, 280); // A

        // Left Arm
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

        // Top Arm
        cross.lineTo(350, 180);
        cross.quadTo(350, 140, 310, 140);
        cross.lineTo(290, 140);
        cross.quadTo(250, 140, 250, 180);
        cross.lineTo(250, 280);

        g2d.draw(cross);
    }
}


class GeometricOperations{

}


