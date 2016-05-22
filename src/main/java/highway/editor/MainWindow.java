package highway.editor;

import highway.editor.panel.ClothoidEditorPanel;
import highway.editor.panel.EditorPanel;
import highway.editor.panel.LineEditorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shohei.miyashita on 5/20/16.
 */
public class MainWindow implements Repainter {
    private final Color MENU_BACKGROUND_COLOR = new Color(250, 250, 250);

    private JFrame mainFrame;

    private JSplitPane splitPane;

    private EditorPanel editorPanel;
    private LineEditorPanel lineEditorPanel;
    private ClothoidEditorPanel clothoidEditorPanel;

    private MapPanel mapPanel;

    public MainWindow() {
        mainFrame = new JFrame();

        mainFrame.setTitle("高速道路エディタ");
        mainFrame.setBounds(0, 0, 1024, 576);

        GridLayout layout = new GridLayout();
        mainFrame.setLayout(layout);

        splitPane = new JSplitPane();

        MapController controller = new MapController();

        mapPanel = new MapPanel(controller);

        editorPanel = new EditorPanel();
        lineEditorPanel = new LineEditorPanel(controller, this);
        clothoidEditorPanel = new ClothoidEditorPanel(controller, this);

        splitPane.setRightComponent(mapPanel);

        editorPanel.addActionListener("Line", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(lineEditorPanel);
            }
        });

        editorPanel.addActionListener("Clothoid", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(clothoidEditorPanel);
            }
        });

        setContentPane(editorPanel);

        mainFrame.setContentPane(splitPane);
        mainFrame.setVisible(true);
    }

    public void repaint() {
        mapPanel.repaint();
        mainFrame.repaint();
    }

    private void setContentPane(Component component)
    {
        component.setMaximumSize(new Dimension(300, 1000));
        component.setMinimumSize(new Dimension(100, 1000));
        component.setPreferredSize(new Dimension(200, 1000));
        component.setBackground(MENU_BACKGROUND_COLOR);
        splitPane.setLeftComponent(component);
    }


}
