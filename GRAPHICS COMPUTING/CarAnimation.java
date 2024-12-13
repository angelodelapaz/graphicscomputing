import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class CarAnimation extends JPanel {

    private int carX = 0; // X position of the car
    private int carY = 400; // Y position of the car (top lane)
    private boolean movingToLowerLane = false; // Tracks if the car is transitioning to the lower lane
    private int frameWidth = 1200; // Frame width to extend the road

    public CarAnimation() {
        int animationDuration = 5000; // Animation duration in milliseconds (faster speed)
        int timerDelay = 20; // Timer delay in milliseconds
        int totalFrames = animationDuration / timerDelay; // Total number of frames
        int carSpeed = frameWidth / totalFrames; // Calculate speed based on duration

        Timer timer = new Timer(timerDelay, e -> {
            carX += carSpeed; // Move the car to the right

            // Trigger lane change when the car reaches the center of the frame
            if (carX >= frameWidth / 2 - 50 && carX <= frameWidth / 2 + 50 && !movingToLowerLane) {
                movingToLowerLane = true; // Start the transition to the lower lane
            }

            // Smooth transition to the lower lane
            if (movingToLowerLane) {
                if (carY < 500) {
                    carY += 5; // Gradually move the car down
                } else {
                    movingToLowerLane = false; // End the transition when the car reaches the lower lane
                }
            }

            // When the car reaches the end of the panel, loop it back
            if (carX > frameWidth) {
                carX = -100; // Start off-screen to the left
                carY = 400; // Reset to the upper lane
            }

            repaint(); // Trigger the repaint
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the nighttime sky
        g2d.setColor(new Color(10, 10, 50)); // Dark blue sky
        g2d.fillRect(0, 0, frameWidth, getHeight());

        // Draw stars
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < 100; i++) {
            int starX = (int) (Math.random() * frameWidth);
            int starY = (int) (Math.random() * 150); // Stars in the upper portion
            g2d.fillOval(starX, starY, 2, 2);
        }

        // Draw cityscape silhouettes with windows
        g2d.setColor(new Color(30, 30, 30)); // Dark gray for buildings
        int[] buildingHeights = { 200, 300, 400, 250, 350, 280 }; // Higher building heights
        for (int i = 0, x = 0; i < buildingHeights.length; i++, x += 200) {
            g2d.fillRect(x, getHeight() - buildingHeights[i], 150, buildingHeights[i]);

            // Add windows to buildings
            g2d.setColor(new Color(255, 223, 0)); // Yellow for lit windows
            for (int wx = x + 10; wx < x + 150; wx += 30) {
                for (int wy = getHeight() - buildingHeights[i] + 10; wy < getHeight() - 10; wy += 30) {
                    g2d.fillRect(wx, wy, 15, 15); // Draw windows
                }
            }
            g2d.setColor(new Color(30, 30, 30)); // Reset to building color
        }

        // Draw the road
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, getHeight() - 150, frameWidth, 150); // Draw road rectangle

        // Draw lane markings
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < frameWidth; i += 50) {
            g2d.fillRect(i, getHeight() - 100, 30, 5); // Top lane marking
            g2d.fillRect(i, getHeight() - 50, 30, 5); // Bottom lane marking
        }

        // Save the original transform
        AffineTransform originalTransform = g2d.getTransform();

        // Apply transformation: translation, scaling
        g2d.translate(carX, carY);
        if (carY == 500) {
            g2d.scale(1.2, 1.2); // Make the car bigger in the lower lane
        }

        // Draw the car with headlights
        drawDetailedCar(g2d);

        // Draw rotating headlights
        g2d.setColor(new Color(255, 255, 200, 100)); // Light yellow, semi-transparent
        int headlightAngle = movingToLowerLane ? 30 : 0; // Rotate if moving to lower lane
        AffineTransform headlightsTransform = g2d.getTransform();
        g2d.rotate(Math.toRadians(headlightAngle), 50, 25); // Rotate around the center of the car front
        g2d.fillPolygon(new int[] { 100, 200, 200 }, new int[] { 20, 0, 40 }, 3); // Left headlight beam
        g2d.setTransform(headlightsTransform);
        g2d.rotate(Math.toRadians(-headlightAngle), 50, 35); // Rotate around the center of the car front
        g2d.fillPolygon(new int[] { 100, 200, 200 }, new int[] { 30, 10, 50 }, 3); // Right headlight beam
        g2d.setTransform(originalTransform);

        // Restore the original transform
        g2d.setTransform(originalTransform);
    }

    private void drawDetailedCar(Graphics2D g2d) {
        // Car body
        g2d.setColor(new Color(220, 20, 60)); // Crimson color
        g2d.fillRoundRect(0, 10, 100, 40, 10, 10); // Rounded car body

        // Windows
        g2d.setColor(new Color(135, 206, 250)); // Sky blue
        g2d.fillRect(10, 15, 30, 20); // Front window
        g2d.fillRect(50, 15, 30, 20); // Rear window
        g2d.setColor(Color.BLACK);
        g2d.drawRect(10, 15, 30, 20); // Front window outline
        g2d.drawRect(50, 15, 30, 20); // Rear window outline

        // Roof
        g2d.setColor(new Color(220, 20, 60));
        g2d.fillRect(10, 5, 70, 10);

        // Headlights
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(-5, 20, 10, 10); // Left headlight
        g2d.fillOval(-5, 30, 10, 10); // Right headlight
        g2d.setColor(Color.BLACK);
        g2d.drawOval(-5, 20, 10, 10); // Headlight outline
        g2d.drawOval(-5, 30, 10, 10); // Headlight outline

        // Wheels
        g2d.setColor(Color.BLACK);
        g2d.fillOval(10, 40, 20, 20); // Front wheel
        g2d.fillOval(70, 40, 20, 20); // Rear wheel

        // Wheel rims
        g2d.setColor(Color.GRAY);
        g2d.fillOval(15, 45, 10, 10); // Front rim
        g2d.fillOval(75, 45, 10, 10); // Rear rim

        // Door lines
        g2d.setColor(Color.BLACK);
        g2d.drawLine(40, 10, 40, 50); // Separation between doors
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Animation");
        CarAnimation animation = new CarAnimation();

        frame.add(animation);
        frame.setSize(1200, 600); // Extend the frame width
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
