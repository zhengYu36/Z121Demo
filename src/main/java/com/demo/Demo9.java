package com.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <ul>
 * <li>???? : com.demo</li>
 * <li>???? : 2019/4/30 13:38</li>
 * <li>???? : ?</li>
 * </ul>
 * 测试类
 *
 * 参考url:  https://blog.csdn.net/shihuacai/article/details/8856370
 *
 * @author zhengyu
 */


public class Demo9 {

    public static void main(String[] args) throws InterruptedException {

        // 开始的倒数锁
        final CountDownLatch begin = new CountDownLatch(1);

        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(4);

        // 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(4);

        Runnable run = new Runnable() {
            public void run() {
                try {
                    System.out.println("我是第一个任务");
                    begin.await();
                } catch (InterruptedException e) {
                } finally {
                    // 每个选手到达终点时，end就减一
                    end.countDown();
                }
            }
        };

        Runnable run2 = new Runnable() {
            public void run() {
                try {
                    System.out.println("我是第2个任务");
                    begin.await();
                } catch (InterruptedException e) {
                } finally {
                    // 每个选手到达终点时，end就减一
                    end.countDown();
                }
            }
        };

        Runnable run3= new Runnable() {
            public void run() {
                try {
                    System.out.println("我是第3个任务");
                    begin.await();
                } catch (InterruptedException e) {
                } finally {
                    // 每个选手到达终点时，end就减一
                    end.countDown();
                }
            }
        };

        Runnable run4 = new Runnable() {
            public void run() {
                try {
                    System.out.println("我是第4个任务");
                    Thread.sleep((long) (Math.random() * 10000));
                    begin.await();
                } catch (InterruptedException e) {
                } finally {
                    // 每个选手到达终点时，end就减一
                    end.countDown();
                }
            }
        };
        new Thread(run).start();
        new Thread(run2).start();
        new Thread(run3).start();
        new Thread(run4).start();



        System.out.println("Game Start");
        // begin减一，开始游戏
        begin.countDown();
        // 等待end变为0，即所有选手到达终点
        end.await();
        System.out.println("Game Over");
        exec.shutdown();
    }

}
