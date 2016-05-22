package highway.editor;

import highway.editor.drawer.Drawer;
import highway.editor.drawer.LineDrawer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class MapController {

    private ArrayList<Drawer> drawers = new ArrayList<Drawer>();

    public void onPaint(Graphics2D graphics2D) {
        for (Drawer drawer : drawers) {
            drawer.draw(graphics2D, 0, 300, 0);
        }
    }

    public void drawLine(int length) {
        drawers.add(new LineDrawer(length));
    }
}
