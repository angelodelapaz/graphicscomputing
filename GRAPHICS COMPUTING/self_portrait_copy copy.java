
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class self_portrait_copy_copy extends Frame {

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        GeneralPath outline = new GeneralPath();
        outline.moveTo(0, 937);
        outline.lineTo(600, 937);
        outline.lineTo(600, 470);
        outline.lineTo(565, 470);
        outline.quadTo(520, 447, 496, 462);
        outline.quadTo(496, 462, 462, 440);
        outline.lineTo(410, 440);
        outline.lineTo(391, 432);

        outline.curveTo(391, 432, 333, 635, 293, 891);
        outline.quadTo(293, 891, 260, 870);
        outline.curveTo(260, 870, 233, 668, 142, 508);
        outline.curveTo(142, 508, 143, 464, 163, 415);

        outline.quadTo(143, 424, 124, 435);
        outline.quadTo(95, 471, 58, 500);
        outline.lineTo(0, 530);

        g2d.setColor(Color.BLACK);
        g2d.fill(outline);

        GeneralPath body = new GeneralPath();
        body.moveTo(391, 432);
        body.curveTo(391, 432, 333, 635, 293, 891);
        body.quadTo(293, 891, 260, 870);
        body.curveTo(260, 870, 233, 668, 142, 508);
        body.curveTo(142, 508, 143, 464, 163, 415);

        g2d.setColor(new Color(180, 138, 120));
        g2d.fill(body);

        GeneralPath head = new GeneralPath();
        head.moveTo(163, 415);
        head.quadTo(173, 371, 165, 358);
        head.quadTo(160, 313, 157, 269);

        //Hairline
        head.quadTo(156, 241, 163, 231);
        head.quadTo(195, 189, 199, 171);
        head.quadTo(209, 165, 230, 163);
        head.curveTo(230, 163, 238, 177, 270, 181);
        head.curveTo(270, 181, 293, 172, 323, 177);
        head.quadTo(342, 174, 360, 177);
        head.quadTo(368, 167, 380, 167);
        head.curveTo(380, 167, 392, 189, 416, 205);
        head.quadTo(416, 205, 419, 194);
        head.quadTo(430, 221, 441, 281);

        head.quadTo(438, 329, 425, 370);
        head.lineTo(391, 432);

        g2d.fill(head);

        GeneralPath leftEar = new GeneralPath();
        leftEar.moveTo(133, 263);
        leftEar.quadTo(118, 282, 128, 298);
        leftEar.quadTo(139, 312, 141, 335);
        leftEar.quadTo(144, 356, 165, 357);
        leftEar.quadTo(173, 371, 157, 254);

        g2d.fill(leftEar);

        GeneralPath rightEar = new GeneralPath();
        rightEar.moveTo(456, 275);
        rightEar.quadTo(471, 285, 463, 308);
        rightEar.quadTo(453, 322, 451, 345);
        rightEar.quadTo(448, 356, 425, 357);
        rightEar.quadTo(438, 329, 440, 278);
        g2d.fill(rightEar);

        GeneralPath hair = new GeneralPath();
        hair.moveTo(162, 241);
        hair.quadTo(156, 241, 163, 231);
        hair.quadTo(195, 189, 199, 171);
        hair.quadTo(209, 165, 230, 163);
        hair.curveTo(230, 163, 238, 177, 270, 181);
        hair.curveTo(270, 181, 293, 172, 323, 177);
        hair.quadTo(342, 174, 360, 177);
        hair.quadTo(368, 167, 380, 167);
        hair.curveTo(380, 167, 392, 189, 416, 205);
        hair.quadTo(416, 205, 419, 194);
        hair.quadTo(430, 221, 441, 281);

        hair.lineTo(456, 273);
        hair.quadTo(448, 268, 453, 261);
        hair.quadTo(478, 151, 461, 134);
        hair.quadTo(399, 37, 338, 49);
        hair.quadTo(218, 43, 142, 126);
        hair.quadTo(131, 173, 126, 207);
        hair.quadTo(141, 251, 132, 264);
        hair.quadTo(141, 263, 148, 271);
        hair.lineTo(159, 276);
        hair.closePath();

        g2d.setColor(Color.BLACK);
        g2d.fill(hair);

        GeneralPath leftEyebrow = new GeneralPath();
        leftEyebrow.moveTo(216, 213);
        leftEyebrow.quadTo(263, 206, 286, 216);
        leftEyebrow.quadTo(293, 232, 273, 221);
        leftEyebrow.quadTo(240, 219, 209, 221);

        g2d.fill(leftEyebrow);

        GeneralPath rightEyebrow = new GeneralPath();
        rightEyebrow.moveTo(363, 216);
        rightEyebrow.quadTo(410, 219, 413, 219);
        rightEyebrow.curveTo(413, 219, 419, 227, 408, 230);
        rightEyebrow.quadTo(387, 222, 356, 224);

        g2d.fill(rightEyebrow);

        GeneralPath leftEye = new GeneralPath();
        leftEye.moveTo(212, 254);
        leftEye.quadTo(243, 240, 273, 261);
        leftEye.quadTo(270, 267, 269, 262);
        leftEye.quadTo(243, 249, 218, 256);
        leftEye.closePath();
        g2d.fill(leftEye);

        Ellipse2D leftEye2 = new Ellipse2D.Double(235, 248, 22, 23);

        g2d.fill(leftEye2);

        GeneralPath rightEye = new GeneralPath();
        rightEye.moveTo(349, 259);
        rightEye.quadTo(370, 243, 410, 260);
        rightEye.quadTo(380, 252, 355, 261);
        rightEye.closePath();
        g2d.fill(rightEye);

        Ellipse2D rightEyeEllipse = new Ellipse2D.Double(360, 250, 20, 21);
        g2d.fill(rightEyeEllipse);

        GeneralPath nose1 = new GeneralPath();
        nose1.moveTo(267, 309);
        nose1.quadTo(256, 322, 264, 330);
        nose1.quadTo(263, 320, 269, 315);
        g2d.setColor(new Color(135, 103, 90));
        nose1.closePath();

        g2d.fill(nose1);

        Ellipse2D nose2 = new Ellipse2D.Double(285, 323, 13, 6);
        g2d.fill(nose2);

        Ellipse2D nose3 = new Ellipse2D.Double(324, 323, 8, 6);
        g2d.fill(nose3);

        GeneralPath lefteyedetail1 = new GeneralPath();
        lefteyedetail1.moveTo(161, 330);
        lefteyedetail1.lineTo(159, 294);
        lefteyedetail1.quadTo(139, 311, 147, 310);
        lefteyedetail1.quadTo(150, 310, 144, 316);
        lefteyedetail1.quadTo(153, 323, 156, 329);
        lefteyedetail1.closePath();
        g2d.setColor(new Color(135, 103, 90));
        g2d.fill(lefteyedetail1);

        GeneralPath lefteyedetail2 = new GeneralPath();
        lefteyedetail2.moveTo(161, 356);
        lefteyedetail2.lineTo(166, 350);
        lefteyedetail2.lineTo(166, 356);
        lefteyedetail2.closePath();
        g2d.fill(lefteyedetail2);

        Ellipse2D lefteardetail3 = new Ellipse2D.Double(131, 272, 5, 25);
        g2d.fill(lefteardetail3);

        Ellipse2D lefteardetail4 = new Ellipse2D.Double(135, 229, 5, 5);
        g2d.fill(lefteardetail4);

        GeneralPath mouth = new GeneralPath();
        mouth.moveTo(255, 380);
        mouth.quadTo(305, 370, 355, 380);
        mouth.quadTo(305, 410, 255, 380);

        g2d.fill(mouth);

        Ellipse2D leftPupil = new Ellipse2D.Double(248, 257, 5, 5);
        g2d.setColor(new Color(180, 138, 120));
        g2d.fill(leftPupil);

        Ellipse2D rightPupil = new Ellipse2D.Double(373, 257, 5, 5);
        g2d.fill(rightPupil);

        GeneralPath undershirt = new GeneralPath();
        undershirt.moveTo(223, 705);
        undershirt.lineTo(330, 682);
        undershirt.lineTo(293, 891);
        undershirt.lineTo(260, 870);

        g2d.setColor(Color.WHITE);
        g2d.fill(undershirt);

        GeneralPath undershirtShadow = new GeneralPath();
        undershirtShadow.moveTo(260, 870);
        undershirtShadow.lineTo(330, 682);
        undershirtShadow.lineTo(293, 891);
        undershirtShadow.lineTo(260, 870);
        undershirtShadow.closePath();

        g2d.setColor(Color.GRAY);
        g2d.fill(undershirtShadow);

        GeneralPath neckOutline = new GeneralPath();
        neckOutline.moveTo(404, 406);
        neckOutline.quadTo(276, 509, 170, 368);

        g2d.setColor(new Color(135, 103, 90));
        g2d.draw(neckOutline);

        GeneralPath neckShadow = new GeneralPath();
        neckShadow.moveTo(404, 406);
        neckShadow.quadTo(276, 509, 170, 368);
        neckShadow.quadTo(276, 519, 404, 406);

        g2d.setColor(new Color(135, 103, 90));
        g2d.fill(neckShadow);

        GeneralPath necklace = new GeneralPath();
        necklace.moveTo(163, 547);
        necklace.quadTo(237, 624, 278, 648);
        necklace.lineTo(278, 688);
        necklace.lineTo(278, 648);
        necklace.quadTo(320, 624, 363, 547);

        g2d.setColor(new Color(192, 192, 192));
        g2d.draw(necklace);

        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString("CK", 472, 697);

    }

    public static void main(String[] args) {
        Frame frame = new self_portrait_copy();
        frame.setTitle("Self Portrait");
        frame.setSize(600, 1000);
        frame.setBackground(Color.WHITE);
        frame.setForeground(Color.BLACK);
        frame.setVisible(true);
    }
}
