package highway.editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shohei.miyashita on 5/21/16.
 */
public class LineEditorPanel extends JPanel {

    private String mode;

    private String modeTypes[];
    private JButton buttons[];

    public LineEditorPanel(final MapController controller, final Repainter mapRepainter) {

        modeTypes = new String[] {
                "Line",
                "Clothoid",
                "Circle"
        };
        buttons = new JButton[modeTypes.length];

        mode = modeTypes[0];

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);

        this.add(new JLabel("長さ"));

        final JTextField lengthField = SwingHelper.createTextField();
        this.add(lengthField);

        final JButton button = new JButton("適用");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = Integer.parseInt(lengthField.getText());
                controller.drawLine(length);
                mapRepainter.repaint();
            }
        });
        this.add(button);
    }

}
