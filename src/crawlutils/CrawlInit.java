package crawlutils;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import crawlthread.CrawlThread;
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

        //create threads to crawl quickly
        CrawlThread thread = new CrawlThread(20);
        return thread.CallableInnerThreadUrlSearch(urlMap, initialUrl);
    }

}
