package com.zy.demo;

import javax.servlet.*;
import java.io.IOException;

/**
 * <ul>
 * <li>文件包名 : ${PACKAGE_NAME}</li>
 * <li>创建时间 : 2019/3/26 17:53</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class filter2 implements Filter {
    public void destroy() {
        System.out.println("destroy2 is ok...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("doFilter2 is ok...");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("init2 is ok...");
    }

}
