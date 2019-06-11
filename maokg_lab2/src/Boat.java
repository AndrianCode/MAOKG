import java.awt.*;
import java.awt.geom.GeneralPath;

class Boat {
    private void drawStick(Graphics2D g2d) {
        g2d.setColor(Color.black);
//        g2d.fillRect(282, 230, 20, 100);
        g2d.fillRect(-13, -24, 20, 100);
    }

    private void drawSail(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
//        double[][] points = {
//                {224, 230}, {207, 168}, {256, 109}, {367, 109},
//                {343, 170}, {372, 235}, {224, 230}
//        };
        double[][] points = {
                {-71, -24}, {-88, -86}, {-39, -145}, {72, -145},
                {48, -84}, {77, -19}, {-71, -24}
        };
        GeneralPath sail = new GeneralPath();
        sail.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            sail.lineTo(points[k][0], points[k][1]);
        sail.closePath();
        g2d.fill(sail);
    }

    private void drawBoard(Graphics2D g2d) {
        GradientPaint gp = new GradientPaint(5, 25,
                new Color(134, 88, 27), 20, 40, Color.RED, true);
        g2d.setPaint(gp);
//        double[][] points = {
//                {105, 270}, {180, 330}, {410, 330}, {475, 280},
//                {400, 400}, {190, 400}, {105, 270}
//        };
        double[][] points = {
                {-190, 16}, {-115, 76}, {115, 76}, {180, 26},
                {105, 146}, {-105, 146}, {-190, 16}
        };
        GeneralPath board = new GeneralPath();
        board.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            board.lineTo(points[k][0], points[k][1]);
        board.closePath();
        g2d.fill(board);
    }

    void drawBoat(Graphics2D g2d) {
        drawStick(g2d);
        drawSail(g2d);
        drawBoard(g2d);
    }
}
