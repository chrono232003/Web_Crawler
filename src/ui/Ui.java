package ui;

import crawlthread.CrawlThread;
import utils.CrawlUtils;
import utils.EnumUtils;
import main.UrlBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ui{
    private JFrame frame;
    private final String TITLE = "Renegade Web Crawler";
    private final String SEARCH_TYPE_LABEL = "Search Type";
    private final String URL_SEARCH_LABEL = "Enter URL to crawl";

    //component names
    private final String CRAWL_BUTTON = "CrawlButton";
    private final String URL_INPUT = "BaseUrlInput";
    private final String RESULT_TEXT_AREA = "results";

    public Ui() {
        frame = new JFrame();
        frame.setTitle(TITLE);

        UiObjectBuilder.addLabel(frame, SEARCH_TYPE_LABEL, 10, 10, 150, 40);

        JComboBox dropDown = new JComboBox();
        //text.setColumns(30);
        dropDown.setBounds(150,10,200, 40);
        dropDown.addItem(EnumUtils.SearchType.URLS.type);
        dropDown.addItem(EnumUtils.SearchType.EMAILS.type);
        dropDown.addItem(EnumUtils.SearchType.CRAWL_EMAILS.type);
        dropDown.setVisible(true);
        frame.add(dropDown);

        UiObjectBuilder.addLabel(frame, URL_SEARCH_LABEL, 10, 60, 150, 40);
        UiObjectBuilder.addTextField(frame,URL_INPUT,150, 60, 200, 40);
        UiObjectBuilder.addButton(frame, "Crawl", CRAWL_BUTTON, 320, 60, 100, 40);
        UiObjectBuilder.addTextArea(frame, RESULT_TEXT_AREA, 10, 200, 500, 300);

        JTextArea textArea = (JTextArea) UiObjectGetter.searchAndRetrieveObjectByName(frame, RESULT_TEXT_AREA);
        JButton button = (JButton) UiObjectGetter.searchAndRetrieveObjectByName(frame, CRAWL_BUTTON);

        dropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
                        textArea.setText("crawling the web for emails. The results will be stored in a local text file");
                        i++;
                    }
                } else {
                    JTextField text = (JTextField) UiObjectGetter.searchAndRetrieveObjectByName(frame, URL_INPUT);
                    String urlFieldValue = text.getText();
//                    for (Component comp : frame.getContentPane().getComponents()) {
//                        if (comp.getName() != null && comp.getName().equals("BaseUrlInput")) {
//                            JTextField text = (JTextField) comp;
//                            urlFieldValue = text.getText();
//                        }
//                    }
                    System.out.println("this is the url text value: " + urlFieldValue);
                    //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
                    CrawlUtils crawlUtils = new CrawlUtils(urlFieldValue, EnumUtils.SearchType.valueOf((String)dropDown.getSelectedItem()));
                    textArea.setText(crawlUtils.init());
                }
            }
        });

        frame.setSize(600,500);
        //frame.setLayout(new GridLayout());//using no layout managers
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
