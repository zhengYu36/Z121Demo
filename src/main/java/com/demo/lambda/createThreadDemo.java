package com.demo.lambda;

/**
 * <ul>
 * <li>文件包名 : com.demo.lambda</li>
 * <li>创建时间 : 2019/3/28 9:54</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 利用 lambda 创建线程
 *
 * @author zhengyu
 */
public class createThreadDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello thread!");
            }
        }).start();
        System.out.println("========lambda create thread==========");
        //注意到没？这里的 ()没有参数是因为匿名内部类的 run()方法里面没有参数的缘故.
        new Thread(()-> System.out.println("hello lambda thread")).start();
    }
}
