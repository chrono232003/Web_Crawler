# Web_Crawler
A web crawler that grabs and lists internal and external links on a website.

This program was developed to take in a url as a parameter and find all links with in a domain.

To Run:

This program has a generated jar file under the out directory that can be ran from the command prompt
IE "..\Web_Crawler\out\artifacts\Web_Crawler_jar\java -jar Web_Crawler.jar"

/***** Technical Specs *****/

The power behind this program is an API library called "jsoup" which allows easy extraction of a webpage source DOM. Within, we are able to extract information such as urls and links.

The program is started under the Crawl class which uses other utility classes such as CrawlUtils and a exception class for custom handling called ExceptionHandling.

/****************************/

If I had a bit more time, I would allow this program to be executed from the command line or make it an executable jar file vs through the test class.

Also, I may work toward making the search a little more efficient by weeding out unneeded query strings on urls and optimize the search algorithm.
