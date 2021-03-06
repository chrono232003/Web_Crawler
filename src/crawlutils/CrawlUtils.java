package crawlutils;

import main.ExceptionHandling;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.EnumUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlUtils {

    EnumUtils.SearchType crawlType;

    public CrawlUtils() {}

    public CrawlUtils(EnumUtils.SearchType crawlType) {
        this.crawlType = crawlType;
    }

    public Document getDocumentFromUrl(String url) {

        //handle a malformed url exception before entering the 3rd party jsoup logic.
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            ExceptionHandling.handleMalformedURLException("Malformed Url exception in getDocumentFrom Url method: ", e);
        }

        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            ExceptionHandling.handleException("This page is either not found or not accessible: ", e);
            return null;
        } catch (Exception e) {
            ExceptionHandling.handleException("Error attempting to get the document from a Url: " + url, e);
            return null;
        }

        return doc;

    }

    public HashMap updateUrlList(Document doc, HashMap<String, Boolean> urls, String initialUrl) {

        //grab all links from the document and store within the urlMap
        Elements linksOnPage = doc.select("a[href]");
        for (Element link : linksOnPage) {
            String linkString = link.absUrl("href");
            //though a hashmap has unique keys, we will still have to check for dups to not override the value.
            if (!linkString.equals("") && !urls.containsKey(linkString)) {
                urls.put(linkString, setUrlCrawlStatus(linkString, initialUrl));
            }
        }

//        //grab all images from the document and store within the urlMap
//        Elements imagesOnPage = doc.select("img");
//        for (Element image : imagesOnPage) {
//            String imageString = image.attr("src");
//            //set the image to true so that the iterator does not try to crawl the image url.
//            if (!imageString.equals("") && !urls.containsKey(imageString)) {
//                urls.put(imageString, true);
//            }
//
//        }

        return urls;
    }

    public String getEmailList(String urls) {
        System.out.println("Fetching the emails from the urls in the list...");
        //convert string into list
        String[] urlList = urls.split("\n");
        ArrayList<String> urlArrayList = new ArrayList<String>(Arrays.asList(urlList));
        HashMap<String, Boolean> emails = new HashMap<String, Boolean>();
        Iterator it = urlArrayList.iterator();
        while (it.hasNext()) {
            try {
                Document doc = getDocumentFromUrl((String) it.next());
                if (doc != null) {
                    Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
                    Matcher matcher = p.matcher(doc.text());
                    while (matcher.find()) {
                        //though a hashmap has unique keys, we will still have to check for dups to not override the value.
                        if (!matcher.group().equals("") && !emails.containsKey(matcher.group())) {
                            emails.put(matcher.group(), true);
                        }
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("The document could not be generated.");
            } catch (Exception e) {
                System.out.println("url failed to provide document: " + it.next());
            }
        }
        StringBuilder sb = new StringBuilder();
        System.out.println("Finished fetching emails");
        for ( String email : emails.keySet() ) {
                sb.append(email + "\n");
        }
        return sb.toString();
    }

    public String getNextListUrl(HashMap<String, Boolean> urls) {

        //make sure the the hashmap is populated as to avoid null pointer exceptions.
        if (urls != null && !urls.isEmpty()) {

            //check to see if the given key value is false. If so, it has net been crawled yet.. so return it.
            for (String key : urls.keySet()) {
                if (!urls.get(key)) {
                    return key;
                }
            }

            //if we hit this, we are done crawling. return null
            return null;
        } else {
            //if we hit this, there is not a list that was passed in. We should not hit this.
            return null;
        }

    }

    /**
     * UTILITIES
     */

    boolean initialUrlIsValidAndNotNull(String initialUrl) {
        try {
            new URL(initialUrl);
            System.out.println("URL is good");
            return true;
        } catch (MalformedURLException e) {
            System.out.println("URL is bad, aborting ...");
            ExceptionHandling.handleMalformedURLException("The input url is invalid.", e);
            return false;
        }
    }

    Boolean setUrlCrawlStatus(String urlString, String initialUrlString) {
        if (urlString != null && initialUrlString != null) {
            try {
                URL url = new URL(urlString);
                URL initialUrl = new URL(initialUrlString);

                // return true if different domain, false if not. Also check if url a file we do not want to crawl.
                return !(url.getHost().equals(initialUrl.getHost())) || checkForNonDocUrl(urlString);



            } catch (MalformedURLException e) {
               ExceptionHandling.handleMalformedURLException("The URL string passed into setURLStatus is malformed... we will skip this one.:" , e);
                return true;
            }
        } else {
            return true;
        }
    }

    String finalUrlList(HashMap<String, Boolean> urls) {
        //ArrayList<String> list = new ArrayList<String>();
        //list.addAll(urls.keySet());
        //return list;
        StringBuilder sb = new StringBuilder();
        for ( String url : urls.keySet() ) {
            sb.append(url + "\n");
        }
        return sb.toString();
    }

    //make sure that the urlString is not an image ir unsupported doc
    private boolean checkForNonDocUrl(String url) {

        boolean isImage = false;

        List<String> imageTypes = Arrays.asList("png", "jpg", "jpeg", "gif", "pdf");
        for (String type : imageTypes) {
            if (url.contains(type)) {
                isImage = true;
            }
        }

        return isImage;

    }

//    private void writeToFile(String content) {
//        try {
//            File file = new File("C:/Users/chron/Documents/urls.txt");
//            StringBuilder fileContext = new StringBuilder(FileUtils.readFileToString(file, "UTF-8"));
//            fileContext.append(content);
//            FileUtils.write(file, fileContext.toString(), "UTF-8");
//        } catch (IOException e) {
//            System.out.println("there was an error saving to the file: " + e);
//        }
//    }

    String generatedResponse(HashMap<String, Boolean> urlMap) {

        //now that we have the final url list... see if we need the emails instead.
        if (crawlType == EnumUtils.SearchType.URLS) {
            return finalUrlList(urlMap);
        } else if (crawlType == EnumUtils.SearchType.EMAILS) {
            String finalEmailList = getEmailList(finalUrlList(urlMap));
            return finalEmailList;
        } else if (crawlType == EnumUtils.SearchType.CRAWL_EMAILS) {
            String finalEmailList = getEmailList(finalUrlList(urlMap));
            if (!(finalEmailList.equals("{}") || finalEmailList.equals(""))) {
                try {
//                    Path path = FileSystems.getDefault().getPath("./emails.txt");
//                    File file = new File(path.toString());
//                    FileWriter fr = new FileWriter(file, true);
//                    BufferedWriter br = new BufferedWriter(fr);
//                    PrintWriter pr = new PrintWriter(br);
//                    pr.println(finalEmailList);
//                    pr.close();
//                    br.close();
//                    fr.close();
                    OutputStream os = null;
                    try {
                       os = new FileOutputStream(new File("./emails.txt"));
                       os.write(finalEmailList.getBytes(), 0, finalEmailList.length());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return finalEmailList;
        } else {
            return "Something went wrong.";
        }
    }

}
