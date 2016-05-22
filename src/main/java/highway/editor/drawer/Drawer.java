package highway.editor.drawer;
import highway.util.Vector2;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public abstract class Drawer {

    private ArrayList<Drawer.Path> paths = new ArrayList<Path>();

    public void draw(Graphics2D graphics2D, int x, int y, double rotation) {
        draw(x, y);
        for (Drawer.Path path : paths) {
            // TODO rotationを考慮する
            graphics2D.drawLine(path.getFromX(), path.getFromY(), path.getToX(), path.getToY());
        }
    }

    protected void drawLine(int fromX, int fromY, int toX, int toY) {
        paths.add(new Path(fromX, fromY, toX, toY));
    }

    protected abstract void draw(int originX, int originY);

    private class Path {
        private Vector2 fromPosition;
        private Vector2 toPosition;

        public Path(int fromX, int fromY, int toX, int toY) {
            fromPosition = new Vector2(fromX, fromY);
            toPosition = new Vector2(toX, toY);
        }

        public int getFromX() { return (int)fromPosition.getX(); }
        public int getFromY() { return (int)fromPosition.getY(); }
        public int getToX() { return (int)toPosition.getX(); }
        public int getToY() { return (int)toPosition.getY(); }
    }
}
