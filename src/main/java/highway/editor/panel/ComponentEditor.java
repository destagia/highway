package highway.editor.panel;

import java.awt.*;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public interface ComponentEditor {
    void onApply();

    void add(Component component);

    Component asComponent();
}
