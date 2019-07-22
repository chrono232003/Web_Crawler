package crawlthread;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CrawlThread {

    private int numberOfThreads;

    public CrawlThread(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public void createThreadsForRandomUrlEmailSearch() {
        for (int i = 0; i < numberOfThreads; i++) {
            CrawlThreadFunctionRandomUrls object = new CrawlThreadFunctionRandomUrls();
            object.start();
        }
    }

    public void createThreadsForUrlSearch(HashMap<String, Boolean> urlMap) {
        for (int i = 0; i < numberOfThreads; i++) {
            CrawlThreadFunctionInnerUrlCrawl object = new CrawlThreadFunctionInnerUrlCrawl(urlMap);
            object.start();
        }
    }

    public HashMap<String, Boolean> CallableInnerThreadUrlSearch(HashMap<String, Boolean> urlMap, String initialUrl) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
            Future<HashMap<String, Boolean>> future = executor.submit(new CallableInnerThreadUrlSearch(urlMap, initialUrl));
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createThreadsTest(List list) {
        for (int i = 0; i < numberOfThreads; i++) {
            ThreadFunctionTest object = new ThreadFunctionTest(list);
            object.start();
        }
    }
}
