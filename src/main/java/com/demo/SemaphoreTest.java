package com.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable {

    // 需要指定信号量的准入数，相当于指定了同时有多少个线程可以同时访问某一个资源
    final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemaphoreTest test = new SemaphoreTest();
        for (int i = 0; i < 20; i++) {
            exec.execute(test);
        }


    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            // 模拟耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
