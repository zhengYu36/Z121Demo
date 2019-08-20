package com.demo.reflect;

/**
 * <ul>
 * <li>文件包名 : com.demo.reflect</li>
 * <li>创建时间 : 2019/7/22 10:13</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Cat implements Animal {

    public static final String name = "cat";

    @Override
    public String sayHello(String str) {
        return String.format("%s  says : %s", this.name, str);
    }
}
