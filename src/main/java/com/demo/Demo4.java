package com.demo;


import com.utils.DateUtil;
import com.utils.NumberRegular;

import java.util.ArrayList;
import java.util.Date;
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

public class Demo4 {
    public static void main(String[] args)  throws Exception {
        //首先我知道了日期，知道了排班人数等相关形象
        String str = "111;222;333;444;555";
        String[] split = str.split(";");
        List<String> classesBatch = NumberRegular.getClassesBatch(split, 10, 1);
        List<String> ax = new ArrayList<>();
        String strDate = "2019-2-12"; //排班开始日期
        //日期为多少
        Date date = DateUtil.parseDate(strDate);
        for(int i=0;i<classesBatch.size();i++){
            StringBuffer sxx = new StringBuffer("");
            String stra = classesBatch.get(i); //排班人员
            //获取日期
            sxx.append(DateUtil.formatDate(date));
            String weekOfDate = DateUtil.getWeekOfDate(date);
            sxx.append(weekOfDate);
            //获取是周几
            sxx.append(stra);
            ax.add(sxx.toString());
            sxx = new StringBuffer("");

            //把date加1天
            date = DateUtil.getNextDayByDate(date);
        }

        ax.forEach(i-> System.out.println(i));
    }
}
