package com.demo.bean;


import javax.validation.constraints.NotNull;

/**
 * <ul>
 * <li>文件包名 : com.demo.bean</li>
 * <li>创建时间 : 2018/12/20 14:13</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Person {

    @NotNull
    private String text;
    private int age;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
