package highway.editor.drawer;

import highway.util.Clothoid;
import highway.util.Vector2;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class ClothoidDrawer extends Drawer {

    private double radius;
    private double length;
    private Clothoid.Type type;

    public ClothoidDrawer(double targetRadius, double targetLength, Clothoid.Type type) {
        radius = targetRadius;
        length = targetLength;
        this.type = type;
    }

    @Override
    protected double draw() {
        Clothoid clothoid = new Clothoid(radius, length, type);
        Vector2 prevPoint = Vector2.ZERO;

        while (clothoid.hasNext()) {
            Vector2 point = clothoid.next();
            drawLine((int)prevPoint.getX(), (int)prevPoint.getY(), (int)point.getX(), (int)point.getY());
            prevPoint = point;
        }

        return clothoid.getNextTheta();
    }
}
