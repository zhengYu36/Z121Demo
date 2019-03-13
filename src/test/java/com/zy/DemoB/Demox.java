package com.zy.DemoB;

/**
 * <ul>
 * <li>文件包名 : com.zy.DemoB</li>
 * <li>创建时间 : 2018/6/15 15:40</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public enum Demox {

    TOM(12,"XX"), TOM1(12,"XX") ,TOM2(12,"XX");


    private int age;
    private String name;

    Demox(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
