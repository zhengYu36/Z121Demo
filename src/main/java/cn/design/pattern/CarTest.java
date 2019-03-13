package cn.design.pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern</li>
 * <li>创建时间 : 2018/12/13 16:09</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class CarTest {

    public static void main(String[] args) {
        CarOpe carOpe = new CarImpl();

        CarProperty carProperty = carOpe;


        carProperty.setCarColor("red");
        carProperty.setCarName("奥迪");
        ((CarOpe) carProperty).run();
        ((CarImpl) carProperty).stop();

        CarAction carAction = carOpe;
        ((CarOpe) carAction).setCarColor("22");

        System.out.println("xxxx");
        System.out.println("xx");
        Map map = new HashMap<>();
        List list =new ArrayList<>();
    }
}
