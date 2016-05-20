package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;

/**
 * Created by shohei.miyashita on 5/20/16.
 */
public class MainWindow extends JPanel {

    public MainWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("タイトル");
        frame.setVisible(true);
        frame.setBounds(0, 0, 640, 480);
        frame.getContentPane().add(this);
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double originX = 340;
        double originY = 240;

        double prevX = originX;
        double prevY = originY;

        double targetL = 200;
        double targetR = 30;

        double A = Math.sqrt(targetL * targetR);
        double l = 0;

        while (targetL > l) {
            double r = A * A / l;
            double τ = l / (2 * r);

            double x = 1;
            double prefix = -1;
            for (int k = 2; k < 100; k += 2) {
                double delta = (1.0 / (fact(k) * (k * 2.0 + 1.0))) * Math.pow(τ, k);
                delta *= prefix;
                prefix *= -1;
                x += delta;
            }

            x *= A * Math.sqrt(2 * τ);

            double y = 1.0 / 3.0;
            prefix = -1;
            for (int k = 2; k < 100; k += 2) {
                double delta = (1.0 / (fact(k + 1.0) * ((k + 1.0) * 2.0 + 1.0))) * Math.pow(τ, k);
                delta *= prefix;
                prefix *= -1;
                y += delta;
            }

            y *= -A * τ * Math.sqrt(2 * τ);

            x += originX;
            y += originY;

            graphics2D.draw(new Line2D.Double(prevX, prevY, x, y));
            graphics2D.drawOval((int)x - 2, (int)y - 2, 4, 4);

            prevX = x;
            prevY = y;

            l += 0.01;
        }
    }


    private double fact(double value) {
        double count = value;
        for (int i = 2; i < count; i++) {
            value *= i;
        }
        return value;
    }
}
