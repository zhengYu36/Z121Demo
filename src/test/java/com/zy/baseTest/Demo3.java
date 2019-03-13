package com.zy.baseTest;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/5/21 10:18</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Demo3 {
    public static void main(String[] args) {
        //DemoFruit d1 = new DemoApple();
        //System.out.println(d1.getName());
       /* Plate<? extends DemoFruit> p = new Plate<? extends DemoFruit>();

        p.setItem(new DemoFruit());
        p.setItem(new DemoApple());
        System.out.println(p);*/
        List<DemoApple> list = new ArrayList<DemoApple>();
        Demo3 d3 = new Demo3();

    }
}
class Plate<T> {
    private T item;
    public Plate(T t){item=t;}
    public T getItem() {
        return item;
    }
    public void setItem(T item) {
        this.item = item;
    }
}