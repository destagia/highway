package highway.editor;

import highway.editor.drawer.ClothoidDrawer;
import highway.editor.drawer.Drawer;
import highway.editor.drawer.LineDrawer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class MapController {

    DrawerRepository repository;

    public MapController() {
        repository = new DrawerRepository();
    }

    public void onPaint(Graphics2D graphics2D) {
        for (Drawer drawer : repository.getDrawers()) {
            drawer.draw(graphics2D, 0, 300, 0);
        }
    }

    public void drawLine(int length) {
        repository.addDrawer(new LineDrawer(length));
    }

    public void drawClothoid(double radius, double length) {
        repository.addDrawer(new ClothoidDrawer(radius, length));
    }
}
