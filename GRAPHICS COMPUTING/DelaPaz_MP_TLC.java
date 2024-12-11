import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;

public class DelaPaz_MP_TLC extends Frame {

    // private final int[] DURATIONS = { 33000, 30000, 3000, 33000, 3000 }; //
    private final int[] DURATIONS = { 5000, 5000, 5000, 5000, 5000 }; //
    // Durations in milliseconds
    private final String[] CONDITIONS = { "Start", "GO1_STP2", "RDY1_STP2", "STP1_GO2", "STP1_RDY2" };
    private int conditionIndex = 0;
    private BufferedImage backgroundImage;
    private long stateStartTime; 
    private long currentDuration;

    // Background Image
    public DelaPaz_MP_TLC() {
        try {
            backgroundImage = ImageIO.read(new File("street.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        BasicStroke stroke = new BasicStroke(5.0f);
        g2d.setStroke(stroke);

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Set background color if image is not available
            g2d.setColor(new Color(170, 215, 165));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }

        // Draw the traffic lights
        g2d.setPaint(Color.BLACK);
        GeneralPath road = new GeneralPath();
        road.moveTo(0, 300);
        road.lineTo(500, 300);
        road.lineTo(500, 0);
        road.lineTo(800, 0);
        road.lineTo(800, 300);
        road.lineTo(1300, 300);
        road.lineTo(1300, 450);
        road.lineTo(800, 450);
        road.lineTo(800, 800);
        road.lineTo(500, 800);
        road.lineTo(500, 450);
        road.lineTo(0, 450);
        road.lineTo(0, 300);
        g2d.draw(road);
        g2d.setPaint(Color.GRAY);
        g2d.fill(road);

        g2d.setPaint(Color.YELLOW);
        GeneralPath road1 = new GeneralPath();
        road1.moveTo(645, 0);
        road1.lineTo(645, 300);
        road1.moveTo(655, 0);
        road1.lineTo(655, 300);
        road1.moveTo(645, 450);
        road1.lineTo(645, 800);
        road1.moveTo(655, 450);
        road1.lineTo(655, 800);
        road1.moveTo(0, 370);
        road1.lineTo(500, 370);
        road1.moveTo(0, 380);
        road1.lineTo(500, 380);
        road1.moveTo(800, 370);
        road1.lineTo(1300, 370);
        road1.moveTo(800, 380);
        road1.lineTo(1300, 380);
        g2d.draw(road1);

        for (int x = 0; x < 900; x++) {
            if (x == 0) { // Initialize timer only at start
                stateStartTime = System.currentTimeMillis();
                currentDuration = DURATIONS[conditionIndex];
            }

            boolean[] light1State = getLightState("Traffic Light 1");
            boolean[] light2State = getLightState("Traffic Light 2");

            long timeLeft = Math.round((stateStartTime + currentDuration - System.currentTimeMillis()) / 1000.0);

            drawTrafficLight(g2d, 420, 150, light1State, timeLeft); // topleft
            drawTrafficLight(g2d, 420, 470, light2State, timeLeft); // bottomleft
            drawTrafficLight(g2d, 820, 150, light2State, timeLeft); // topright
            drawTrafficLight(g2d, 820, 470, light1State, timeLeft); // bottomright

            if (timeLeft <= 0) {
                conditionIndex = (conditionIndex + 1) % CONDITIONS.length;
                stateStartTime = System.currentTimeMillis();
                currentDuration = DURATIONS[conditionIndex];
            }

            // Move cars based on traffic light states
            boolean isYellowLight = light1State[1] || light2State[1];
            if (light1State[2]) { // Traffic Light 1 is green
                moveCars(g2d, "leftToRight", isYellowLight);
            } else if (light2State[2]) { // Traffic Light 2 is green
                moveCars(g2d, "rightToLeft", isYellowLight);
            } else {
                moveCars(g2d, "stationary", isYellowLight);
            }

            sustain(100);
            repaint();
        }

        // Stoplight
        g2d.setPaint(Color.GRAY);
        Rectangle2D.Double stoplight_1 = new Rectangle2D.Double(200, 100, 100, 150);
        g2d.fill(stoplight_1);

        Rectangle2D.Double stoplight_2 = new Rectangle2D.Double(1000, 100, 100, 150);
        g2d.fill(stoplight_2);

        Rectangle2D.Double stoplight_3 = new Rectangle2D.Double(200, 500, 100, 150);
        g2d.fill(stoplight_3);

        Rectangle2D.Double stoplight_4 = new Rectangle2D.Double(1000, 500, 100, 150);
        g2d.fill(stoplight_4);

        g2d.setPaint(Color.RED);
        Ellipse2D.Double red_1 = new Ellipse2D.Double(230, 105, 40, 40);
        g2d.draw(red_1);
        Ellipse2D.Double red_2 = new Ellipse2D.Double(1030, 105, 40, 40);
        g2d.draw(red_2);
        Ellipse2D.Double red_3 = new Ellipse2D.Double(230, 505, 40, 40);
        g2d.draw(red_3);
        Ellipse2D.Double red_4 = new Ellipse2D.Double(1030, 505, 40, 40);
        g2d.draw(red_4);

        g2d.setPaint(Color.YELLOW);
        Ellipse2D.Double yellow_1 = new Ellipse2D.Double(230, 155, 40, 40);
        g2d.draw(yellow_1);
        Ellipse2D.Double yellow_2 = new Ellipse2D.Double(1030, 155, 40, 40);
        g2d.draw(yellow_2);
        Ellipse2D.Double yellow_3 = new Ellipse2D.Double(230, 555, 40, 40);
        g2d.draw(yellow_3);
        Ellipse2D.Double yellow_4 = new Ellipse2D.Double(1030, 555, 40, 40);
        g2d.draw(yellow_4);

        g2d.setPaint(Color.GREEN);
        Ellipse2D.Double green_1 = new Ellipse2D.Double(230, 205, 40, 40);
        g2d.draw(green_1);
        Ellipse2D.Double green_2 = new Ellipse2D.Double(1030, 205, 40, 40);
        g2d.draw(green_2);
        Ellipse2D.Double green_3 = new Ellipse2D.Double(230, 605, 40, 40);
        g2d.draw(green_3);
        Ellipse2D.Double green_4 = new Ellipse2D.Double(1030, 605, 40, 40);
        g2d.draw(green_4);

        // car
        GeneralPath car = new GeneralPath();
        car.moveTo(60, 120);
        car.lineTo(80, 120);
        car.quadTo(90, 140, 100, 120);
        car.lineTo(160, 120);
        car.quadTo(170, 140, 180, 120);
        car.lineTo(200, 120);
        car.curveTo(195, 100, 200, 80, 160, 80);
        car.lineTo(110, 80);
        car.lineTo(90, 100);
        car.lineTo(60, 100);
        car.lineTo(60, 120);

        AffineTransform translate = new AffineTransform();
        g2d.setPaint(Color.blue);
        translate.setToTranslation(1100, 230);

        AffineTransform scaling = new AffineTransform();
        g2d.setPaint(Color.blue);
        scaling.setToScale(-1, 1);
        g2d.fill(scaling.createTransformedShape(car));

        g2d.setPaint(Color.red);
        translate.setToTranslation(200, 310);

        // Up and Down
        GeneralPath car1 = new GeneralPath();
        car1.moveTo(710, 500);
        car1.lineTo(780, 500);
        car1.lineTo(780, 650);
        car1.lineTo(710, 650);
        car1.lineTo(710, 500);
        g2d.setPaint(Color.RED);
        g2d.fill(car1);

        Rectangle2D.Double car2 = new Rectangle2D.Double(540, 100, 60, 100);
        g2d.setPaint(Color.red);
        g2d.fill(car2);

        for (int x = 0; x < 90; x++) {
            g2d.setColor(Color.red);
            g2d.fill(red_4);
            g2d.fill(red_2);
            g2d.setColor(Color.GREEN);
            g2d.fill(green_1);
            g2d.fill(green_3);
            g2d.setColor(Color.BLACK);
            g2d.fill(yellow_3);
            g2d.fill(red_1);
            g2d.fill(red_3);
            g2d.fill(yellow_1);
            g2d.fill(yellow_4);
            g2d.fill(yellow_2);
            g2d.fill(green_4);
            g2d.fill(green_2);
            int goRight = 1150;
            int goLeft = 220;
            AffineTransform moveRight = new AffineTransform();
            AffineTransform moveLeft = new AffineTransform();
            g2d.setPaint(Color.GREEN);
            g2d.fill(car1);
            g2d.fill(car2);
            for (int y = 0; y < 25; y++) {
                // Right Car
                g2d.setColor(Color.blue);
                moveRight.setToTranslation(goRight, 230);
                g2d.fill(moveRight.createTransformedShape(car));
                g2d.setColor(Color.blue);
                moveLeft.setToTranslation(goLeft, 315);
                g2d.fill(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));

                runCars(50);
                // Right Car
                goRight = goRight - 50;
                g2d.setColor(new Color(0, 0, 0, 0)); // Transparent color
                g2d.fill(moveRight.createTransformedShape(car));
                // Left Car
                goLeft = goLeft + 50;
                g2d.setColor(Color.GRAY);
                g2d.fill(moveLeft.createTransformedShape(car));

            }
            g2d.setColor(Color.yellow);
            g2d.fill(yellow_1);
            g2d.fill(yellow_3);
            g2d.setColor(Color.RED);
            g2d.fill(red_4);
            g2d.fill(red_2);
            g2d.setColor(Color.BLACK);
            g2d.fill(green_1);
            g2d.fill(green_3);
            g2d.fill(red_1);
            g2d.fill(red_3);
            g2d.fill(yellow_4);
            g2d.fill(yellow_2);
            g2d.fill(green_4);
            g2d.fill(green_2);

            goRight = 1150;

            g2d.setColor(Color.blue);
            moveRight.setToTranslation(1100, 230);
            goLeft = 220;
            for (int y = 0; y < 26; y++) {
                g2d.setColor(Color.blue);
                moveRight.setToTranslation(goRight, 230);
                g2d.fill(moveRight.createTransformedShape(car));
                g2d.setColor(Color.blue);
                moveLeft.setToTranslation(goLeft, 315);
                g2d.fill(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));
                runCars(50);
                // Right Car
                goRight = goRight - 2;
                g2d.setColor(Color.GRAY);
                g2d.fill(moveRight.createTransformedShape(car));
                // Left
                goLeft = goLeft + 2;
                g2d.setColor(Color.GRAY);
                g2d.fill(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));
            }

            g2d.setColor(Color.blue);
            moveLeft.setToTranslation(270, 315);
            g2d.fill(moveRight.createTransformedShape(car));
            g2d.fill(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));

