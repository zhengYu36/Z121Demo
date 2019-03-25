package com.utils;


import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/11/9 11:00</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 * 如果有 1，2，3，4，5  需要排班的人员id 如果每3天派一次班，
 * 需要排一个月(30)的班，频率为1：排班为　　１,２,３　2，3，4   3，4，5  4，5，6 ...
 * 需要排一个月(30)的班，频率为2：排班为　　１,２,３ 1,2,3　2，3，4  2,3,4  3，4，5  3,4,5 ...
 * 以此类推
 *
 * @author zhengyu
 */

public class NumberRegular {

    //下标数据
    public static int NUM = 0;

    public static void main(String[] args) {
        //循环人数
        String str = "111";
        String[] split = str.split(";");
        List<String> classesBatch = getClassesBatch(split, 5, 2);
        classesBatch.forEach(i-> System.out.println(i));

        //这里可以依次添加时间
    }

    /**
     * 生成时间： 2019/3/20 13:43
     * 方法说明：
     * 开发人员：zhengyu
     *
     * @param classLists 需要排班的人员集合
     * @param day        排班的天数
     * @param rate       排班频率  eg:
     * @return
     */
    public static List<String> getClassesBatch(String[] classLists, int day, int rate) {
        NUM = classLists.length - 1; //获取总长度
        List<String> list = new ArrayList<>();
        StringBuffer tempStr = new StringBuffer("");
        //默认下标,当前值向的下标
        int index = 0;
        for (int i = 0; i < day; i++) {
            for (int j = 0; j < 3; j++) {
                int temp = NumberRegular.getIndex(index);
                tempStr.append(classLists[temp] + ";");
                index++;
            }
            for (int k = 0; k < rate; k++) {
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

        list = list.subList(0, day);
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
