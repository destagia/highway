package highway.editor.panel;

import highway.editor.MapController;
import highway.editor.Repainter;
import highway.editor.SwingHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shohei.miyashita on 5/21/16.
 */
public class ClothoidPanelContainer implements ComponentEditor {

    private MapController controller;

    private JPanel panel;

    private JTextField radiusField;
    private JTextField lengthField;

    public ClothoidPanelContainer(final MapController controller) {
        this.controller = controller;

        panel = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);

        panel.add(new JLabel("長さ"));

        lengthField = SwingHelper.createTextField();
        panel.add(lengthField);

        panel.add(new JLabel("半径"));

        radiusField = SwingHelper.createTextField();
        panel.add(radiusField);
    }

    public void add(Component component) {
        panel.add(component);
    }

    public Container asComponent() {
        return panel;
    }

    public void onApply() {
        int length = Integer.parseInt(lengthField.getText());
        int radius = Integer.parseInt(radiusField.getText());
        controller.drawClothoid(radius, length);
    }
}
