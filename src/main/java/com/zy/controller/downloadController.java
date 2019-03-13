package com.zy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
public class downloadController {

    /**
     * 生成时间： 2018/9/4 11:19
     * 方法说明：测试文件下载漏洞
     * 开发人员：zhengyu
     * @param
     * @param
     * @return
     */

    @RequestMapping(value = "/downloadFile")
    public String downloads(HttpServletResponse response, HttpServletRequest request) throws Exception {

        //String realPath = this.getServletContext().getRealPath("");
        //muiltFile.transforToFile(new File(realPath+File.separator+"uploaded", muiltFile.getFileName()));

        //System.out.println("xxxxx");
        //System.out.println(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(""));

        //String realPath= ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("src/main/jpg/");
        String realPath = "C:\\Users\\h\\Desktop\\Z121Demo\\src\\main\\jpg\\";
        System.out.println(realPath);

        String fileName = request.getParameter("fileName");

        //1、设置response 响应头
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        //File file = new File(path, fileName);
        File file = new File(realPath+fileName);
        //2、 读取文件--输入流
        InputStream input = new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024];
        int index = 0;
        //4、执行 写出操作
        while ((index = input.read(buff)) != -1) {
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;
    }

}