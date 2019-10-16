package com.utils.guava;


import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * <ul>
 * <li>文件包名 : com.utils.guava</li>
 * <li>创建时间 : 2019/8/22 10:48</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： guava系统学习
 *
 * @author zhengyu
 */
public class GuavaTester {
    public static void main(String[] args) {

        String str = "aa,dfd,ddc,cc3r, ,xcvsda,fag3,sdfa";
      /*  String[] split = str.split(",");
        Arrays.stream(split).forEach(x-> System.out.println(x));*/

        /*List<String> strings = Splitter.on(",").splitToList(str);
        strings.forEach(x-> System.out.println(x));*/

     /*   List<String> list = Lists.newArrayList("xx","aa","bb","c","xx");
        List<List<String>> partition = Lists.partition(list, 2);
        partition.forEach(x-> System.out.println(x));*/

       /* Integer i = null;
        System.out.println("one ");
        Optional op = Optional.of(i);
        System.out.println("two .");*/

     /*   Integer i = null;
        System.out.println("111");
        Integer integer = Preconditions.checkNotNull(i);
        System.out.println(integer);*/

        try {
            FileUtils.deleteDirectory(new File("E:\\abc3"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
