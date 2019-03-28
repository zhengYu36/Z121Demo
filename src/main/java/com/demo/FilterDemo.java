package com.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2019/3/28 9:19</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：关于filter过滤器的使用
 *
 * @author zhengyu
 */
public class FilterDemo {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a1");
        list.add("a2");
        list.add("a3");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello, i am thread!");
            }
        }).start();

        new Thread(()-> System.out.println("hello,i am lambda")).start();


    }
}
