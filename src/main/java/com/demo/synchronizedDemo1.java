package com.demo;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2019/1/3 14:31</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：测试是锁对象，还是锁代码块
 *
 * @author zhengyu
 */
public class synchronizedDemo1 {

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronizedDemo1 sx = new synchronizedDemo1();
                    sx.test();
                }
            }, "线程:").start();
        }
    }

    public void test() {
        synchronized (Demo1.class) {
            System.out.println(Thread.currentThread().getName() +":test开始..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() +"test结束..");
        }
    }

}
