package com.demo.bean;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/9/19 16:40</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Ab extends Person{
    public String name;
    public int age;
    public int isHavePhoto;

    public int getIsHavePhoto() {
        return isHavePhoto;
    }

    public void setIsHavePhoto(int isHavePhoto) {
        this.isHavePhoto = isHavePhoto;
    }

    public Ab(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Ab{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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
}
