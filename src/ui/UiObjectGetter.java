package ui;

import javax.swing.*;
import java.awt.*;

public class UiObjectGetter {
    static Component searchAndRetrieveObjectByName(JFrame frame, String componentName) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp.getName() != null && comp.getName().equals(componentName)) {
//                JTextField text = (JTextField) comp;
//                urlFieldValue = text.getText();
                return comp;
            }
        }
        //hopefully we don't hit this
        return null;
    }
}
