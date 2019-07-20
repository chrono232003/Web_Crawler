package crawlutils;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.jsoup.nodes.Document;
import utils.EnumUtils;

import java.io.*;
import java.util.HashMap;

public class CrawlInit {

    //create a hashmap to store the url as key and isVisited as value. Also, a hashmap will prevent duplicate urls.
    private HashMap<String, Boolean> urlMap = new HashMap<String, Boolean>();
    private String initialUrl;
    private EnumUtils.SearchType crawlType;

    public CrawlInit(String initialUrl, EnumUtils.SearchType crawlType) {
        this.initialUrl = initialUrl;
        this.crawlType = crawlType;
    }

    public String init() {

        System.out.println("Starting the process with " + initialUrl + " ...");

        //get the crawl utils class
        CrawlUtils crawlUtils = new CrawlUtils(crawlType);

        //check the initial url for validity
        if (crawlUtils.initialUrlIsValidAndNotNull(initialUrl)) {

        //initiate the iteration through the urlMap with the first initial url
        urlMap.put(initialUrl, false);

        System.out.println("Fetching All the URLS on the website...");

        HashMap<String, Boolean> completedUrlMap = recusivelyUpdateUrlList(crawlUtils, urlMap);

        return crawlUtils.generatedResponse(urlMap);

        } else {
        return "Invalid url, please correct or enter another.";
        }
    }

    HashMap<String, Boolean> recusivelyUpdateUrlList(CrawlUtils crawlUtils, HashMap<String, Boolean> urlMap) {
        Document doc;
        String nextUrl = crawlUtils.getNextListUrl(urlMap);

        if(nextUrl != null) {
            //set the url value to true so that it does not get crawled again.
            urlMap.put(nextUrl, true);

            //get document from url or null if unable to acquire.
            doc = crawlUtils.getDocumentFromUrl(nextUrl);

            //check if a document was acquired before updating the url list.
            if (doc != null) {
                urlMap = crawlUtils.updateUrlList(doc, urlMap, initialUrl);
            }
            recusivelyUpdateUrlList(crawlUtils, urlMap);
        }
        return urlMap;
    }

}
