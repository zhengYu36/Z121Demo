package com.demo.lambda;


import java.util.*;
import java.util.stream.Collectors;

/**
 * <ul>
 * <li>文件包名 : com.demo.lambda</li>
 * <li>创建时间 : 2019/3/29 10:18</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class StreamDemo2 {
    public static void main(String[] args) {

        List<Student> list = new ArrayList<Student>(){
            {
                add(new Student("stu1", 120.0));
                add(new Student("stu2", 97.0));
                add(new Student("stu3", 96.0));
                add(new Student("stu9", 96.0));
                add(new Student("stu5", 105.0));
                add(new Student("stu6", 25.0));
            }
        };

        List<String> studentList = list.stream()
                //.filter(x->x.getScore()>85)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .map(Student::getName)
                .collect(Collectors.toList())
                ;
        System.out.println(studentList);

        //转换截取最后一位

        studentList.forEach(x->{
            System.out.println(x);
            //System.out.println(x.getName()+"-"+x.getScore());
        });

    }
}
