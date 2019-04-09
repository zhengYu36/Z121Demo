package com.zy.demo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

/**
 * <ul>
 * <li>文件包名 : com.zy.demo</li>
 * <li>创建时间 : 2019/4/2 15:30</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class VelocityDemo {
    public static void main(String[] args) {
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        // 获取模板文件
        Template template = ve.getTemplate("vltDemo.vm");
        VelocityContext velocityContext = new VelocityContext();
        // 设置变量
        velocityContext.put("name", "zhangsan");
        // 输出
        StringWriter sw = new StringWriter();
        template.merge(velocityContext,sw);
        System.out.println(sw.toString());
    }
}
