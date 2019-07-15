package ui;

import crawlthread.CrawlThread;
import main.CrawlUtils;
import utils.EnumUtils;
import main.UrlBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ui {
    private JFrame frame;

    public Ui() {
        frame = new JFrame();
        frame.setTitle("Renegade Web Crawler");

        JLabel label = new JLabel("Enter URL to crawl.");
        label.setBounds(10,10,150, 40);
        label.setVisible(true);
        frame.add(label);

        JTextField text = new JTextField();
        //text.setColumns(30);
        text.setBounds(150,10,200, 40);
        text.setVisible(true);
        frame.add(text);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(10,200,500, 300);
        textArea.setVisible(true);
        frame.add(textArea);

        //JTextField text = createComponent(new JTextField(), 150, 10, 200, 40);

        JComboBox dropDown = new JComboBox();
        //text.setColumns(30);
        dropDown.setBounds(150,100,200, 40);
        dropDown.addItem(EnumUtils.SearchType.URLS.type);
        dropDown.addItem(EnumUtils.SearchType.EMAILS.type);
        dropDown.addItem(EnumUtils.SearchType.CRAWL_EMAILS.type);
        dropDown.setVisible(true);
        frame.add(dropDown);

        JButton button = new JButton("Crawl");
        button.setBounds(300,10,200, 40);
        button.setVisible(true);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dropDown.getSelectedItem().equals(EnumUtils.SearchType.CRAWL_EMAILS.type)) {
                    CrawlThread thread = new CrawlThread(20);
                    thread.createThreads();
                    UrlBuilder builder = new UrlBuilder();
                    int i = 0;
                    while(i < 1) {
                        CrawlUtils crawlUtils = new CrawlUtils(builder.getUrl(), EnumUtils.SearchType.EMAILS);
                        textArea.setText("hi");
                        i++;
                    }
                } else {
                    //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
                    CrawlUtils crawlUtils = new CrawlUtils(text.getText(), EnumUtils.SearchType.valueOf((String)dropDown.getSelectedItem()));
                    textArea.setText(crawlUtils.init());
                }
            }
        });

        frame.setSize(600,500);
        //frame.setLayout(new GridLayout());//using no layout managers
        frame.setLayout(null);
        frame.setVisible(true);
    }

//    private <T extends JComponent> T createComponent(Class<T> component, int x, int y, int width, int height) {
//        component.setBounds(10,100,500, 300);
//        component.setVisible(true);
//        //frame.add(textArea);
//        return component;
//    }
}
