import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Crawl {

    URL url;

    //constructor (Takes the initial URL as a param)
    Crawl(String urlString) {

        //handle malformed URL exception
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {

            ExceptionHandling.handle("Malformed URL: " + urlString, e);

        }

        getDocumentFromUrl(url);

    }

    private Document getDocumentFromUrl(URL url) {

        Document doc = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //DocumentBuilder db = dbf.newDocumentBuilder();
            //doc = db.parse(url.openStream());
            Document doc = Jsoup.connect("http://example.com/").get();
            //NodeList nodes = doc.getElementsByTagName("row");
            System.out.println(doc);

        } catch(IOException e) {
            ExceptionHandling.handle("Url IO exception: ", e);
        } catch (Exception e) {
            ExceptionHandling.handle("Error attempting to get the document from a Url: ", e);
        }

        return doc;

    }


}
