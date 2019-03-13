package com.zy.effective;

import java.io.File;
import java.util.HashMap;

/**
 * <ul>
 * <li>文件包名 : com.zy.effective</li>
 * <li>创建时间 : 2018/7/9 15:41</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
class Dex{
    private String name;
    private int age;

    public Dex(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dex{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int hashCode() {
        //return super.hashCode();
        // 这里是为了好方便比较
        return name.hashCode()*36+age;
    }

    //复写equals方法. @Override 只是给人一个提示，表示它是被覆盖的方法哈.
    @Override
    public boolean equals(Object obj) {
        //return super.equals(obj);
        Dex dx = (Dex) obj;
        if(this.age == dx.age && this.name.equals(dx.name)){
            return true;
        }
        return false;
    }
}
public class Flag8 {

    public static void main(String[] args) {

        //测试下经典的String
        String str = "123";
        String str2 = "123";
        System.out.println(str.hashCode());
        System.out.println(str2.hashCode());

        System.out.println(str == str2);
        System.out.println(str.equals(str2));
        System.out.println(str.equals(str));

        //创建一个类，来进行相关的测试
        System.out.println("///////////////////");
        Dex dex = new Dex("aaa",123);
        Dex dex2 = new Dex("aaa",123);
        System.out.println(dex == dex2);
        System.out.println(dex.equals(dex2));
        System.out.println(dex.hashCode());
        System.out.println(dex2.hashCode());

        ////
        HashMap<Dex, Integer> hashMap = new HashMap<Dex, Integer>();
        hashMap.put(dex, 123);

        System.out.println("XXX");
        System.out.println(hashMap.get(new Dex("aaa",123)));

    }
}
