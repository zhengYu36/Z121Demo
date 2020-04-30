package com.demo;


import java.text.DecimalFormat;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2019/7/25 10:36</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 * 参考url:  http://www.zuidaima.com/share/3039487115938816.htm
 * list 进行分页
 *
 * @author zhengyu
 */
// add{new Student("111",22,"22")};
public class Demo10 {


    public static void main(String[] args) {
        double comp = 112.3d;

        //double comp = 0;
        double total = 234.33d;

        DecimalFormat df = new DecimalFormat("0.00");
        double v = (comp / total) * 100;
        System.out.println(v);
        System.out.println(df.format((comp / total) * 100));
        System.out.println(df.format(v));

    }

}
