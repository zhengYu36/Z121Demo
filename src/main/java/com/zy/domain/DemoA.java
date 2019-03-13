package com.zy.domain;

import org.springframework.context.ApplicationEvent;

/**
 * <ul>
 * <li>文件包名 : com.zy.domain</li>
 * <li>创建时间 : 2018/6/25 17:15</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class DemoA extends ApplicationEvent {

    public DemoA(Object source) {
        super(source);
        System.out.println("ApplicationEvent start...");
    }
}
