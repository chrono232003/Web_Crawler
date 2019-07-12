package main;

import java.awt.*;
import java.awt.event.*;

public class Gui extends Frame {
    Gui() {
        setTitle("Renegade Web Crawler");
        setLayout(new GridLayout());
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Label lb = new Label("Enter URL to Crawl");
        add(lb);

        TextField t = new TextField();
        t.setColumns(30);
        add(t);

        Button b = new Button("Crawl");
        TextArea result = new TextArea();
        //add button functionality
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
                CrawlUtils crawlUtils = new CrawlUtils();
                result.setText(crawlUtils.init(t.getText()));
            }
        });
        add(b);
        add(result);

        Label copyright = new Label();
        copyright.setText("Copyright Renegade Web Design - All Rights Reserved.");
        add(copyright);

        Label link = new Label("http://renegadewebdesign.com");
        add(link);

        //setting frame size
        setSize(600, 400);

        //Setting the layout for the Frame
        setLayout(new FlowLayout());

        setVisible(true);
    }

}

