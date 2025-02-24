package Threads;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.tomcat.util.net.jsse.JSSEUtil;

public class test {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        ThreadImpl t1 = new ThreadImpl(counter);
        ThreadImpl t2 = new ThreadImpl(counter);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (Exception e){

        }
        System.out.println(counter.getCount()); // Expected: 2000, Actual will be random <= 2000
    }

}
