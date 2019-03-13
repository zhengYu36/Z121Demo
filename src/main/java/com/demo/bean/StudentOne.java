package com.demo.bean;

import java.util.ArrayList;
import java.util.List;

public class StudentOne implements Comparable {

    private String name;
    private int age;
    private String password;
    private String remark;

    List<StudentOne> list = new ArrayList<>();

    public List<StudentOne> getList() {
        return list;
    }

    public void setList(List<StudentOne> list) {
        this.list = list;
    }

    public StudentOne(String name, int age, String password, String remark) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.remark = remark;
    }

    public StudentOne() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public StudentOne(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StudentOne{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    public int compareTo(Object o) {

        StudentOne current = (StudentOne) o;
        int num = this.age - current.age;
        if (num < 0) {
            return 1;
        } else if (num == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}