            // If Right is Red = Left is Go
            g2d.setColor(Color.GREEN);

            g2d.setColor(Color.RED);
            g2d.fill(red_1);
            g2d.fill(red_3);
            g2d.setColor(Color.BLACK);
            g2d.fill(green_1);
            g2d.fill(green_3);
            g2d.fill(yellow_1);
            g2d.fill(yellow_3);

            goLeft = 220;

            g2d.setColor(Color.red);
            g2d.fill(red_1);
            g2d.fill(red_3);
            g2d.setColor(Color.GREEN);
            g2d.fill(green_4);
            g2d.fill(green_2);
            g2d.setColor(Color.BLACK);
            g2d.fill(yellow_3);
            g2d.fill(yellow_1);
            g2d.fill(yellow_4);
            g2d.fill(yellow_2);
            g2d.fill(red_2);
            g2d.fill(red_4);

            int yup = 0;
            int ydown = 0;
            AffineTransform movedown = new AffineTransform();
            AffineTransform moveup = new AffineTransform();
            for (int y = 0; y < 25; y++) {
                g2d.setColor(Color.GREEN);
                moveup.setToTranslation(00, yup);
                g2d.fill(moveup.createTransformedShape(car2));
                g2d.setColor(Color.GREEN);
                movedown.setToTranslation(00, ydown);
                g2d.fill(movedown.createTransformedShape(car2));
                runCars(50);
                // Right Car
                yup = yup - 50;
                g2d.setColor(Color.GRAY);
                g2d.fill(moveup.createTransformedShape(car2));
                // Left Car
                ydown = ydown + 50;
                g2d.setColor(Color.GRAY);
                g2d.fill(movedown.createTransformedShape(car2));

            }
            g2d.setColor(Color.yellow);
            g2d.fill(yellow_2);
            g2d.fill(yellow_4);
            g2d.setColor(Color.RED);
            g2d.fill(red_3);
            g2d.fill(red_1);
            g2d.setColor(Color.BLACK);
            g2d.fill(green_1);
            g2d.fill(green_3);

