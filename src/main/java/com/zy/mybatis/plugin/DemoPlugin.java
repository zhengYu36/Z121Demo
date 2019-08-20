package com.zy.mybatis.plugin;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/**
 * <ul>
 * <li>文件包名 : com.zy.mybatis.plugin</li>
 * <li>创建时间 : 2019/8/2 16:05</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class DemoPlugin implements Interceptor {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept");
        return null;
    }

    @Override
    public Object plugin(Object o) {
        System.out.println("object plugin");
        return null;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("setProperties");
    }
}
