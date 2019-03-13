package cn.design.pattern.simpleaop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.simpleaop</li>
 * <li>创建时间 : 2018/12/24 10:08</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class TestAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println(
                "after " + o1.getClass().getSimpleName() + "." + method.getName() + "()");
    }
}
