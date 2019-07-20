package ui;
import java.awt.*;

public class UiObject {
    //overload base component to handle many different param configs

    //without name parameter
    Component baseComponent(Component component, Boolean initialVisibility,  int x, int y, int width, int height) {
        component.setBounds(x,y,width,height);
        component.setVisible(initialVisibility);
        return component;
    }

    //with name parameter
    Component baseComponent(Component component, String name, Boolean initialVisibility, int x, int y, int width, int height) {
        component.setBounds(x,y,width,height);
        component.setName(name);
        component.setVisible(initialVisibility);
        return component;
    }
}
