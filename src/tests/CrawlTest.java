package tests;

import main.Crawl;
import org.junit.jupiter.api.Test;


public class CrawlTest {

    @Test
    public void testCrawlSuccess() {

        //returns an arraylist of all urls that are found from the provided domain.
        new Crawl("https://alexanderealestate.com");
    }

}
