package com.demo.notify;

/**
 * Created with IntelliJ IDEA.
 * User: Blank
 * Date: 14-3-28
 * Time: 下午7:49
 */
public class TestJoin implements Runnable {

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(new TestJoin());
        Thread t2 = new Thread(new TestJoin());
        t1.setName("t1");
        t1.start();


        t2.setName("t2");
        t2.join(3000);
        t2.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+": start ...");
    }
}