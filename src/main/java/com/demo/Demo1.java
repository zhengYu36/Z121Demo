package com.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class Demo1 {

    public static void main(String[] args) throws Exception {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println( simpleDateFormat.format(calendar.getTime()));

        //比较时间是否有一年的哈，


    }

}
