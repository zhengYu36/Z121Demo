package com.demo;


import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/11/9 11:00</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 时间排序算法
 *
 * @author zhengyu
 * @see cn.design.pattern.strategyPattern.Demo
 */

public class Demo3 {

    //数据，数据长度
    public static String str = "111;222;333;444;555;666;777";
    //下标数据
    public static int NUM = str.split(";").length - 1;

    public static void main(String[] args) throws Exception {
        System.out.println("num is:" + NUM);
        //循环人数
        String[] split = str.split(";");

        //获取排班批次
        List<String> classesBatch = getClassesBatch(split, 2);
        classesBatch = classesBatch.subList(0,4);
        for (int i = 0; i < classesBatch.size(); i++) {
            System.out.println(classesBatch.get(i));
        }
    }

    private static List<String> getClassesBatch(String[] split, int rate) {
        List<String> list = new ArrayList<>();
        StringBuffer tempStr = new StringBuffer("");
        //默认下标,当前值向的下标
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                int temp = Demo3.getIndex(index);
                tempStr.append(split[temp] + ";");
                index++;
            }
          /*  list.add(tempStr.toString());
            tempStr = new StringBuffer("");*/
            for(int k=0;k<rate;k++){
                list.add(tempStr.toString());
            }
            tempStr = new StringBuffer("");
            if (index > 0) {
                //这里也需要判断
                index = index - 1 - 1;
            }
            if (index > NUM) {
                index = index - NUM - 1;
            }
        }
        return list;
    }

    public static int getIndex(int index) {
        if (index <= NUM) {
            return index;
        } else {
            return index - NUM - 1;
        }
    }
}
