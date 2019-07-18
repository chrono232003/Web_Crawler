package ui;
import java.awt.*;

public class UiObject {
    Component baseComponent(Component component, int x, int y, int width, int height) {
        component.setBounds(x,y,width,height);
        component.setVisible(true);
        return component;
    }

    Component baseComponent(Component component, String name, int x, int y, int width, int height) {
        component.setBounds(x,y,width,height);
        component.setName(name);
        component.setVisible(true);
        return component;
    }
}
