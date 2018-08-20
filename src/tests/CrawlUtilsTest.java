package tests;

import main.CrawlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CrawlUtilsTest {

    @Test
    public void testGetDocumentMethod() {
        CrawlUtils crawlUtils = new CrawlUtils();
        Document doc = crawlUtils.getDocumentFromUrl("http://google.com");

        assert doc != null;
        assert doc.title() != null;
    }

    @Test
    public void testUpdateUrlList() {

        HashMap<String, Boolean> urlMap = new HashMap<String, Boolean>();

        StringBuilder contentBuilder = new StringBuilder();
        try {
            File file = new File("src/tests/testHTML.html");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }

        String htmlString = contentBuilder.toString();
        Document doc = Jsoup.parse(htmlString);

        CrawlUtils crawlUtils = new CrawlUtils();
        urlMap = crawlUtils.updateUrlList(doc, urlMap);


        System.out.println(urlMap);

        assert urlMap.containsKey("http://www.example.com");
    }

    @Test
    public void testGetNextListUrl() {

        HashMap<String, Boolean> urlMap = new HashMap<String, Boolean>();
        urlMap.put("http://example.com", false);
        urlMap.put("http://example2.com", true);

        CrawlUtils crawlUtils = new CrawlUtils();
        String url = crawlUtils.getNextListUrl(urlMap);

        assert url.equals("http://example.com");
    }

}
