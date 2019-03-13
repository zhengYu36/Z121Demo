package cn.design.pattern.simpleaop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.simpleaop</li>
 * <li>创建时间 : 2018/12/24 10:09</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class TestAdvisor implements PointcutAdvisor {
    @Override
    public Advice getAdvice() {
        return new TestAfterAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    /**
     * 获取切入点
     */
    @Override
    public Pointcut getPointcut() {
        return new TestPointCut();
    }

}
