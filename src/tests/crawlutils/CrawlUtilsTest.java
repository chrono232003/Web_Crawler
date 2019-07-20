package tests.crawlutils;

import crawlutils.CrawlInit;
import crawlutils.CrawlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import utils.EnumUtils;

public class CrawlUtilsTest {
    public static class CrawlUtilsTestTest {

        private CrawlUtils crawlUtils;
        private final String TEST_URL = "http://google.com";

        @Before
        public void setUp() {
            crawlUtils = new CrawlUtils(EnumUtils.SearchType.URLS);
        }

        @Test
        public void testGetDocumentMethod() {
            Document doc = crawlUtils.getDocumentFromUrl(TEST_URL);

            assert doc != null;
            assert doc.title() != null;
        }

        @Test
        public void testGetDocumentMethodExceptionHanding() {
            Document doc = crawlUtils.getDocumentFromUrl(TEST_URL);

            assert doc != null;
            assert doc.title() != null;
        }

       @Test
        public void testUpdateEmailList() {

            StringBuilder sb = new StringBuilder();
            sb.append("https://renegadewebdesign.com/\n");
            sb.append("https://renegadewebdesign.com/templates/one-page-smooth-theme/img/portfolio/fullsize/5.jpg\n");
             String testUrlStrings = sb.toString();
            String emails = crawlUtils.getEmailList(testUrlStrings);


            System.out.println("email map:" + emails);

            assert emails.contains("chrono232003@yahoo.com");
            assert emails.contains("heyyooo@gmail.com");
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

            urlMap = crawlUtils.updateUrlList(doc, urlMap,"http://www.example.com");


            System.out.println(urlMap);

            assert urlMap.containsKey("http://www.example.com");
        }

        @Test
        public void testUpdateUrlListBadLinkImageHandling() {

            HashMap<String, Boolean> urlMap = new HashMap<String, Boolean>();

            StringBuilder contentBuilder = new StringBuilder();
            try {
                File file = new File("src/tests/testBadHTML.html");
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

            urlMap = crawlUtils.updateUrlList(doc, urlMap, "http://www.example.com");


            System.out.println(urlMap);

        }


        @Test
        public void testGetNextListUrl() {

            HashMap<String, Boolean> urlMap = new HashMap<String, Boolean>();
            urlMap.put("http://example.com", false);
            urlMap.put("http://example2.com", true);

            String url = crawlUtils.getNextListUrl(urlMap);
            System.out.println("this is the url returned: " +  url);
            assert url.equals("http://example.com");
        }

    }
}
