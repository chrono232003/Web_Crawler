import org.junit.jupiter.api.Test;

public class CrawlTest {

    @Test
    public void testCrawlSuccess() {

        Crawl crawl = new Crawl("http://greatoldgames.com");

    }

    @Test
    public void testCrawlMalformedURLException() {

        Crawl crawl = new Crawl("htt://google.com");

    }

}
