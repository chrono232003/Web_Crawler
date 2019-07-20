package tests.crawlthread;

import crawlthread.CrawlThread;
import org.junit.Before;
import org.junit.Test;

public class CrawlThreadTest {

    private final int NUMBER_OF_THREADS = 5;
    CrawlThread threads;

    @Before
    public void setUp() {
        threads = new CrawlThread(NUMBER_OF_THREADS);
    }

    @Test
    public void setupThreads() {
        threads.createThreadsForRandomUrlEmailSearch();
    }
}
