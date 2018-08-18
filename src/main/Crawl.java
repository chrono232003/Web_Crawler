package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.jsoup.nodes.Document;

public class Crawl {

    String url;

    //create a hashmap to stor ethe url as key and isVisited as value. Also, a hashmap will prevent duplicate urls.
    HashMap urlMap = new HashMap();

    //constructor (Takes the initial URL as a param)
    public Crawl(String urlString) {
        url = urlString;
        Document doc = getDocumentFromUrl(url);
        urlMap = updateUrlList(doc, urlMap);

        System.out.println(urlMap);
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
        } catch (Exception e) {
            ExceptionHandling.handleException("Error attempting to get the document from a Url: ", e);
        }

        return doc;

    }

    private HashMap updateUrlList(Document doc, HashMap urls) {

        Elements linksOnPage = doc.select("a[href]");
        for (Element link : linksOnPage) {
            urls.put(link.absUrl("href"), false);
        }

        return urls;
    }


}
