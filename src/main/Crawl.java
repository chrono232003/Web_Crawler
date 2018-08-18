package main;

import java.util.Scanner;

public class Crawl {

    //constructor (Takes the initial URL as a param)
    public Crawl(String initialUrl) {

        //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
        CrawlUtils crawlUtils = new CrawlUtils();
        crawlUtils.init(initialUrl);

    }

}
