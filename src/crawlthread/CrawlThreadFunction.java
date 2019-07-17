package crawlthread;

import utils.CrawlUtils;
import utils.EnumUtils;
import main.UrlBuilder;

public class CrawlThreadFunction extends Thread {
    public void run()
    {
        try
        {
            UrlBuilder builder = new UrlBuilder();
            int i = 0;
            while(i < 10000) {
                CrawlUtils crawlUtils = new CrawlUtils(builder.getUrl(), EnumUtils.SearchType.EMAILS);
                crawlUtils.init();
                i++;
            }

        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }
}
