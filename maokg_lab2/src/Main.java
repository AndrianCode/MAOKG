import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
    private double angle = 0;
    private double scale = 1;
    private double delta = 0.01;

    private static int maxWidth;
    private static int maxHeight;

    private Main() {
        Timer timer = new Timer(10, this);
        timer.start();
    }

    private void drawFrame(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        BasicStroke bs2 = new BasicStroke(16, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER);
        g2d.setStroke(bs2);
        g2d.drawRect(50, 15, maxWidth - 100, maxHeight - 30);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setBackground(new Color(237, 238, 109));
        g2d.clearRect(0, 0, maxWidth + 1, maxHeight + 1);

        drawFrame(g2d);
        g2d.translate(maxWidth/2, maxHeight/2);

        g2d.rotate(angle);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float) scale));
        Boat boat = new Boat();
        boat.drawBoat(g2d);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LAB 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 475);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Main());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        if (scale < 0.01) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        scale += delta;
        angle += 0.01;

        repaint();
    }
}
