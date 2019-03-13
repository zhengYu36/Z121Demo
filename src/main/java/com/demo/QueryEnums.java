package com.demo;

import java.math.BigDecimal;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/10/12 10:39</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class QueryEnums {

    public static void main(String[] args) {

        System.out.println("ggg");
        BigDecimal b1 = new BigDecimal(100.000);
        BigDecimal b2 = new BigDecimal(100.00);
        System.out.println(b1.compareTo(b2));

    }
}
