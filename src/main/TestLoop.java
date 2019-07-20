package main;

import crawlthread.CrawlThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestLoop extends Thread {

    public static void main(String[] args){
        CrawlThread thread = new CrawlThread(10);
        List list = Collections.synchronizedList(new ArrayList());
        for (int i = 0; i < 500; i++) {
            list.add(i);
        }

        thread.createThreadsTest(list);
        System.out.println("this is the list: " + list);

    }

}
