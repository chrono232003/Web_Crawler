package ui;

import crawlthread.CrawlThread;
import crawlutils.CrawlInit;
import utils.EnumUtils;

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
    private final String SEARCH_TYPE_LABEL_NAME = "SearchTypeLabel";
    private final String URL_SEARCH_LABEL_NAME = "UrlSearchLabel";
    private final String CRAWL_BUTTON = "CrawlButton";
    private final String URL_INPUT = "BaseUrlInput";
    private final String RESULT_TEXT_PANE = "results";
    private final String SEARCH_TYPE_DD_NAME = "SearchTypeDropDown";

    public Ui() {
        frame = new JFrame();
        frame.setTitle(TITLE);

        UiObjectBuilder componentBuilder = new UiObjectBuilder(frame);

        ArrayList<String> dropdownValues = new ArrayList<>();
        dropdownValues.add("");
        dropdownValues.add(EnumUtils.SearchType.URLS.type);
        dropdownValues.add(EnumUtils.SearchType.EMAILS.type);
        dropdownValues.add(EnumUtils.SearchType.CRAWL_EMAILS.type);

        componentBuilder.addLabel(SEARCH_TYPE_LABEL, SEARCH_TYPE_LABEL_NAME, true, 10, 10, 150, 40);
        componentBuilder.addComboBox(SEARCH_TYPE_DD_NAME,true, dropdownValues, 150, 10, 200, 40);
        componentBuilder.addLabel(URL_SEARCH_LABEL, URL_SEARCH_LABEL_NAME,false, 10, 60, 220, 40);
        componentBuilder.addTextField(URL_INPUT,false,150, 60, 220, 40);
        componentBuilder.addButton("Crawl", false, CRAWL_BUTTON, 380, 60, 100, 40);
        componentBuilder.addTextArea(RESULT_TEXT_PANE, true, 10, 200, 580, 300);

        JLabel searchLabel = (JLabel) UiObjectGetter.searchAndRetrieveObjectByName(frame, URL_SEARCH_LABEL_NAME);
        JTextField text = (JTextField) UiObjectGetter.searchAndRetrieveObjectByName(frame, URL_INPUT);
        JButton button = (JButton) UiObjectGetter.searchAndRetrieveObjectByName(frame, CRAWL_BUTTON);
        JComboBox dropDown = (JComboBox) UiObjectGetter.searchAndRetrieveObjectByName(frame, SEARCH_TYPE_DD_NAME);

        dropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String searchType = (String) dropDown.getSelectedItem();
                if (!searchType.equals("")) {
                    button.setVisible(true);
                }

                if (searchType.equals(EnumUtils.SearchType.EMAILS.type) || searchType.equals(EnumUtils.SearchType.URLS.type)) {
                    searchLabel.setVisible(true);
                    button.setVisible(true);
                    text.setVisible(true);

                    //get the textarea object from the scroll pane
                    JTextArea textArea = null;
                    for (Component comp : frame.getContentPane().getComponents()) {
                        if (comp.getName() != null && comp.getName().equals(RESULT_TEXT_PANE)) {
                            textArea = (JTextArea) UiObjectGetter.searchAndRetrieveObjectByName((JScrollPane) comp, "TextArea");
                        }
                    }
                     textArea.setVisible(true);
                } else {
                    searchLabel.setVisible(false);
                    text.setVisible(false);
                }
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
                    thread.createThreadsForRandomUrlEmailSearch();
                    textArea.setText("crawling the web for emails. The results will be stored in a local text file called emails.txt");
                } else {
                    String urlFieldValue = text.getText();

                    //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
                    CrawlInit crawlInit = new CrawlInit(urlFieldValue, EnumUtils.SearchType.valueOf((String)dropDown.getSelectedItem()));
                    textArea.setText("Grabbing the URLs from " + text.getText() + ". Please wait...");
                    textArea.setText(crawlInit.init());
                }
            }
        });

        frame.setSize(600,600);
        //frame.setLayout(new GridLayout());
        frame.setLayout(null); //using no layout managers
        frame.setVisible(true);
    }

}
