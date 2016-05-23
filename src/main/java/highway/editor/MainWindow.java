package highway.editor;

import highway.editor.panel.ClothoidPanelContainer;
import highway.editor.panel.ComponentEditorManager;
import highway.editor.panel.LinePanelContainer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shohei.miyashita on 5/20/16.
 */
public class MainWindow implements Repainter {
    private final Color MENU_BACKGROUND_COLOR = new Color(250, 250, 250);

    private JFrame mainFrame;
    private JSplitPane splitPane;

    private MapPanel mapPanel;

    private EditorPanel editorPanel;

    public MainWindow() {
        mainFrame = new JFrame();

        mainFrame.setTitle("高速道路エディタ");
        mainFrame.setBounds(0, 0, 1024, 576);

        GridLayout layout = new GridLayout();
        mainFrame.setLayout(layout);

        splitPane = new JSplitPane();

        MapController controller = new MapController();

        mapPanel = new MapPanel(controller);

        editorPanel = new EditorPanel(new ComponentEditorManager[] {
                new ComponentEditorManager("直線", this, new LinePanelContainer(controller)),
                new ComponentEditorManager("クロソイド曲線", this, new ClothoidPanelContainer(controller)),
        });

        splitPane.setRightComponent(mapPanel);

        setContentPanel(editorPanel);

        mainFrame.setContentPane(splitPane);
        mainFrame.setVisible(true);
    }

    public void repaint() {
        mapPanel.repaint();
        mainFrame.repaint();
    }

    public void backToMenu() {
        setContentPanel(editorPanel);
    }

    public void setContentPanel(Component component) {
        component.setMaximumSize(new Dimension(300, 1000));
        component.setMinimumSize(new Dimension(100, 1000));
        component.setPreferredSize(new Dimension(200, 1000));
        component.setBackground(MENU_BACKGROUND_COLOR);
        splitPane.setLeftComponent(component);
    }

}
