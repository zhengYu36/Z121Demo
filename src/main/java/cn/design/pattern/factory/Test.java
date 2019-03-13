package cn.design.pattern.factory;

import java.util.HashMap;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.factory</li>
 * <li>创建时间 : 2018/12/14 14:13</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Test {

    public static void main(String[] args) {

        Factory factory = new FactoryImpl();
        //Car byd = factory.createCar("BYD");
        //byd.setCarName("aaa");

        Car car = factory.createCar(HongQI.class);
        car.setCarName("xxxx");
    }
}
