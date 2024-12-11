
/**
 *
 * @author AldrinLuces
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class picture extends Frame {

    private Label coordinatesLabel;

    public picture() {
        // Set the title of the frame
        super("Image with Cursor Coordinates");
        setSize(600, 1000);  // Set the size of the frame

        // Create a label to display the coordinates
        coordinatesLabel = new Label("Coordinates: ");
        
        // Create a custom Panel
        Panel panel = new Panel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                // Load the image
                Image img = Toolkit.getDefaultToolkit().getImage("Screenshot 2024-10-18 161556.png");
                
                // Get the dimensions of the panel
                Dimension size = this.getSize();
                int width = size.width;
                int height = size.height;
                
                // Draw the image, scaling it to fit the entire panel
                g.drawImage(img, 0, 0, width, height, this);
            }
        };

        // Add a MouseMotionListener to the panel to track cursor movement
        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                // Update the label with the current coordinates
                coordinatesLabel.setText("Coordinates: (" + e.getX() + ", " + e.getY() + ")");
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Handle dragging if needed (not used in this example)
            }
        });

        // Add the panel and label to the frame
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(coordinatesLabel, BorderLayout.SOUTH);

        // Make the Frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the Frame
        new picture();
    }
}
