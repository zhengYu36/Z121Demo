package com.demo.bean;

/**
 * <ul>
 * <li>文件包名 : com.demo.bean</li>
 * <li>创建时间 : 2019/4/2 17:14</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class LS {


    private String name;
    private int id;

    private Student stu;


    public LS(String name, int id, Student stu) {
        this.name = name;
        this.id = id;
        this.stu = stu;
    }


    public LS() {
    }

    public LS(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LS{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", stu=" + stu +
                '}';
    }
}
