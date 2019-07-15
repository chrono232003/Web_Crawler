package crawlthread;

public class CrawlThread {

    private int numberOfThreads;

    public CrawlThread(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public void createThreads() {
        for (int i = 0; i < numberOfThreads; i++) {
            CrawlThreadFunction object = new CrawlThreadFunction();
            object.start();
        }
    }
}
