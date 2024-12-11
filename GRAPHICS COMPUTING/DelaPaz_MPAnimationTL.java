import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

public class DelaPaz_MPAnimationTL extends Frame {

    private final int[] DURATIONS = {33000, 30000, 3000, 33000, 3000}; //
    //private final int[] DURATIONS = { 5000, 5000, 5000, 5000, 5000 }; // Durations in milliseconds
    private final String[] CONDITIONS = { "Start", "GO1_STP2", "RDY1_STP2", "STP1_GO2", "STP1_RDY2" };
    private int conditionIndex = 0;
    private BufferedImage backgroundImage;
    private long stateStartTime; // Add this field
    private long currentDuration; 

    // Background Image
    public DelaPaz_MPAnimationTL() {
        try {
            backgroundImage = ImageIO.read(new File("maxresdefault.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Switch Case to control the state of the traffic lights
    private boolean[] getLightState(String trafficLight) {
        return switch (CONDITIONS[conditionIndex]) {
            case "Start" -> trafficLight.equals("Traffic Light 1") ? new boolean[] { true, false, false }
                    : new boolean[] { true, false, false };
            case "GO1_STP2" -> trafficLight.equals("Traffic Light 1") ? new boolean[] { false, false, true }
                    : new boolean[] { true, false, false };
            case "RDY1_STP2" -> trafficLight.equals("Traffic Light 1") ? new boolean[] { false, true, false }
                    : new boolean[] { true, false, false };
            case "STP1_GO2" -> trafficLight.equals("Traffic Light 1") ? new boolean[] { true, false, false }
                    : new boolean[] { false, false, true };
            case "STP1_RDY2" -> trafficLight.equals("Traffic Light 1") ? new boolean[] { true, false, false }
                    : new boolean[] { false, true, false };
            default -> new boolean[] { false, false, false };
        };
    }

    // Sustain Function for Lights
    private void sustain(long t) {
        long finish = (new Date()).getTime() + t;
        while ((new Date()).getTime() < finish) {
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Enables antialiasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background image
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Set background color if image is not available
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        for (int x = 0; x < 900; x++) {
            if (x == 0) {  // Initialize timer only at start
                stateStartTime = System.currentTimeMillis();
                currentDuration = DURATIONS[conditionIndex];
            }
            
            boolean[] light1State = getLightState("Traffic Light 1");
            boolean[] light2State = getLightState("Traffic Light 2");

            // Changed to divide duration by 1000.0 for floating-point division
            long timeLeft = Math.round((stateStartTime + currentDuration - System.currentTimeMillis()) / 1000.0);
            
            drawTrafficLight(g2d, 150, 200, light1State, timeLeft, "Stoplight 1");
            drawTrafficLight(g2d, 700, 200, light2State, timeLeft, "Stoplight 2");

            if (timeLeft <= 0) {
                if (conditionIndex == 0) {
                    conditionIndex = 1;
                } else {
                    conditionIndex = (conditionIndex % 4) + 1;
                }
                // Only resets timer when state changes
                stateStartTime = System.currentTimeMillis();
                currentDuration = DURATIONS[conditionIndex];
            }

            sustain(100);
            repaint();
        }
    }

    // Modify method signature to accept title parameter
    private void drawTrafficLight(Graphics2D g2d, int x, int y, boolean[] states, long timeLeft, String title) {
        // Black background for the traffic light
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, y - 120, getWidth(), 20); 
        g2d.fillRect(x + 70, y - 120, 60, 120);

        // White outline for the traffic light
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(new RoundRectangle2D.Double(x - 10, y - 10, 220, 520, 50, 50)); 

        // Outer box for the traffic light with rounded corners
        g2d.setColor(Color.BLACK);
        g2d.fill(new RoundRectangle2D.Double(x, y, 200, 500, 40, 40)); 

        // Add metallic border effect
        GradientPaint metallic = new GradientPaint(x, y, new Color(192, 192, 192),
                                                  x + 200, y, new Color(128, 128, 128));
        g2d.setPaint(metallic);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new RoundRectangle2D.Double(x, y, 200, 500, 40, 40));

        // Dimmer colors for inactive lights
        Color dimRed = new Color(80, 0, 0); // Darker red when off
        Color dimYellow = new Color(90, 90, 0); // Darker yellow when off
        Color dimGreen = new Color(0, 50, 0); // Darker green when off

        // Draw the lights with gradients
        // Red light
        GradientPaint redGradient = states[0] ? 
            new GradientPaint(x + 50, y + 40, Color.RED, x + 150, y + 140, new Color(200, 0, 0)) :
            new GradientPaint(x + 50, y + 40, dimRed, x + 150, y + 140, new Color(60, 0, 0));
        g2d.setPaint(redGradient);
        g2d.fillOval(x + 50, y + 40, 100, 100);

        // Yellow light
        GradientPaint yellowGradient = states[1] ? 
            new GradientPaint(x + 50, y + 160, Color.YELLOW, x + 150, y + 260, new Color(200, 200, 0)) :
            new GradientPaint(x + 50, y + 160, dimYellow, x + 150, y + 260, new Color(70, 70, 0));
        g2d.setPaint(yellowGradient);
        g2d.fillOval(x + 50, y + 160, 100, 100);

        // Green light
        GradientPaint greenGradient = states[2] ? 
            new GradientPaint(x + 50, y + 280, Color.GREEN, x + 150, y + 380, new Color(0, 200, 0)) :
            new GradientPaint(x + 50, y + 280, dimGreen, x + 150, y + 380, new Color(0, 40, 0));
        g2d.setPaint(greenGradient);
        g2d.fillOval(x + 50, y + 280, 100, 100);

        // Add light reflections (small white circles)
        g2d.setColor(new Color(255, 255, 255, 80)); // Semi-transparent white
        g2d.fillOval(x + 70, y + 60, 30, 30);  // Red light reflection
        g2d.fillOval(x + 70, y + 180, 30, 30); // Yellow light reflection
        g2d.fillOval(x + 70, y + 300, 30, 30); // Green light reflection

        // Horizontal bar separating the lights from the Countdown Timer
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(x, y + 420, 200, 10);

        Color textColor;
        if (states[0]) {
            textColor = Color.RED;
        } else if (states[1]) {
            textColor = Color.YELLOW;
        } else if (states[2]) {
            textColor = Color.GREEN;
        } else {
            textColor = Color.DARK_GRAY; // Default color if no light is active
        }
        
        // Countdown Timer
        g2d.setColor(textColor);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        String text = String.format("%02d", Math.max(0, timeLeft)); 
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = x + (200 - metrics.stringWidth(text)) / 2;
        int textY = y + 480;
        g2d.drawString(text, textX, textY);

        // Add title text below stoplight
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 24));
        FontMetrics titleMetrics = g2d.getFontMetrics();
        int titleX = x + (200 - titleMetrics.stringWidth(title)) / 2;
        int titleY = y + 550; // Position below the stoplight
        g2d.drawString(title, titleX, titleY);
    }

    // Main Method
    public static void main(String[] args) {
        DelaPaz_MPAnimationTL frame = new DelaPaz_MPAnimationTL();
        frame.setSize(1000, 800);
        frame.setVisible(true);
        frame.setTitle("Dela Paz - Traffic Lights");
    }
}
