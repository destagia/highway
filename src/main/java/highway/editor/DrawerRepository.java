package highway.editor;

import highway.editor.drawer.Drawer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class DrawerRepository {
    private int currentIndex = -1;
    private ArrayList<Drawer> drawers = new ArrayList<Drawer>();

    public List<Drawer> getDrawers() {
        return drawers;
    }

    public void forwardCurrentIndex() {
        if (currentIndex < drawers.size()) {
            currentIndex += 1;
        }
    }

    public void backwardCurrentIndex() {
        if (currentIndex > 1) {
            currentIndex -= 1;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void addDrawer(Drawer drawer) {
        if (currentIndex != -1 && currentIndex < drawers.size()) {
            drawers.set(currentIndex, drawer);
        } else {
            drawers.add(drawer);
            currentIndex = drawers.size();
        }
    }
}
