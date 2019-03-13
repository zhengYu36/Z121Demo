package com.zy.listener;

import com.zy.domain.DemoA;
import org.springframework.context.ApplicationListener;

/**
 * <ul>
 * <li>文件包名 : com.zy.listener</li>
 * <li>创建时间 : 2018/6/25 17:13</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class DemoListener implements ApplicationListener<DemoA> {


    @Override
    public void onApplicationEvent(DemoA demoA) {
        System.out.println("onApplicationEvent start...:"+demoA);
    }
}
