package main;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class CrawlUtils {

    //create a hashmap to store the url as key and isVisited as value. Also, a hashmap will prevent duplicate urls.
    HashMap<String, Boolean> urlMap = new HashMap<String, Boolean>();
    private String INITIAL_URL;

    void init(String initialUrl) {

        //check the initial url for validity
        if (initialUrlIsValid(initialUrl)) {

            //initaite the iteration through the urlMap with the first inital url
            if (initialUrl != null) {
                INITIAL_URL = initialUrl;
                urlMap.put(initialUrl, false);
            }


            while (true) {
                Document doc;

                String nextUrl = getNextListUrl(urlMap);

                if (nextUrl == null) {
                    break;
                }

                //set the url value to true so that it does not get crawled again.
                urlMap.put(nextUrl, true);

                //get document from url or null if unable to acquire.
                doc = getDocumentFromUrl(nextUrl);

                //check if a document was acquired before updating the url list.
                if (doc != null) {
                    urlMap = updateUrlList(doc, urlMap);
                }
            }

            System.out.println(finalUrlList(urlMap));

        }
    }

    public Document getDocumentFromUrl(String url) {

        //handle a malformed url exception before entering the 3rd party jsoup logic.
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            ExceptionHandling.handleMalformedURLException("Malformed Url exception in getDocumentFrom Url method: ", e, false);
        }

        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            ExceptionHandling.handleException("This page is either not found or not accessible: ", e);
            return null;
        } catch (Exception e) {
            ExceptionHandling.handleException("Error attempting to get the document from a Url: ", e);
            return null;
        }

        return doc;

    }

    public HashMap updateUrlList(Document doc, HashMap<String, Boolean> urls) {

        //grab all links from the document and store within the urlMap
        Elements linksOnPage = doc.select("a[href]");
            for (Element link : linksOnPage) {
                String linkString = link.absUrl("href");
                //though a hashmap has unique keys, we will still have to check for dups to not override the value.
                if (!urls.containsKey(linkString)) {
                    urls.put(linkString, setUrlCrawlStatus(linkString, INITIAL_URL));
                }
            }

        //grab all images from the document and store within the urlMap
        Elements imagesOnPage = doc.select("img");
        for (Element image : imagesOnPage) {
            String imageString = image.absUrl("href");
            //set the image to true so that the iterator does not try to crawl the image url.
                urls.put(imageString, true);

        }

            return urls;
    }

   public String getNextListUrl(HashMap<String, Boolean> urls) {

        //make sure the the hashmap is populated as to avoid null pointer exceptions.
        if (urls != null && !urls.isEmpty()) {

            String returnUrl = null;

            for (String key : urls.keySet()) {
                if (!urls.get(key)) {
                    returnUrl = key;
                    break;
                }
            }

            return returnUrl;
        } else {
            return null;
        }

    }

    /**
     * UTILITIES
     */

    private boolean initialUrlIsValid(String initialUrl) {
        try {
           URL url = new URL(initialUrl);
            return true;
        } catch (MalformedURLException e) {
            ExceptionHandling.handleMalformedURLException("The input url is invalid.", e, true);
            return false;
        }
    }

    private Boolean setUrlCrawlStatus(String urlString, String initialUrlString) {
        if (urlString != null && initialUrlString != null) {
            try {
                URL url = new URL(urlString);
                URL initialUrl = new URL(initialUrlString);

                // return true if different domain, false if not
                return !(url.getHost().equals(initialUrl.getHost()));

            } catch (MalformedURLException e) {
                ExceptionHandling.handleMalformedURLException("One or both of the url string spassed into setURLCrawlStatus is malformed:" , e, false);
                return null;
            }
        } else {
            return null;
        }
    }

    ArrayList finalUrlList(HashMap<String, Boolean> urls) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(urls.keySet());
        return list;
    }

}
