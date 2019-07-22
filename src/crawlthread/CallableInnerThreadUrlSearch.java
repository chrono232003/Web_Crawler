package crawlthread;

import crawlutils.CrawlUtils;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class CallableInnerThreadUrlSearch implements Callable {

    private HashMap<String, Boolean> urlMap;
    private String initialUrl;

    public CallableInnerThreadUrlSearch(HashMap<String, Boolean> urlMap, String initialUrl) {
        this.urlMap = urlMap;
        this.initialUrl = initialUrl;
    }

    public HashMap<String, Boolean> call()
    {
        HashMap<String, Boolean> updatedMap = null;

        try {
            updatedMap = recusivelyUpdateUrlList(new CrawlUtils(), urlMap);
        }
        catch(Exception e)
        {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
        return updatedMap;
    }

    private HashMap<String, Boolean> recusivelyUpdateUrlList(CrawlUtils crawlUtils, HashMap<String, Boolean> urlMap) {
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
