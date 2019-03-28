package com.demo;


/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/11/9 11:00</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：测试 Listener方法，这种方式是异步的
 *
 * @author zhengyu
 * @see cn.design.pattern.strategyPattern.Demo
 */

public class ListenerDemo {
    public void sayHi()throws Exception{
        System.out.println("sayHi");
        throw new Exception("ggg");
    }
    public static void main(String[] args)  throws Exception {
        ListenerDemo list = new ListenerDemo();
        System.out.println("111");
        list.sayHi();
        System.out.println("222");
    }
}
