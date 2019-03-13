package com.zy.baseTest;

import java.math.BigDecimal;

/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/8/1 9:23</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */


/**
 * 360
 * 39441.00
 * 500900.70
 */
public class Demo9 {

    public static void main(String[] args) {

        System.out.println("xiaoyu ggg.");
        System.out.println("TTT it's nike");
        /** 实收总金额 */
        BigDecimal totalAmount = new BigDecimal("500900.7");
        if(totalAmount.compareTo(new BigDecimal("500900.70")) != 0){
            System.out.println("xxxx");
        }else{
            System.out.println("ggggg");
        }
    }

}
