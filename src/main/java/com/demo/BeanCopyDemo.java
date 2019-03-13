package com.demo;

import com.demo.bean.Student;
import com.demo.bean.StudentOne;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/9/20 9:32</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 利用 springframwork 框架来拷贝对象
 * 1 测试的目的， 如果 A对象拷贝到 B对象 ，是不是A里面有的field 拷贝到B对象中 field一样的值，如果
 * 不是是否会拷贝成
 * eg: A{name,age} B{age,remark,uid} 从A拷贝到B 应该只会把 age 拷贝到B中吧？ 测试是额
 *
 * 2.是否可以拷贝list 对象 ？ 不可以
 *
 * 3.如果是深拷贝是否会成功？ 深拷贝就是指对象里面还有对象 好像是可以的感觉
 *
 * @author zhengyu
 */

public class BeanCopyDemo {

    public static void main(String[] args) {
        Student student = new Student("xiaoyu",12,"123");
        StudentOne studentOne = new StudentOne();

        // 1.拷贝对象 (ok)
      /*  BeanUtils.copyProperties(student,studentOne);
        System.out.println(studentOne);*/

        // 2. 拷贝集合 不能，还是需要循环拷贝才行
        /*List<Student> list = new ArrayList<>();
        List<StudentOne> listStu = new ArrayList<>();
        Student student2 = new Student("xiaoyu2",22,"222");
        list.add(student);
        list.add(student2);
        System.out.println(list);

        //需要通过循环来拷贝
        list.stream().forEach(i->{
            StudentOne a = new StudentOne();
            BeanUtils.copyProperties(i,a);
            listStu.add(a);
        });
        System.out.println(listStu);*/

        //深拷贝,貌似是支持 ... 还是挺强大的感觉!
        List<Student> list = new ArrayList<>();
        Student student2 = new Student("xiaoyu2",22,"222");
        list.add(student2);
        student.setList(list);
        System.out.println(student);
        BeanUtils.copyProperties(student,studentOne);
        System.out.println(studentOne);


    }
}
