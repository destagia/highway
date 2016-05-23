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
    protected double draw(int originX, int originY) {
        Clothoid clothoid = new Clothoid(radius, length, type);
        Vector2 prevPoint = Vector2.ZERO;

        while (clothoid.hasNext()) {
            Vector2 point = clothoid.next();
            drawLine((int)prevPoint.getX() + originX, (int)prevPoint.getY() + originY, (int)point.getX() + originX, (int)point.getY() + originY);
            prevPoint = point;
        }

        Vector2 additional = clothoid.next();

        return additional.minus(prevPoint).getTheta();
    }
}
