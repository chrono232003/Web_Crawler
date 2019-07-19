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
    private final String RESULT_TEXT_PANE = "results";
    private final String SEARCH_TYPE_DD_NAME = "SearchTypeDropDown";

    public Ui() {
        frame = new JFrame();
        frame.setTitle(TITLE);

        UiObjectBuilder componentBuilder = new UiObjectBuilder(frame);

        ArrayList<String> dropdownValues = new ArrayList<>();
        dropdownValues.add(EnumUtils.SearchType.URLS.type);
        dropdownValues.add(EnumUtils.SearchType.EMAILS.type);
        dropdownValues.add(EnumUtils.SearchType.CRAWL_EMAILS.type);

        componentBuilder.addLabel(SEARCH_TYPE_LABEL, 10, 10, 150, 40);
        componentBuilder.addComboBox(SEARCH_TYPE_DD_NAME, dropdownValues, 150, 10, 200, 40);
        componentBuilder.addLabel(URL_SEARCH_LABEL, 10, 60, 220, 40);
        componentBuilder.addTextField(URL_INPUT,150, 60, 220, 40);
        componentBuilder.addButton("Crawl", CRAWL_BUTTON, 380, 60, 100, 40);
        componentBuilder.addTextArea(RESULT_TEXT_PANE, 10, 200, 580, 300);

        JButton button = (JButton) UiObjectGetter.searchAndRetrieveObjectByName(frame, CRAWL_BUTTON);
        JComboBox dropDown = (JComboBox) UiObjectGetter.searchAndRetrieveObjectByName(frame, SEARCH_TYPE_DD_NAME);

        dropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //get the textarea object from the scroll pane
                JTextArea textArea = null;
                for (Component comp : frame.getContentPane().getComponents()) {
                    if (comp.getName() != null && comp.getName().equals(RESULT_TEXT_PANE)) {
                        textArea = (JTextArea) UiObjectGetter.searchAndRetrieveObjectByName((JScrollPane) comp, "TextArea");
                    }
                }

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

                    //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
                    CrawlUtils crawlUtils = new CrawlUtils(urlFieldValue, EnumUtils.SearchType.valueOf((String)dropDown.getSelectedItem()));
                    textArea.setText(crawlUtils.init());
                }
            }
        });

        frame.setSize(600,600);
        //frame.setLayout(new GridLayout());
        frame.setLayout(null); //using no layout managers
        frame.setVisible(true);
    }
}
