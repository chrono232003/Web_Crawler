package ui;

import javax.swing.*;
import java.util.ArrayList;

public class UiObjectBuilder extends UiObject {

    JFrame frame;

    UiObjectBuilder(JFrame frame) {
        this.frame = frame;
    }

     void addLabel(String labelText, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelText);
        baseComponent(label, x, y, width, height);
        frame.add(label);
    }

    void addComboBox(String name,  ArrayList<String> items, int x, int y, int width, int height) {
        JComboBox dropDown = new JComboBox();
        baseComponent(dropDown, name, x, y, width, height);
        for (String item : items) {
            dropDown.addItem(item);
        }
        frame.add(dropDown);
    }

    void addTextField(String name, int x, int y, int width, int height) {
        JTextField text = new JTextField();
        baseComponent(text, name, x, y, width, height);
        frame.add(text);
    }

    void addButton(String buttonText, String buttonName, int x, int y, int width, int height) {
        JButton button = new JButton(buttonText);
        baseComponent(button, buttonName, x, y, width, height);
        frame.add(button);
    }

    void addTextArea(String name, int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");
        JScrollPane scroll = new JScrollPane (textArea);
        baseComponent(scroll, name, x, y, width, height);
        frame.add(scroll);
        //frame.add(textArea);
    }

}
