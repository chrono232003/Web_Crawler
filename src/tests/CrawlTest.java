package tests;

import main.Crawl;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;


public class CrawlTest {

    @Test
    public void testCrawlSuccess() {

        //returns an arraylist of all urls that are found from the provided domain.
        new Crawl("http://textureshair.net/");

    }

    @Test
    public void testCrawlMalformedInitalUrl() {

        //returns an arraylist of all urls that are found from the provided domain.
        new Crawl("htps://google.com");

    }

}
