package tests;

import main.Crawl;
import org.junit.jupiter.api.Test;


public class CrawlTest {

    @Test
    public void testCrawlSuccess() {
        new Crawl("http://google.com");
    }

}
