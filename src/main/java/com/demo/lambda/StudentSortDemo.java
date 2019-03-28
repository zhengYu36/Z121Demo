package com.demo.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo.lambda</li>
 * <li>创建时间 : 2019/3/28 9:54</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：实体排序例子
 *
 * @author zhengyu
 */
public class StudentSortDemo {

    //通过 Collection 工具类进行排序，以前是通过在类中来实现的
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>() {
            {
                add(new Student("stu1", 120.0));
                add(new Student("stu2", 97.0));
                add(new Student("stu3", 96.0));
                add(new Student("stu9", 96.0));
                add(new Student("stu5", 105.0));
                add(new Student("stu6", 25.0));
            }
        };

        //通过 sort进行排序
      /*  Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                //这里默认好像是升序排列? 这个比较方便
                return Double.compare(o1.getScore(), o2.getScore());
            }
        });
        System.out.println(studentList);*/
        System.out.println("==============通过lambda来实现==============");
        Collections.sort(studentList,(s1,s2)->Double.compare(s1.getScore(),s2.getScore()));
        System.out.println(studentList);
    }
}
