package com.demo.bean;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable {

    private String name;
    private int age;
    private String password;

    List<Student> list = new ArrayList<>();

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public Student() {
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    public int compareTo(Object o) {

        Student current = (Student) o;
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