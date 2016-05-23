package highway.editor.drawer;

import java.awt.*;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class LineDrawer extends Drawer {
    private int length;

    public LineDrawer(int length)
    {
        this.length = length;
    }

    @Override
    public double draw(int originX, int originY)
    {
        drawLine(originX, originY, originX + length, originY);
        return 0.0;
    }
}
