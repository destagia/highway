package highway.editor.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by shohei.miyashita on 5/21/16.
 */
public class EditorPanel extends JPanel {

    private String mode;

    private String modeTypes[];
    private JButton buttons[];

    public EditorPanel() {

        modeTypes = new String[] {
                "Line",
                "Clothoid",
                "Circle"
        };
        buttons = new JButton[modeTypes.length];

        mode = modeTypes[0];

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(boxlayout);

        for (int i = 0; i < modeTypes.length; i++) {
            JButton btn = new JButton(modeTypes[i]);
            btn.setFont(new Font("Serif", Font.PLAIN, 18));
            buttons[i] = btn;
            this.add(btn);
        }
    }

    public void addActionListener(String mode, ActionListener listener) {
        int index = indexOfModeTypes(mode);
        if (index != -1) {
            buttons[index].addActionListener(listener);
        }
    }

    private int indexOfModeTypes(String mode) {
        for (int i = 0; i < modeTypes.length; i++) {
            if (modeTypes[i].equals(mode)) {
                return i;
            }
        }
        return -1;
    }
}