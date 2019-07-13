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

        Choice c=new Choice();
        c.setBounds(100,100, 75,75);
        c.add(EnumUtils.Type.URLS.type);
        c.add(EnumUtils.Type.EMAILS.type);
        add(c);

        Button b = new Button("Crawl");
        TextArea result = new TextArea();
        //add button functionality
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
                CrawlUtils crawlUtils = new CrawlUtils(t.getText(), EnumUtils.Type.valueOf(c.getSelectedItem()));
                result.setText(crawlUtils.init());
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

