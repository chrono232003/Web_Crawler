package crawlthread;

import crawlutils.CrawlInit;
import crawlutils.CrawlUtils;
import main.RandomUrlBuilder;
import org.jsoup.nodes.Document;
import utils.EnumUtils;

import java.util.HashMap;

public class CrawlThreadFunctionInnerUrlCrawl extends Thread {

    private HashMap<String, Boolean> urlMap = new HashMap<String, Boolean>();

    //constructor if argument is passed in
    public CrawlThreadFunctionInnerUrlCrawl(HashMap<String, Boolean> urlMap) {
        this.urlMap = urlMap;
    }


    public void run()
    {
        try {
            recusivelyUpdateUrlList(new CrawlUtils(), urlMap);
        }
        catch(Exception e)
            {
                // Throwing an exception
                System.out.println("Exception is caught");
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
                //urlMap = crawlUtils.updateUrlList(doc, urlMap, initialUrl);
            }
            recusivelyUpdateUrlList(crawlUtils, urlMap);
        }
        return urlMap;
    }

}
