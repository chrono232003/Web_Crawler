package crawlthread;

import crawlutils.CrawlInit;
import utils.EnumUtils;
import main.RandomUrlBuilder;

public class CrawlThreadFunctionRandomUrls extends Thread {

    public CrawlThreadFunctionRandomUrls() {}


    public void run()
    {
        try {
            RandomUrlBuilder builder = new RandomUrlBuilder();
            int i = 0;
            while (i < 10000) {
                CrawlInit crawlInit = new CrawlInit(builder.getUrl(), EnumUtils.SearchType.CRAWL_EMAILS);
                crawlInit.init();
                i++;
            }

        }
        catch(Exception e)
            {
                // Throwing an exception
                System.out.println("Exception is caught");
            }
    }
}
