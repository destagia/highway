package highway.editor;

import highway.editor.panel.ComponentEditorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shohei.miyashita on 5/21/16.
 */
public class EditorPanel extends JPanel {

    public EditorPanel(final ComponentEditorManager managers[]) {

        JButton buttons[] = new JButton[managers.length];

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(boxlayout);

        for (int i = 0; i < managers.length; i++) {
            final int index = i;
            JButton btn = new JButton(managers[i].getName());
            btn.setFont(new Font("Serif", Font.PLAIN, 18));
            buttons[i] = btn;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    managers[index].go();
                }
            });
            this.add(btn);
        }
    }

}