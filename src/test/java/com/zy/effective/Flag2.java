package com.zy.effective;

import java.util.Stack;

/**
 * <ul>
 * <li>文件包名 : com.zy.effective</li>
 * <li>创建时间 : 2018/7/4 11:05</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Flag2 {
    private String name;
    private String id;
    private int age;

    /**
     * 构造方法这样来设计比较好
     *
     * @param name
     */
    public Flag2(String name) {
        this(name, "");
    }

    public Flag2(String name, String id) {
        this(name, id, 0);
    }

    public Flag2(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public static void main(String[] args) {

        Flag6 flag6 = new Flag6();
        for (int i = 0; i < 100; i++) {
            flag6.push(i + "ab");
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(flag6.pop());
        }
    }
}
