package highway.editor;

import highway.editor.drawer.ClothoidDrawer;
import highway.editor.drawer.Drawer;
import highway.editor.drawer.LineDrawer;
import highway.editor.drawer.Terminal;
import highway.util.Vector2;

import java.awt.*;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class MapController {

    DrawerRepository repository;

    public MapController() {
        repository = new DrawerRepository();
    }

    public void onPaint(Graphics2D graphics2D) {
        Terminal terminal = new Terminal(new Vector2(30, 300), 0);
        for (Drawer drawer : repository.getDrawers()) {
            Vector2 origin = terminal.position;
            double rotation = terminal.theta;
            terminal = drawer.draw(graphics2D, (int) origin.getX(), (int) origin.getY(), rotation);
            System.out.println(terminal.position.getX() + ", " + terminal.position.getY());
        }
    }

    public void drawLine(int length) {
        repository.addDrawer(new LineDrawer(length));
    }

    public void drawClothoid(double radius, double length) {
        repository.addDrawer(new ClothoidDrawer(radius, length));
    }
}
