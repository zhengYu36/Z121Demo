package cn.design.pattern.method;

import java.lang.reflect.Method;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.method</li>
 * <li>创建时间 : 2018/12/14 10:33</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：通过 @see ZhengYu 类 看看是否可以获取父类中的方法
 *
 * @author zhengyu
 */
public class MethodTest {

    public static void main(String[] args) throws Exception{

        Class zy = Class.forName("cn.design.pattern.method.ZhengYu");
        Method[] methods = zy.getMethods();
        for (Method method:methods){
            System.out.println(method.getName());
        }

    }
}
