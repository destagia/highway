package highway.editor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class MapPanel extends JPanel {

    MapController controller;

    public MapPanel(MapController controller) {
        this.controller = controller;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        /*
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
                */

        controller.onPaint(graphics2D);
    }
}
