# Web_Crawler
A web crawler that grabs and lists internal and external links on a website.

This program was developed to take in a url as a parameter and find all links with in a domain.

You can run the program and return a list via the unit test "testCrawlSuccess()" by adding a domain of your choice and running in the IDE of your choice. The method in under src/tests/CrawlTest.

NOTE: This program uses a library called jsoup for document fetching and parsing. The jar file is included in the lib folder. You may need to add as a project resource in your IDE before using.

Since it is powered by JUnit and has the Test annotation, the IDE should allow run functionality.

ex:

@Test
public void testCrawlSuccess() {

    Crawl crawl = new Crawl("http://google.com");

}


/***** Technical Specs *****/

The power behind this program is an API library called "jsoup" which allows easy extraction of a webpage source DOM. Within, we are able to extract information such as urls and links.

The program is started under the Crawl class which uses other utility classes such as CrawlUtils and a exception class for custom handling called ExceptionHandling.

/****************************/

If I had a bit more time, I would allow this program to be executed from the command line or make it an executable jar file vs through the test class.

Also, I may work toward making the search a little more efficient by weeding out unneeded query strings on urls and optimize the search algorithm.
