package com.demo.notify;

import java.util.LinkedList;

/**
 * <ul>
 * <li>文件包名 : com.demo.notify</li>
 * <li>创建时间 : 2019/4/10 14:10</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class SynDemo {

    private static LinkedList list = new LinkedList();

    public static void main(String[] args) throws Exception {

        SynDemo sd = new SynDemo();

        for (int i = 0; i < 12; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sd.add();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sd.reduce();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        /*Thread.sleep(9000L);
        System.out.println(list.size());*/
    }

    public void add() throws Exception {
        synchronized (list) {

            if (list.size() > 10) {
                list.wait();
            }

            list.notifyAll();

            list.add(new Object());
        }
    }

    public void reduce() throws Exception {
        synchronized (list) {
            if (list.size() < 1) {
                list.wait();
            }

            list.notifyAll();
            list.remove();
        }
    }

}


