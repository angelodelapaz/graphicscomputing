
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MidpointCirclePlot extends JPanel {

    private List<int[]> points;

    public MidpointCirclePlot(int xc, int yc, int r) {
        points = midpointCircle(xc, yc, r);
    }

    public static List<int[]> midpointCircle(int xc, int yc, int r) {
        List<int[]> points = new ArrayList<>();

        int x = 0;
        int y = r;
        int p = 1 - r;  // Initial decision parameter

        while (x <= y) {
            points.add(new int[]{xc + x, yc + y});
            points.add(new int[]{xc - x, yc + y});
            points.add(new int[]{xc + x, yc - y});
            points.add(new int[]{xc - x, yc - y});
            points.add(new int[]{xc + y, yc + x});
            points.add(new int[]{xc - y, yc + x});
            points.add(new int[]{xc + y, yc - x});
            points.add(new int[]{xc - y, yc - x});

            x++;

            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }

        return points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the circle points
        for (int[] point : points) {
            g.fillOval(point[0] * 20, point[1] * 20, 5, 5);  // Scale points by 20 for visibility
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Midpoint Circle Plot");
        MidpointCirclePlot panel = new MidpointCirclePlot(5, 5, 5); // Center at (5, 5) with radius 5

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(panel);
        frame.setVisible(true);
    }
}
