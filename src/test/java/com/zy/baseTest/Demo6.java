package com.zy.baseTest;


/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/5/25 10:44</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */

public class Demo6 {

    public static void main(String[] args) throws Exception {

        ThreadLocal<String> list = new ThreadLocal<String>();
        list.set("123");



        new Thread(new Runnable() {
            @Override
            public void run() {
                //list.set("gggggg");
                System.out.println("a2:"+list.get());

            }
        }).start();

        System.out.println("a1:"+list.get());
        System.out.println("a1:"+list.get());
        System.out.println("a1:"+list.get());


    }

}
