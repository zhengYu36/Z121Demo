package com.demo.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <ul>
 * <li>文件包名 : com.demo.lambda</li>
 * <li>创建时间 : 2019/3/28 10:41</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 关于 lambda 的 stream流的学习 这个可以参考url即可
 * 参考相关url: https://www.cnblogs.com/andywithu/p/7404101.html
 *
 * @author zhengyu
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<Student> stuList = new ArrayList<Student>() {
            {
                add(new Student("stu1", 120.0));
                add(new Student("stu2", 97.0));
                add(new Student("stu3", 96.0));
                add(new Student("stu9", 96.0));
                add(new Student("stu5", 105.0));
                add(new Student("stu6", 25.0));
            }
        };
        System.out.println(stuList);

        List<String> studentList = stuList.stream()
                .filter(x->x.getScore()>85)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(studentList);

        List students = stuList.stream()
                .filter(x->x.getScore()>85)
                .sorted(Comparator.comparing(Student::getName).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(students);
    }
}
