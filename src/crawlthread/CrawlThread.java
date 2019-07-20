package crawlthread;

import utils.EnumUtils;

import java.util.ArrayList;
import java.util.List;

public class CrawlThread {

    private int numberOfThreads;

    public CrawlThread(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public void createThreadsForRandomUrlEmailSearch() {
        for (int i = 0; i < numberOfThreads; i++) {
            CrawlThreadFunction object = new CrawlThreadFunction();
            object.start();
        }
    }

    public void createThreadsTest(List list) {
        for (int i = 0; i < numberOfThreads; i++) {
            ThreadFunctionTest object = new ThreadFunctionTest(list);
            object.start();
        }
    }
}
