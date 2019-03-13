package com.zy.baseTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/5/21 9:43</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：关于java 8 lambda 的相关使用
 *
 * @author zhengyu
 */
public class Demo {

    public static void main(String[] args) {
        // storge data
        List<A> list = new ArrayList<A>();
        list.add(new A(1,"a"));
        list.add(new A(2,"b"));
        list.add(new A(3,"c"));
        list.add(new A(1,"a"));

        // filter data
        //list.stream().filter(a -> "hanmeimei".equals(a.getName())) .findFirst());
        //Optional<A> firstA= list.stream() .filter(a -> "a".equals(a.getName())) .findFirst();
        //System.out.println(firstA);
        /*List<A> list0 = list.stream().filter(a -> "a".equals(a.getName())).collect(Collectors.toList());
        list0.forEach(b-> System.out.println(b));*/
        List<Integer> list0  = list.stream().filter(a -> "a".equals(a.getName())).map(A::getId).distinct().collect(Collectors.toList());
        list0.forEach(b -> System.out.println(b));

    }
}

class A{

    private int id;
    private String name;

    public A(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
