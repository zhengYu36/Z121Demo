package com.zy.DemoA;

/**
 * <ul>
 * <li>文件包名 : com.zy.DemoA</li>
 * <li>创建时间 : 2018/6/1 16:40</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
class Aj {
    int i = 10;

    public void sa() {
        System.out.println("x");
    }
}

public class DemoAx {

    public static void main(String[] args) throws Exception {

        Aj aj = new Aj();
        ClassLoader classLoader = aj.getClass().getClassLoader();
        System.out.println(classLoader);

    }

}
