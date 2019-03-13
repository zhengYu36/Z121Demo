package com.utils;

/**
 * <ul>
 * <li>文件包名 : com.utils</li>
 * <li>创建时间 : 2019/3/13 15:14</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Demo1 {
    public static void main(String[] args) {
        String str="xxx,dsdf,sdfs,";
        String sx = str.substring(0,str.lastIndexOf(","));
      //  System.out.println(sx);

        StringBuffer aaa = new StringBuffer();
        aaa.append("xxx");
        aaa.append("ggg");
        System.out.println(aaa.toString());
        aaa = new StringBuffer("");
        aaa.append("xxx");
        System.out.println(aaa.toString());
    }
}
