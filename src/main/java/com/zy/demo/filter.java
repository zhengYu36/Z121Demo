package com.zy.demo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
public class filter implements Filter {
    public void destroy() {
        System.out.println("filter destroy is ok...");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("获取请求路径");
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println(request.getRequestURL());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("filter init is ok...");
    }

}
