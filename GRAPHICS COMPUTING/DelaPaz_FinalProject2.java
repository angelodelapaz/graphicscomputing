import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class DelaPaz_FinalProject2 extends Frame {
    private GeneralPath mario;
    private GeneralPath mario2;
    private double translateY = 0;
    private boolean isJumping = false;
    private int jumpCounter = 0; // Counter for jumps

    public DelaPaz_FinalProject2() {
        Button jumpButton = new Button("Jump");
        jumpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!isJumping) {
                    isJumping = true;
                    jumpCounter++; // Increment the counter
                    Timer timer = new Timer(1, new ActionListener() {
                        private int jumpHeight = 0;
                        private boolean goingUp = true;

                        public void actionPerformed(ActionEvent e) {
                            if (goingUp) {
                                translateY -= 2;
                                jumpHeight += 2;
                                if (jumpHeight >= 50) {
                                    goingUp = false;
                                }
                            } else {
                                translateY += 2;
                                if (translateY >= 0) {
                                    translateY = 0;
                                    isJumping = false;
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                            repaint();
                        }
                    });
                    timer.start();
                }
            }
        });
        add(jumpButton, BorderLayout.SOUTH);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.translate(0, translateY);
        if (isJumping) {
            g2.fill(getJumpingMario());
        } else {
            g2.fill(getNormalMario());
        }
        g2.setColor(Color.WHITE);
        g2.drawString("Jump Count: " + jumpCounter, 10, 50); // Display the counter
    }

    private GeneralPath getNormalMario() {
        GeneralPath mario = new GeneralPath();
        mario.moveTo(115, 475);
        mario.lineTo(140, 475);
        mario.lineTo(140, 480);
        mario.lineTo(155, 480);
        mario.lineTo(155, 485);
        mario.lineTo(145, 485);
        mario.lineTo(145, 490);
        mario.lineTo(155, 490);
        mario.lineTo(155, 495);
        mario.lineTo(160, 495);
        mario.lineTo(160, 500);
        mario.lineTo(155, 500);
        mario.lineTo(155, 505);
        mario.lineTo(150, 505);
        mario.lineTo(150, 510);
        mario.lineTo(140, 510);
        mario.lineTo(140, 515);
        mario.lineTo(155, 515);
        mario.lineTo(155, 520);
        mario.lineTo(160, 520);
        mario.lineTo(160, 540);
        mario.lineTo(150, 540);
        mario.lineTo(150, 545);
        mario.lineTo(155, 545);
        mario.lineTo(155, 550);
        mario.lineTo(160, 550);
        mario.lineTo(160, 555);
        mario.lineTo(140, 555);
        mario.lineTo(140, 545);
        mario.lineTo(135, 545);
        mario.lineTo(135, 540);
        mario.lineTo(125, 540);
        mario.lineTo(125, 545);
        mario.lineTo(120, 545);
        mario.lineTo(120, 555);
        mario.lineTo(100, 555);
        mario.lineTo(100, 550);
        mario.lineTo(105, 550);
        mario.lineTo(105, 545);
        mario.lineTo(110, 545);
        mario.lineTo(110, 540);
        mario.lineTo(100, 540);
        mario.lineTo(100, 520);
        mario.lineTo(105, 520);
        mario.lineTo(105, 515);
        mario.lineTo(120, 515);
        mario.lineTo(120, 510);
        mario.lineTo(110, 510);
        mario.lineTo(115, 510);
        mario.lineTo(115, 505);
        mario.lineTo(105, 505);
        mario.lineTo(105, 490);
        mario.lineTo(110, 490);
        mario.lineTo(110, 480);
        mario.lineTo(115, 480);
        mario.closePath();
        return mario;
    }

    private GeneralPath getJumpingMario() {
        mario2 = new GeneralPath();
        mario2.moveTo(140 * 0.25 + 100, 400 + 0 * 0.25);
        mario2.lineTo(300 * 0.25 + 100, 400 + 0 * 0.25);
        mario2.lineTo(300 * 0.25 + 100, 400 + 20 * 0.25);
        mario2.lineTo(340 * 0.25 + 100, 400 + 20 * 0.25);
        mario2.lineTo(340 * 0.25 + 100, 400 + 0 * 0.25);
        mario2.lineTo(360 * 0.25 + 100, 400 + 0 * 0.25);
        mario2.lineTo(360 * 0.25 + 100, 400 + 120 * 0.25);
        mario2.lineTo(340 * 0.25 + 100, 400 + 120 * 0.25);
        mario2.lineTo(340 * 0.25 + 100, 400 + 160 * 0.25);
        mario2.lineTo(320 * 0.25 + 100, 400 + 160 * 0.25);
        mario2.lineTo(320 * 0.25 + 100, 400 + 180 * 0.25);
        mario2.lineTo(300 * 0.25 + 100, 400 + 180 * 0.25);
        mario2.lineTo(300 * 0.25 + 100, 400 + 200 * 0.25);
        mario2.lineTo(340 * 0.25 + 100, 400 + 200 * 0.25);
        mario2.lineTo(340 * 0.25 + 100, 400 + 180 * 0.25);
        mario2.lineTo(360 * 0.25 + 100, 400 + 180 * 0.25);
        mario2.lineTo(360 * 0.25 + 100, 400 + 160 * 0.25);
        mario2.lineTo(380 * 0.25 + 100, 400 + 160 * 0.25);
        mario2.lineTo(380 * 0.25 + 100, 400 + 260 * 0.25);
        mario2.lineTo(260 * 0.25 + 100, 400 + 260 * 0.25);
        mario2.lineTo(260 * 0.25 + 100, 400 + 280 * 0.25);
        mario2.lineTo(140 * 0.25 + 100, 400 + 280 * 0.25);
        mario2.lineTo(140 * 0.25 + 100, 400 + 300 * 0.25);
        mario2.lineTo(100 * 0.25 + 100, 400 + 300 * 0.25);
        mario2.lineTo(100 * 0.25 + 100, 400 + 260 * 0.25);
        mario2.lineTo(120 * 0.25 + 100, 400 + 260 * 0.25);
        mario2.lineTo(120 * 0.25 + 100, 400 + 240 * 0.25);
        mario2.lineTo(160 * 0.25 + 100, 400 + 240 * 0.25);
        mario2.lineTo(160 * 0.25 + 100, 400 + 220 * 0.25);
        mario2.lineTo(140 * 0.25 + 100, 400 + 220 * 0.25);
        mario2.lineTo(140 * 0.25 + 100, 400 + 200 * 0.25);
        mario2.lineTo(120 * 0.25 + 100, 400 + 200 * 0.25);
        mario2.lineTo(120 * 0.25 + 100, 400 + 160 * 0.25);
        mario2.lineTo(180 * 0.25 + 100, 400 + 160 * 0.25);
        mario2.lineTo(180 * 0.25 + 100, 400 + 140 * 0.25);
        mario2.lineTo(140 * 0.25 + 100, 400 + 140 * 0.25);
        mario2.lineTo(140 * 0.25 + 100, 400 + 80 * 0.25);
        mario2.lineTo(160 * 0.25 + 100, 400 + 80 * 0.25);
        mario2.lineTo(160 * 0.25 + 100, 400 + 40 * 0.25);
        mario2.lineTo(180 * 0.25 + 100, 400 + 40 * 0.25);
        mario2.lineTo(180 * 0.25 + 100, 400 + 20 * 0.25);
        mario2.closePath();
        return mario2;
    }

    public static void main(String[] args) {
        Frame frame = new DelaPaz_FinalProject2();
        frame.setSize(520, 600);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setTitle("Dela Paz - Final Project");
    }
}