            g2d.fill(yellow_3);
            g2d.fill(yellow_1);
            g2d.fill(green_4);
            g2d.fill(green_2);

            yup = 50;
            ydown = -50;
            for (int y = 0; y < 25; y++) {
                g2d.setColor(Color.GREEN);
                moveup.setToTranslation(00, yup);
                g2d.fill(moveup.createTransformedShape(car1));
                movedown.setToTranslation(00, ydown);
                g2d.fill(movedown.createTransformedShape(car2));
                runCars(50);
                yup = yup - 2;
                g2d.setColor(Color.GRAY);
                g2d.fill(moveup.createTransformedShape(car1));
                ydown = ydown + 2;
                g2d.setColor(Color.GRAY);
                g2d.fill(movedown.createTransformedShape(car2));
            }

            g2d.setColor(Color.GREEN);
            moveup.setToTranslation(1050, 230);
            g2d.fill(moveup.createTransformedShape(car2));

            g2d.setColor(Color.GREEN);
            movedown.setToTranslation(220, 315);
            g2d.fill(movedown.createTransformedShape(scaling.createTransformedShape(car1)));

            // Right = Red, Left=Go
            g2d.setColor(Color.GREEN);
            g2d.setColor(Color.RED);
            g2d.fill(red_1);
            g2d.fill(red_3);
            g2d.setColor(Color.BLACK);
            g2d.fill(green_1);
            g2d.fill(green_3);
            g2d.fill(yellow_1);
            g2d.fill(yellow_3);

