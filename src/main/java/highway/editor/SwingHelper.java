package highway.editor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by shohei.miyashita on 5/23/16.
 */
public class SwingHelper {

    public static JTextField createTextField() {
        JTextField field = new JTextField();
        field.setSize(350, 30);
        field.setMaximumSize(new Dimension(350, 30));
        return field;
    }

}
