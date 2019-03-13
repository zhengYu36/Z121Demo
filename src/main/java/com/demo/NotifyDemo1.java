package com.demo;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2019/1/3 15:10</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class NotifyDemo1 {

    final static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        System.out.println("aaaa   start");
                        Thread.sleep(1000);
                        lock.wait();
                        System.out.println("aaaa  end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("bbbb   start");
                        Thread.sleep(1000);
                        lock.wait();
                        System.out.println("bbbb  end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("cccccc   start");
                        Thread.sleep(3000);
                        lock.notifyAll();
                        System.out.println("cccc  end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
