package main;

import org.jsoup.Jsoup;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Document;

public class Crawl {

    String url;

    //constructor (Takes the initial URL as a param)
    public Crawl(String urlString) {
        url = urlString;

        getDocumentFromUrl(url);
    }



    private Document getDocumentFromUrl(String url) {

        //handle a malformed url exception before entering the 3rd party jsoup logic.
        try {
           new URL(url);
        } catch (MalformedURLException e) {
            ExceptionHandling.handleMalformedURLException("Malformed Url exception in getDocumentFrom Url method: ", e);
        }

        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
            System.out.println(doc);

        } catch (Exception e) {
            ExceptionHandling.handleException("Error attempting to get the document from a Url: ", e);
        }

        return doc;

    }


}
