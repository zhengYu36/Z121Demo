package com.demo;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2019/1/4 10:20</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class ExchangerDemo {

    static final Exchanger<String> exgr = new Exchanger<>();
    static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    String a = "银行流水A";
                    //String b = exgr.exchange(a);
                    String b = "xx";
                    System.err.println("a:A 和 B 数据是否一致： " + a.equals(b) + ", A 录入的是：" + a + ", B 录入的是：" + b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String b = "银行流水B";
                    String a = exgr.exchange(b);
                    System.out.println("b:A 和 B 数据是否一致： " + a.equals(b) + ", A 录入的是：" + a + ", B 录入的是：" + b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();

    }
}
