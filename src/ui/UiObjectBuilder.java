package ui;

import javax.swing.*;
import java.util.ArrayList;

public class UiObjectBuilder extends UiObject {

    JFrame frame;

    UiObjectBuilder(JFrame frame) {
        this.frame = frame;
    }

     void addLabel(String labelText, String name, Boolean initialVisibility, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelText);
        baseComponent(label, name, initialVisibility, x, y, width, height);
        frame.add(label);
    }

    void addComboBox(String name, Boolean initialVisibility, ArrayList<String> items, int x, int y, int width, int height) {
        JComboBox dropDown = new JComboBox();
        baseComponent(dropDown, name, initialVisibility, x, y, width, height);
        for (String item : items) {
            dropDown.addItem(item);
        }
        frame.add(dropDown);
    }

    void addTextField(String name, Boolean initialVisibility, int x, int y, int width, int height) {
        JTextField text = new JTextField();
        baseComponent(text, name, initialVisibility, x, y, width, height);
        frame.add(text);
    }

    void addButton(String buttonText, Boolean initialVisibility,String buttonName, int x, int y, int width, int height) {
        JButton button = new JButton(buttonText);
        baseComponent(button, buttonName, initialVisibility, x, y, width, height);
        frame.add(button);
    }

    void addTextArea(String name, Boolean initialVisibility, int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        JScrollPane scroll = new JScrollPane (textArea);
        baseComponent(scroll, name, initialVisibility, x, y, width, height);
        frame.add(scroll);
        //frame.add(textArea);
    }

}
