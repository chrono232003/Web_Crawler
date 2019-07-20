package crawlthread;

import main.TestLoop;

import java.util.List;

public class ThreadFunctionTest extends Thread {

    List list;

    ThreadFunctionTest(List list) {
        this.list = list;
    }

    public void run() {
        while (list.size() > 0) {
            list.remove(list.size() - 1);
            System.out.println(getName() + "size at this point: " + Integer.toString(list.size()));
        }
    }
}

