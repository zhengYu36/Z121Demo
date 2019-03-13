package cn.design.pattern.simpleaop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.simpleaop</li>
 * <li>创建时间 : 2018/12/24 10:06</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class TestPointCut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> aClass) {

                if ("test".equals(method.getName())) {
                    return true;
                }

                return false;
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> aClass, Object[] objects) {
                if ("test".equals(method.getName())) {
                    return true;
                }

                return false;
            }
        };
    }
}
