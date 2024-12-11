import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class DelaPaz_FinalProject extends Frame {

    private Image backgroundImage;
    private double marioX = 0;
    private double marioY = 0;
    private boolean jumping = false;
    private int timer = 0;

    public DelaPaz_FinalProject() {
        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("Project level.png");

        // Start timer thread
        new Thread(() -> {
            try {
                while (true) {
                    timer++;
                    repaint();
                    Thread.sleep(1000); // Increment every second
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Draw the background image
        g2d.drawImage(backgroundImage, 0, 0, this);

        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);

        // Level Text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString("MARIO", 100, 100);
        g2d.drawString("0000000", 100, 140);

        g2d.drawString("WORLD", 950, 100);
        g2d.drawString("1-1", 975, 140);

        g2d.drawString("TIME", 1750, 100);
        g2d.drawString(String.format("%04d", timer), 1750, 140);

        // Apply translation to Mario
        AffineTransform transform = new AffineTransform();
        transform.translate(marioX, marioY);
        g2d.setTransform(transform);

        // Draw Mario based on state
        GeneralPath marioShape = jumping ? getJumpingMario() : getNormalMario();
        g2d.setColor(new Color(255, 0, 0));
        g2d.fill(marioShape);
    }

    private GeneralPath getNormalMario() {
        GeneralPath mario = new GeneralPath();
        mario.moveTo(115, 875);
        mario.lineTo(140, 875);
        mario.lineTo(140, 880);
        mario.lineTo(155, 880);
        mario.lineTo(155, 885);
        mario.lineTo(145, 885);
        mario.lineTo(145, 890);
        mario.lineTo(155, 890);
        mario.lineTo(155, 895);
        mario.lineTo(160, 895);
        mario.lineTo(160, 900);
        mario.lineTo(155, 900);
        mario.lineTo(155, 905);
        mario.lineTo(150, 905);
        mario.lineTo(150, 910);
        mario.lineTo(140, 910);
        mario.lineTo(140, 915);
        mario.lineTo(155, 915);
        mario.lineTo(155, 920);
        mario.lineTo(160, 920);
        mario.lineTo(160, 940);
        mario.lineTo(150, 940);
        mario.lineTo(150, 945);
        mario.lineTo(155, 945);
        mario.lineTo(155, 950);
        mario.lineTo(160, 950);
        mario.lineTo(160, 955);
        mario.lineTo(140, 955);
        mario.lineTo(140, 945);
        mario.lineTo(135, 945);
        mario.lineTo(135, 940);
        mario.lineTo(125, 940);
        mario.lineTo(125, 945);
        mario.lineTo(120, 945);
        mario.lineTo(120, 955);
        mario.lineTo(100, 955);
        mario.lineTo(100, 950);
        mario.lineTo(105, 950);
        mario.lineTo(105, 945);
        mario.lineTo(110, 945);
        mario.lineTo(110, 940);
        mario.lineTo(100, 940);
        mario.lineTo(100, 920);
        mario.lineTo(105, 920);
        mario.lineTo(105, 915);
        mario.lineTo(120, 915);
        mario.lineTo(120, 910);
        mario.lineTo(110, 910);
        mario.lineTo(115, 910);
        mario.lineTo(115, 905);
        mario.lineTo(105, 905);
        mario.lineTo(105, 890);
        mario.lineTo(110, 890);
        mario.lineTo(110, 880);
        mario.lineTo(115, 880);
        mario.closePath();
        return mario;
    }

    private GeneralPath getJumpingMario() {
        GeneralPath mario2 = new GeneralPath();
        mario2.moveTo(140 * 0.25, 800 + 0 * 0.25);
        mario2.lineTo(300 * 0.25, 800 + 0 * 0.25);
        mario2.lineTo(300 * 0.25, 800 + 20 * 0.25);
        mario2.lineTo(340 * 0.25, 800 + 20 * 0.25);
        mario2.lineTo(340 * 0.25, 800 + 0 * 0.25);
        mario2.lineTo(360 * 0.25, 800 + 0 * 0.25);
        mario2.lineTo(360 * 0.25, 800 + 120 * 0.25);
        mario2.lineTo(340 * 0.25, 800 + 120 * 0.25);
        mario2.lineTo(340 * 0.25, 800 + 160 * 0.25);
        mario2.lineTo(320 * 0.25, 800 + 160 * 0.25);
        mario2.lineTo(320 * 0.25, 800 + 180 * 0.25);
        mario2.lineTo(300 * 0.25, 800 + 180 * 0.25);
        mario2.lineTo(300 * 0.25, 800 + 200 * 0.25);
        mario2.lineTo(340 * 0.25, 800 + 200 * 0.25);
        mario2.lineTo(340 * 0.25, 800 + 180 * 0.25);
        mario2.lineTo(360 * 0.25, 800 + 180 * 0.25);
        mario2.lineTo(360 * 0.25, 800 + 160 * 0.25);
        mario2.lineTo(380 * 0.25, 800 + 160 * 0.25);
        mario2.lineTo(380 * 0.25, 800 + 260 * 0.25);
        mario2.lineTo(260 * 0.25, 800 + 260 * 0.25);
        mario2.lineTo(260 * 0.25, 800 + 280 * 0.25);
        mario2.lineTo(140 * 0.25, 800 + 280 * 0.25);
        mario2.lineTo(140 * 0.25, 800 + 300 * 0.25);
        mario2.lineTo(100 * 0.25, 800 + 300 * 0.25);
        mario2.lineTo(100 * 0.25, 800 + 260 * 0.25);
        mario2.lineTo(120 * 0.25, 800 + 260 * 0.25);
        mario2.lineTo(120 * 0.25, 800 + 240 * 0.25);
        mario2.lineTo(160 * 0.25, 800 + 240 * 0.25);
        mario2.lineTo(160 * 0.25, 800 + 220 * 0.25);
        mario2.lineTo(140 * 0.25, 800 + 220 * 0.25);
        mario2.lineTo(140 * 0.25, 800 + 200 * 0.25);
        mario2.lineTo(120 * 0.25, 800 + 200 * 0.25);
        mario2.lineTo(120 * 0.25, 800 + 160 * 0.25);
        mario2.lineTo(180 * 0.25, 800 + 160 * 0.25);
        mario2.lineTo(180 * 0.25, 800 + 140 * 0.25);
        mario2.lineTo(140 * 0.25, 800 + 140 * 0.25);
        mario2.lineTo(140 * 0.25, 800 + 80 * 0.25);
        mario2.lineTo(160 * 0.25, 800 + 80 * 0.25);
        mario2.lineTo(160 * 0.25, 800 + 40 * 0.25);
        mario2.lineTo(180 * 0.25, 800 + 40 * 0.25);
        mario2.lineTo(180 * 0.25, 800 + 20 * 0.25);
        mario2.closePath();
        return mario2;
    }

    public void jumpMario() {
        jumping = true;
        new Thread(() -> {
            try {
                // Move Mario up
                for (int i = 0; i < 10; i++) { // Increase the loop count for a higher jump
                    marioY -= 10; // Adjust the value for higher movement
                    repaint();
                    Thread.sleep(30); // Adjust for smoother animation
                }

                // Pause briefly at the peak of the jump
                Thread.sleep(200);

                // Move Mario down
                for (int i = 0; i < 10; i++) {
                    marioY += 10; // Adjust the value to match the upward movement
                    repaint();
                    Thread.sleep(30);
                }

                jumping = false;
                //repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        Frame frame = new DelaPaz_FinalProject();
        frame.setSize(1520, 1080);
        frame.setVisible(true);
        frame.setTitle("Dela Paz - Final Project");

        // Example of moving Mario and jumping
        new Thread(() -> {
            try {
                while (true) {
                    ((DelaPaz_FinalProject) frame).updateMarioPosition(10, 0); // Move right
                    Thread.sleep(50); // Adjust speed
                    if (Math.random() < 0.15) { // Randomly trigger jump
                        ((DelaPaz_FinalProject) frame).jumpMario();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void updateMarioPosition(double dx, double dy) {
        marioX += dx;
        marioY += dy;
        repaint();
    }
}
