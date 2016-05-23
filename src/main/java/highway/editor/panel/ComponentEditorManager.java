package highway.editor.panel;

import highway.editor.MainWindow;
import highway.editor.MapController;
import highway.editor.Repainter;
import highway.editor.SwingHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class ComponentEditorManager {

    private String name;

    public String getName() { return name; }

    private MainWindow mainWindow;
    private ComponentEditor editor;

    public ComponentEditorManager(String name, final MainWindow mainWindow, final ComponentEditor editor) {
        this.mainWindow = mainWindow;
        this.editor = editor;
        this.name = name;

        JButton applyButton = new JButton("適用する");
        JButton returnButton = new JButton("戻る");

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.onApply();
                mainWindow.repaint();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.backToMenu();
            }
        });

        editor.add(applyButton);
        editor.add(returnButton);
    }

    public void go() {
        mainWindow.setContentPanel(editor.asComponent());
    }
}
