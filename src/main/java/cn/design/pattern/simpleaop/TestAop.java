package cn.design.pattern.simpleaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.simpleaop</li>
 * <li>创建时间 : 2018/12/24 10:16</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class TestAop {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
                "src/main/resources/testAop.xml");
        TestTarget target = (TestTarget) applicationContext.getBean("testAOP");
        target.test();
        System.out.println("----------------");
        target.test2();
    }
}
