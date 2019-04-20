package com.demo;


import com.demo.bean.Student;
import com.demo.bean.StudentOne;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/11/9 11:00</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：整合Demo2 和 Demo3
 *
 * @author zhengyu
 * @see cn.design.pattern.strategyPattern.Demo
 */
public class Demo5 {
    public static void main(String[] args) throws Exception {

        List<Student> list = new ArrayList<Student>() {
            {
                add(new Student("demo1", 2, "p1", new ArrayList<Student>() {{
                    add(new Student("dxx", 2, "pxx"));
                }}));
                add(new Student("demo2", 231, "p2"));
                add(new Student("demo3", 211, "p3"));
            }
        };

        Student student = new Student();
        student.setName("1");
        student.setAge(12);
        student.setPassword("password1");
        student.setList(list);

        System.out.println(student);

        StudentOne student1 = new StudentOne();

        //BeanUtils.copyProperties(student,student1);
        org.springframework.beans.BeanUtils.copyProperties(student, student1);
        System.out.println();
        System.out.println(student1);

    }
}
