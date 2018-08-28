package main;

import java.util.Scanner;

public class Crawl {


        public static void main(String[] args){

            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Enter a url: ");
            String initialUrl = reader.nextLine();
            reader.close();

            //spider through urls using the urlMap as an engine. It will always contain te initial url first and then spider from there.
            CrawlUtils crawlUtils = new CrawlUtils();
            crawlUtils.init(initialUrl);

        }

}
