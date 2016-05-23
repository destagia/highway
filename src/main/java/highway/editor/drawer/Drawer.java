package highway.editor.drawer;
import highway.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public abstract class Drawer {

    private ArrayList<Path> paths = new ArrayList<Path>();

    public Terminal draw(Graphics2D graphics2D, int x, int y, double rotation) {
        Vector2 origin = new Vector2(x, y);
        Vector2 terminal = origin;

        double nextRotation = draw(x, y) + rotation;

        for (Path path : paths) {
            Vector2 from = origin.plus(path.getOrigin().minus(origin).getRotated(rotation));
            int fromX = (int)from.getX();
            int fromY = (int)from.getY();

            Vector2 to = origin.plus(path.getDestination().minus(origin).getRotated(rotation));

            int toX = (int)to.getX();
            int toY = (int)to.getY();

            graphics2D.drawLine(fromX, fromY, toX, toY);

            terminal = to;
        }

        return new Terminal(terminal, nextRotation);
    }

    protected void drawLine(int fromX, int fromY, int toX, int toY) {
        paths.add(new Path(fromX, fromY, toX, toY));
    }

    protected abstract double draw(int originX, int originY);

    private class Path {
        private Vector2 origin;
        private Vector2 destination;

        public Vector2 getOrigin()
        {
            return origin;
        }

        public Vector2 getDestination()
        {
            return destination;
        }

        public Path(int fromX, int fromY, int toX, int toY) {
            origin = new Vector2(fromX, fromY);
            destination = new Vector2(toX, toY);
        }
    }
}