            goLeft = 220;
        }

    }

    private void drawTrafficLight(Graphics2D g2d, int x, int y, boolean[] states, long timeLeft) {
        // Black background for the traffic light
        g2d.setColor(Color.BLACK);
        // g2d.fillRect(0, y - 120, getWidth(), 20);
        // g2d.fillRect(x + 70, y - 120, 60, 120);

        // White outline for the traffic light
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(new RoundRectangle2D.Double(x - 3, y - 3, 60, 140, 15, 15));

        // Outer box for the traffic light with rounded corners
        g2d.setColor(Color.BLACK);
        g2d.fill(new RoundRectangle2D.Double(x, y, 54, 130, 10, 10));

        // Add metallic border effect
        GradientPaint metallic = new GradientPaint(x, y, new Color(192, 192, 192),
                x + 54, y, new Color(128, 128, 128));
        g2d.setPaint(metallic);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(new RoundRectangle2D.Double(x, y, 54, 130, 10, 10));

        // Dimmer colors for inactive lights
        Color dimRed = new Color(80, 0, 0); // Darker red when off
        Color dimYellow = new Color(90, 90, 0); // Darker yellow when off
        Color dimGreen = new Color(0, 50, 0); // Darker green when off

        // Draw the lights with gradients
        // Red light
        GradientPaint redGradient = states[0]
                ? new GradientPaint(x + 14, y + 10, Color.RED, x + 44, y + 40, new Color(200, 0, 0))
                : new GradientPaint(x + 14, y + 10, dimRed, x + 44, y + 40, new Color(70, 0, 0));
        g2d.setPaint(redGradient);
        g2d.fillOval(x + 14, y + 10, 25, 25);

        // Yellow light
        GradientPaint yellowGradient = states[1]
                ? new GradientPaint(x + 14, y + 45, Color.YELLOW, x + 44, y + 75, new Color(200, 200, 0))
                : new GradientPaint(x + 14, y + 45, dimYellow, x + 44, y + 75, new Color(70, 70, 0));
        g2d.setPaint(yellowGradient);
        g2d.fillOval(x + 14, y + 45, 25, 25);

        // Green light
        GradientPaint greenGradient = states[2]
                ? new GradientPaint(x + 14, y + 80, Color.GREEN, x + 44, y + 110, new Color(0, 200, 0))
                : new GradientPaint(x + 14, y + 80, dimGreen, x + 44, y + 110, new Color(0, 40, 0));
        g2d.setPaint(greenGradient);
        g2d.fillOval(x + 14, y + 80, 25, 25);

        // Add light reflections (small white circles)
        g2d.setColor(new Color(255, 255, 255, 80)); // Semi-transparent white
        g2d.fillOval(x + 19, y + 15, 8, 8); // Red light reflection
        g2d.fillOval(x + 19, y + 50, 8, 8); // Yellow light reflection
        g2d.fillOval(x + 19, y + 85, 8, 8); // Green light reflection

        // Horizontal bar separating the lights from the Countdown Timer
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(x, y + 110, 54, 3);

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
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        String text = String.format("%02d", Math.max(0, timeLeft));
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = x + (55 - metrics.stringWidth(text)) / 2;
        int textY = y + 125; // Move 1 pixel up
        g2d.drawString(text, textX, textY);
    }

    private void moveCars(Graphics2D g2d, String direction, boolean isYellowLight) {
        int goRight = 1150;
        int goLeft = 220;
        int yup = 0;
        int ydown = 0;

        AffineTransform moveRight = new AffineTransform();
        AffineTransform moveLeft = new AffineTransform();
        AffineTransform moveUp = new AffineTransform();
        AffineTransform moveDown = new AffineTransform();

        GeneralPath car = createCarShape();
        GeneralPath car1 = createCar1Shape();
        Rectangle2D.Double car2 = createCar2Shape();

        AffineTransform scaling = new AffineTransform();
        scaling.setToScale(-1, 1);

        int delay = isYellowLight ? 100 : 50; // Slow down during yellow light

        if (direction.equals("leftToRight")) {
            for (int y = 0; y < 25; y++) {
                g2d.setColor(Color.GRAY);
                moveRight.setToTranslation(goRight + 50, 230);
                g2d.fill(moveRight.createTransformedShape(car));
                g2d.draw(moveRight.createTransformedShape(car));
                moveLeft.setToTranslation(goLeft - 50, 315);
                g2d.fill(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));
                g2d.draw(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));

                // Draw the car in the new position
                g2d.setColor(Color.blue);
                moveRight.setToTranslation(goRight, 230);
                g2d.fill(moveRight.createTransformedShape(car));
                g2d.setColor(Color.blue);
                moveLeft.setToTranslation(goLeft, 315);
                g2d.fill(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));

                runCars(delay);
                goRight -= 50;
                goLeft += 50;
            }

            // Fill the final position with gray to clear any remaining car drawing
            g2d.setColor(Color.GRAY);
            moveRight.setToTranslation(goRight + 50, 230);
            g2d.fill(moveRight.createTransformedShape(car));
            g2d.draw(moveRight.createTransformedShape(car)); // Ensure outline is filled

        } else if (direction.equals("rightToLeft")) {
            for (int y = 0; y < 25; y++) {
                // Fill previous position with gray
                g2d.setColor(Color.GRAY);
                moveUp.setToTranslation(0, yup + 50);
                g2d.fill(moveUp.createTransformedShape(car1));
                g2d.draw(moveUp.createTransformedShape(car1));
                moveDown.setToTranslation(0, ydown - 50);
                g2d.fill(moveDown.createTransformedShape(car2));
                g2d.draw(moveDown.createTransformedShape(car2));

                // Draw the car in the new position
                g2d.setColor(Color.GREEN);
                moveUp.setToTranslation(0, yup);
                g2d.fill(moveUp.createTransformedShape(car1));
                g2d.setColor(Color.GREEN);
                moveDown.setToTranslation(0, ydown);
                g2d.fill(moveDown.createTransformedShape(car2));

                runCars(delay);
                yup -= 50;
                ydown += 50;
            }

            // Fill the final position with gray to clear any remaining car drawing
            g2d.setColor(Color.GRAY);
            moveUp.setToTranslation(0, yup + 50);
            g2d.fill(moveUp.createTransformedShape(car1));
            g2d.draw(moveUp.createTransformedShape(car1));

        } else {

            // Draw stationary cars during red light closer to the stoplights
            g2d.setColor(Color.blue);
            moveRight.setToTranslation(900, 230);
            g2d.fill(moveRight.createTransformedShape(car));
            g2d.setColor(Color.blue);
            moveLeft.setToTranslation(400, 315);
            g2d.fill(moveLeft.createTransformedShape(scaling.createTransformedShape(car)));

            g2d.setColor(Color.GREEN);
            moveUp.setToTranslation(0, 0);
            g2d.fill(moveUp.createTransformedShape(car1));
            g2d.setColor(Color.GREEN);
            moveDown.setToTranslation(0, 100);
            g2d.fill(moveDown.createTransformedShape(car2));
        }
    }

    private GeneralPath createCarShape() {
        GeneralPath car = new GeneralPath();
        car.moveTo(60, 120);
        car.lineTo(80, 120);
        car.quadTo(90, 140, 100, 120);
        car.lineTo(160, 120);
        car.quadTo(170, 140, 180, 120);
        car.lineTo(200, 120);
        car.curveTo(195, 100, 200, 80, 160, 80);
        car.lineTo(110, 80);
        car.lineTo(90, 100);
        car.lineTo(60, 100);
        car.lineTo(60, 120);
        return car;
    }

    private GeneralPath createCar1Shape() {
        GeneralPath car1 = new GeneralPath();
        car1.moveTo(710, 500);
        car1.lineTo(780, 500);
        car1.lineTo(780, 650);
        car1.lineTo(710, 650);
        car1.lineTo(710, 500);
        return car1;
    }

    private Rectangle2D.Double createCar2Shape() {
        return new Rectangle2D.Double(540, 100, 60, 100);
    }

    public void runCars(long t1) {
        long finishGo = (new Date()).getTime() + t1;
        while ((new Date()).getTime() < finishGo) {
        }
    }

    public static void main(String[] args) {
        DelaPaz_MP_TLC s = new DelaPaz_MP_TLC();
        s.setTitle("Dela Paz - MP TLC");
        s.setSize(1200, 700);
        s.setBackground(new Color(170, 215, 165));
        s.setVisible(true);
        s.setPreferredSize(new Dimension(500, 500));
        s.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
