import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class CarAnimation extends JPanel {

    private int redCarX = 0; // X position of the red car
    private int redCarY = 400; // Y position of the red car (top lane)
    private boolean redCarMovingToLowerLane = false; // Tracks if the red car is transitioning to the lower lane

    private int blueCarX = -200; // X position of the blue car (starts off-screen)
    private int blueCarY = 450; // Y position of the blue car (middle lane)
    private boolean blueCarMovingToMiddleLane = false; // Tracks if the blue car is transitioning to the middle lane

    private int greenCarX = -300; // X position of the green car (starts off-screen)
    private int greenCarY = 500; // Y position of the green car (lower lane)
    private boolean greenCarMovingToMiddleLane = false; // Tracks if the green car is transitioning to the middle lane

    private int ufoX = 1300; // Starting x position of the ufo (off-screen to the right)
    private int ufoY = 100; // Y position of the ufo
    private int ufoSpeed = 3; // Speed of the ufo moving left
    private boolean ufoInMiddle = false; // Tracks if the ufo is in the middle

    private int frameWidth = 1200; // Frame width to extend the road
    private double starScaleDirection = 0.01; // Direction of scaling
    private double rotationAngle = 0; // Angle for star rotation
    private List<Cloud> clouds = new ArrayList<>();
    private List<Star> stars = new ArrayList<>();
    private int blueCarDelayCounter = 0; // Counter to delay blue car movement
    private int greenCarDelayCounter = 0; // Counter to delay green car movement


      public CarAnimation() {
        int animationDuration = 5000; // Animation duration in milliseconds (faster speed)
        int timerDelay = 20; // Timer delay in milliseconds
        int totalFrames = animationDuration / timerDelay; // Total number of frames
        int carSpeed = frameWidth / totalFrames; // Calculate speed based on duration

        // Initialize clouds with random positions and sizes
        for (int i = 0; i < 5; i++) {
            clouds.add(new Cloud((int) (Math.random() * frameWidth), (int) (Math.random() * 150),
                    50 + (int) (Math.random() * 100)));
        }

        // Initialize stars with random positions and initial scaling values
        for (int i = 0; i < 15; i++) {
            stars.add(new Star((int) (Math.random() * frameWidth), (int) (Math.random() * 200),
                    0.8 + Math.random() * 0.4));
        }

        Timer timer = new Timer(timerDelay, e -> {
            redCarX += carSpeed; // Move the red car to the right

            // Move ufo from right to left
            ufoX -= ufoSpeed;

            // Reset ufo when it goes off-screen to the left
            if (ufoX < -200) {
                ufoX = 1300; // Reset to right side
                ufoY = 50 + (int) (Math.random() * 150); // Randomize height slightly
            }

            // Delay blue car movement by 2 seconds
            if (blueCarDelayCounter >= 100) { // 100 frames = 2 seconds (100 * timerDelay = 2000ms)
                blueCarX += carSpeed; // Move the blue car to the right
            } else {
                blueCarDelayCounter++;
            }

            // Delay green car movement by 3 seconds
            if (greenCarDelayCounter >= 150) { // 150 frames = 3 seconds (150 * timerDelay = 3000ms)
                greenCarX += carSpeed; // Move the green car to the right
            } else {
                greenCarDelayCounter++;
            }

            // Move clouds slowly
            for (Cloud cloud : clouds) {
                cloud.x -= 1; // Move clouds to the left
                if (cloud.x + cloud.size < 0) {
                    cloud.x = frameWidth; // Reset cloud position
                }
            }

            // Trigger lane change when the red car reaches the center of the frame
            if (redCarX >= frameWidth / 2 - 50 && redCarX <= frameWidth / 2 + 50 && !redCarMovingToLowerLane) {
                redCarMovingToLowerLane = true; // Start the transition to the lower lane
            }

            // Smooth transition to the lower lane for the red car
            if (redCarMovingToLowerLane) {
                if (redCarY < 500) {
                    redCarY += 5; // Gradually move the red car down
                } else {
                    redCarMovingToLowerLane = false; // End the transition when the car reaches the lower lane
                }
            }

            // Trigger lane change when the blue car reaches the center of the frame
            if (blueCarX >= frameWidth / 3 - 50 && blueCarX <= frameWidth / 3 + 50 && !blueCarMovingToMiddleLane) {
                blueCarMovingToMiddleLane = true; // Start the transition to the middle lane
            }

            // Smooth transition to the middle lane for the blue car
            if (blueCarMovingToMiddleLane) {
                if (blueCarY > 400) {
                    blueCarY -= 5; // Gradually move the blue car up
                } else {
                    blueCarMovingToMiddleLane = false; // End the transition when the car reaches the middle lane
                }
            }

            // Trigger lane change when the green car reaches the center of the frame
            if (greenCarX >= frameWidth * 2 / 3 - 50 && greenCarX <= frameWidth * 2 / 3 + 50
                    && !greenCarMovingToMiddleLane) {
                greenCarMovingToMiddleLane = true; // Start the transition to the middle lane
            }

            // Smooth transition to the middle lane for the green car
            if (greenCarMovingToMiddleLane) {
                if (greenCarY > 450) {
                    greenCarY -= 5; // Gradually move the green car up
                } else {
                    greenCarMovingToMiddleLane = false; // End the transition when the car reaches the middle lane
                }
            }

            // When the cars reach the end of the panel, loop them back
            if (redCarX > frameWidth) {
                redCarX = -100; // Start off-screen to the left
                redCarY = 400; // Reset to the upper lane
            }

            if (blueCarX > frameWidth) {
                blueCarX = -200; // Start off-screen to the left
                blueCarY = 450; // Reset to the middle lane
                blueCarDelayCounter = 0; // Reset the delay counter
            }

            if (greenCarX > frameWidth) {
                greenCarX = -300; // Start off-screen to the left
                greenCarY = 500; // Reset to the lower lane
                greenCarDelayCounter = 0; // Reset the delay counter
            }

            // Update rotation angle for stars
            rotationAngle += 0.01;
            if (rotationAngle >= 2 * Math.PI) {
                rotationAngle -= 2 * Math.PI;
            }

            // Update star scaling
            for (Star star : stars) {
                star.scale += star.scaleDirection;
                if (star.scale >= 1.2 || star.scale <= 0.8) {
                    star.scaleDirection = -star.scaleDirection; // Reverse scaling direction
                }
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

        // Draw stationary stars with rotation and scaling
        g2d.setColor(Color.WHITE);
        for (Star star : stars) {
            AffineTransform originalTransform = g2d.getTransform();
            g2d.translate(star.x, star.y);
            g2d.scale(star.scale, star.scale);
            g2d.rotate(rotationAngle);
            drawStar(g2d, 0, 0, 10, 20, 5); // Draw a 5-pointed star
            g2d.setTransform(originalTransform);
        }

        // Draw clouds
        g2d.setColor(new Color(200, 200, 200)); // Light gray for clouds
        for (Cloud cloud : clouds) {
            g2d.fillOval(cloud.x, cloud.y, cloud.size, cloud.size / 2);
        }

        // Draw the ufo
        drawufo(g2d, ufoX, ufoY);

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

        // Draw the red car with transformation
        g2d.translate(redCarX, redCarY);
        drawDetailedCar(g2d, new Color(220, 20, 60)); // Red car

        // Restore the transform
        g2d.setTransform(originalTransform);

        // Draw the blue car with transformation
        g2d.translate(blueCarX, blueCarY);
        drawDetailedCar(g2d, new Color(0, 0, 255)); // Blue car

        // Restore the transform
        g2d.setTransform(originalTransform);

        // Draw the green car with transformation
        g2d.translate(greenCarX, greenCarY);
        drawDetailedCar(g2d, new Color(0, 128, 0)); // Green car

        // Restore the transform again
        g2d.setTransform(originalTransform);
    }

    private void drawufo(Graphics2D g2d, int x, int y) {
        // Base of the UFO
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(x, y, 120, 60); // Wider base for more realistic shape

        // Dome of the UFO
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x + 30, y - 30, 60, 40); // Smooth, rounded dome on top

        // Base outline
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x, y, 120, 60);
        g2d.drawOval(x + 30, y - 30, 60, 40); // Dome outline

        // Lights around the UFO base
        g2d.setColor(Color.YELLOW);
        for (int i = 0; i < 5; i++) {
            int lightX = x + 15 + i * 18;
            int lightY = y + 25;
            g2d.fillOval(lightX, lightY, 12, 12);
        }

        // Reflection on the dome
        g2d.setColor(new Color(255, 255, 255, 150)); // Semi-transparent white
        g2d.fillOval(x + 45, y - 20, 30, 20);

        // Draw beam underneath the UFO when stationary
        if (ufoInMiddle) {
            // Beam with gradient for glowing effect
            GradientPaint beamGradient = new GradientPaint(
                    x + 60, y + 60, new Color(0, 255, 255, 100),
                    x + 60, y + 300, new Color(0, 255, 255, 10));
            g2d.setPaint(beamGradient);

            // Triangular beam widening towards the ground
            int[] beamXPoints = {
                    x + 60, // Center point at UFO
                    x + 20, // Left point at bottom
                    x + 100 // Right point at bottom
            };
            int[] beamYPoints = {
                    y + 60, // Top point (UFO base)
                    y + 300, // Bottom left point
                    y + 300 // Bottom right point
            };
            g2d.fillPolygon(beamXPoints, beamYPoints, 3);
        }
    }

    private void drawHeadlightBeam(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(255, 255, 0, 50)); // Yellow light with transparency
        int[] xPoints = { x, x + 10, x - 130 }; // Triangle representing the light beam
        int[] yPoints = { y, y + 30, y - 5 }; // Spread of the beam
        g2d.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawDetailedCar(Graphics2D g2d, Color bodyColor) {
        // Car body
        g2d.setColor(bodyColor);
        g2d.fillRoundRect(0, 10, 100, 40, 10, 10); // Rounded car body

        // Windows
        g2d.setColor(new Color(135, 206, 250)); // Sky blue
        g2d.fillRect(10, 15, 30, 20); // Front window
        g2d.fillRect(50, 15, 30, 20); // Rear window
        g2d.setColor(Color.BLACK);
        g2d.drawRect(10, 15, 30, 20); // Front window outline
        g2d.drawRect(50, 15, 30, 20); // Rear window outline

        // Roof
        g2d.setColor(bodyColor);
        g2d.fillRect(10, 5, 70, 10);

        // Headlights
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(-5, 20, 10, 10); // Left headlight
        g2d.fillOval(-5, 30, 10, 10); // Right headlight
        g2d.setColor(Color.BLACK);
        g2d.drawOval(-5, 20, 10, 10); // Headlight outline
        g2d.drawOval(-5, 30, 10, 10); // Headlight outline

        // Draw headlight beams
        drawHeadlightBeam(g2d, 200, 25); // Beam from the left headlight
        drawHeadlightBeam(g2d, 200, 35); // Beam from the right headlight

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

    private void drawStar(Graphics2D g2d, int centerX, int centerY, int innerRadius, int outerRadius, int numPoints) {
        double angleStep = Math.PI / numPoints;
        int[] xPoints = new int[numPoints * 2];
        int[] yPoints = new int[numPoints * 2];

        for (int i = 0; i < numPoints * 2; i++) {
            double angle = i * angleStep;
            int radius = (i % 2 == 0) ? outerRadius : innerRadius;
            xPoints[i] = centerX + (int) (Math.cos(angle) * radius);
            yPoints[i] = centerY + (int) (Math.sin(angle) * radius);
        }

        g2d.fillPolygon(xPoints, yPoints, numPoints * 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Animation");
        CarAnimation animation = new CarAnimation();

        frame.add(animation);
        frame.setSize(1200, 600); // Extend the frame width
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static class Cloud {
        int x, y, size;

        public Cloud(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    private static class Star {
        int x, y;
        double scale;
        double scaleDirection = 0.01;

        public Star(int x, int y, double scale) {
            this.x = x;
            this.y = y;
            this.scale = scale;
        }
    }
}