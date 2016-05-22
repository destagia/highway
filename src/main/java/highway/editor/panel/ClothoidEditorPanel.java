package highway.editor.panel;

import highway.editor.MapController;
import highway.editor.Repainter;
import highway.editor.SwingHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shohei.miyashita on 5/21/16.
 */
public class ClothoidEditorPanel extends JPanel {

    public ClothoidEditorPanel(final MapController controller, final Repainter mapRepainter) {

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);

        this.add(new JLabel("長さ"));

        final JTextField lengthField = SwingHelper.createTextField();
        this.add(lengthField);

        this.add(new JLabel("半径"));

        final JTextField radiusField = SwingHelper.createTextField();
        this.add(radiusField);

        final JButton button = new JButton("適用");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = Integer.parseInt(lengthField.getText());
                int radius = Integer.parseInt(radiusField.getText());
                controller.drawClothoid(radius, length);
                mapRepainter.repaint();
            }
        });
        this.add(button);
    }

}
