package ui;

import utils.EnumUtils;

import javax.swing.*;
import java.util.ArrayList;

public class UiObjectBuilder {

    static void addLabel(JFrame frame, String labelText, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x,y,width,height);
        label.setVisible(true);
        frame.add(label);
    }

    static void addComboBox(JFrame frame, ArrayList<String> items, int x, int y, int width, int height) {
        JComboBox dropDown = new JComboBox();
        dropDown.setBounds(x,y,width,height);
        dropDown.setVisible(true);
        for (String item : items) {
            dropDown.addItem(item);
        }
        frame.add(dropDown);
    }

    static void addTextField(JFrame frame, String name, int x, int y, int width, int height) {
        JTextField text = new JTextField();
        text.setName(name);
        text.setBounds(x,y,width,height);
        text.setVisible(true);
        frame.add(text);
    }

    static void addButton(JFrame frame, String buttonText, String buttonName, int x, int y, int width, int height) {
        JButton button = new JButton(buttonText);
        button.setName(buttonName);
        button.setBounds(x,y,width,height);
        button.setVisible(true);
        frame.add(button);
    }

    static void addTextArea(JFrame frame, String name, int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setName(name);
        textArea.setBounds(x,y,width,height);
        textArea.setVisible(true);
        frame.add(textArea);
    }

}
