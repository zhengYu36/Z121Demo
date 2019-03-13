package cn.design.pattern.pp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.pp</li>
 * <li>创建时间 : 2018/12/14 15:17</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class PDFProxy2 {

    public static Object getProxy(final Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                method.invoke(obj, args);
                System.out.println("proxy");
                return null;
            }
        });
    }
}
