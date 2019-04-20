package com.zy.demo; /**
 * <ul>
 * <li>文件包名 : ${PACKAGE_NAME}</li>
 * <li>创建时间 : 2019/3/26 18:08</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class listener3 implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("listener3 init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("listener3 destroy");
    }
}
