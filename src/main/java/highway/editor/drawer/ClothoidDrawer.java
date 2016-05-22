package highway.editor.drawer;

import highway.util.Clothoid;
import highway.util.Vector2;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class ClothoidDrawer extends Drawer {

    private double radius;
    private double length;

    public ClothoidDrawer(double targetRadius, double targetLength) {
        radius = targetRadius;
        length = targetLength;
    }

    @Override
    protected void draw(int originX, int originY) {
        Clothoid clothoid = new Clothoid(radius, length);
        Vector2 prevPoint = new Vector2(originX, originY);

        while (clothoid.hasNext()) {
            Vector2 point = clothoid.next();
            drawLine((int)prevPoint.getX() + originX, (int)prevPoint.getY() + originY, (int)point.getX() + originX, (int)point.getY() + originY);
            prevPoint = point;
        }
    }
}
