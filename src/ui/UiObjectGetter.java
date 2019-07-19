package ui;

import javax.swing.*;
import java.awt.*;

public class UiObjectGetter {

    static Component searchAndRetrieveObjectByName(JFrame frame, String componentName) {
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp.getName() != null && comp.getName().equals(componentName)) {
                return comp;
            }
        }
        //hopefully we don't hit this
        return null;
    }

    static Component searchAndRetrieveObjectByName(JScrollPane scrollPane, String componentName) {
        JViewport viewPort = scrollPane.getViewport();
        for (Component comp : viewPort.getComponents()) {
            System.out.println("these are the components inside the scrollPane: " + comp);
            if (comp.getName() != null && comp.getName().equals(componentName)) {
                return comp;
            }
        }
        //hopefully we don't hit this
        return null;
    }
}
