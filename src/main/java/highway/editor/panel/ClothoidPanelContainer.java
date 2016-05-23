package highway.editor.panel;

import highway.editor.MapController;
import highway.editor.Repainter;
import highway.editor.SwingHelper;
import highway.util.Clothoid;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JComboBox<Clothoid.Type> typeField;

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

        typeField = new JComboBox<Clothoid.Type>();
        Clothoid.Type[] types = Clothoid.Type.values();
        for (int i = 0; i < types.length; i++) {
            typeField.addItem(types[i]);
        }
        panel.add(typeField);
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
        Clothoid.Type type = (Clothoid.Type)typeField.getSelectedItem();
        controller.drawClothoid(radius, length, type);
    }
}
