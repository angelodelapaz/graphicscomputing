
import java.awt.*;
import java.awt.geom.*;

public class object_java extends Frame {

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke bs = new BasicStroke(5.0f);
        g2d.setStroke(bs);
        GeneralPath tshirt = new GeneralPath();

        tshirt.moveTo(100, 250);
        tshirt.lineTo(150, 250);
        tshirt.lineTo(150, 200);
        tshirt.lineTo(200, 200);
        tshirt.lineTo(200, 500);
        tshirt.lineTo(400, 500);
        tshirt.lineTo(400, 200);
        tshirt.lineTo(450, 200);
        tshirt.lineTo(450, 250);
        tshirt.lineTo(500, 250);
        tshirt.lineTo(500, 150);
        tshirt.lineTo(450, 100);
        tshirt.lineTo(150, 100);
        tshirt.lineTo(100, 150);
        tshirt.lineTo(100, 250);
        tshirt.closePath();

        //    g2d.setPaint(Color.MAGENTA);
        //    g2d.fill(tshirt);


        // Boat Base
        GeneralPath boatBase = new GeneralPath();
        boatBase.moveTo(100, 350);
        boatBase.lineTo(150, 450);
        boatBase.lineTo(450, 450);
        boatBase.lineTo(500, 350);
        boatBase.lineTo(100, 350);

        // Boat Flag
        GeneralPath boatFlag = new GeneralPath();
        boatFlag.moveTo(305, 150);
        boatFlag.lineTo(305, 200);
        boatFlag.lineTo(405, 200);

        // Boat Pole
        GeneralPath boatPole = new GeneralPath();
        boatPole.moveTo(295, 350);
        boatPole.lineTo(295, 150);
        boatPole.lineTo(305, 150);
        boatPole.lineTo(305, 350);

        // Boat Color
        g2d.setPaint(Color.GREEN);
        g2d.fill(boatBase);
        g2d.fill(boatPole);
        g2d.fill(boatFlag);

        // Creating Tshirt Area
        Area tshirtArea = new Area(tshirt);
        
    //     // // Creating Area for Boat Components
    //     Area poleArea = new Area(boatPole);
    //     Area baseArea = new Area(boatBase);
    //     Area flagArea = new Area(boatFlag);
    //     Area boatArea = new Area();
    //     boatArea.add(baseArea);
    //     boatArea.add(poleArea);
    //     boatArea.add(flagArea);


    //     // Relative Difference of tshirt and Boat
    //     // Area relative = new Area(tshirtArea);
    //     // relative.subtract(boatArea);
    //     // g2d.setPaint(Color.YELLOW);
    //     // g2d.draw(relative);
    //     // g2d.setPaint(Color.BLUE);
    //     // g2d.fill(relative);

    //     // Symmetric Difference 
    //    Area symmetric = new Area(tshirtArea);
    //    symmetric.exclusiveOr(boatArea);
       
    //    g2d.setPaint(Color.RED);
    //    g2d.draw(symmetric);
    //    g2d.setPaint(Color.ORANGE);
    //    g2d.fill(symmetric);
    }

    public static void main(String[] args) {
        object_java frame = new object_java();
        frame.setSize(600, 600);
        frame.setTitle("SYMMETRIC DIFFERENCE");
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }
}
