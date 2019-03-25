package com.demo;


import com.utils.DateUtil;

import java.util.Date;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/11/9 11:00</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 * @see cn.design.pattern.strategyPattern.Demo
 */

public class Demo2 {

    public static void main(String[] args) throws Exception {
/*
        //获取为星期几
        Date date = new Date();
        String weekOfDate = DateUtil.getWeekOfDate(date);
        System.out.println("day is:"+weekOfDate);
*/

        //获取下一天
/*        Date date = new Date();
        Date nextDate = DateUtil.getNextDayByDate(date);
        String weekOfDate = DateUtil.getWeekOfDate(nextDate);
        System.out.println("day is:"+weekOfDate);*/

        String str = "2019-2-12";
        //把 str转换为 Date
        Date date = DateUtil.parseDate(str);
        String weekOfDate = DateUtil.getWeekOfDate(date);
        System.out.println("day is:"+weekOfDate);


    }

}
