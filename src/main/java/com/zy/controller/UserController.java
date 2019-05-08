package com.zy.controller;

import com.alibaba.fastjson.JSON;
import com.demo.bean.DemoMaterial;
import com.demo.lambda.Student;
import com.utils.BasicJBZRestConfig;
import com.utils.CaptchaUtil;
import com.zy.domain.DemoA;
import com.zy.domain.User;
import com.zy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    protected final HttpHeaders headers = BasicJBZRestConfig.getHeaders();
    protected RestTemplate restTemplate = BasicJBZRestConfig.getRestTemplate();

    @Resource
    ApplicationContext applicationContext;
    @Resource
    private UserService userService;

    // 验证的简单生成
    @RequestMapping("/user/check.jpg")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        CaptchaUtil util = CaptchaUtil.Instance();
        // 将验证码输入到session中，用来验证
        String code = util.getString();
        request.getSession().setAttribute("code", code);
        // 输出打web页面
        ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
    }


    // 验证验证码
    @RequestMapping("/user/checkRandCode")
    public void checkRandCode(HttpSession session, String code) throws IOException {
        String codeSession = (String) session.getAttribute("code");
        if (StringUtils.isEmpty(codeSession)) {
            log.error("没有生成验证码信息");
            throw new IllegalStateException("ERR-01000");
        }
        if (StringUtils.isEmpty(code)) {
            log.error("未填写验证码信息");
            throw new ServerException("ERR-06018");
        }
        if (codeSession.equalsIgnoreCase(code)) {
            // 验证码通过
            System.out.println("验证码通过");
        } else {
            log.error("验证码错误");
            throw new ServerException("ERR-06019");
        }
    }

    /**
     * 返回json
     */
    @RequestMapping("/findUserByID")
    @ResponseBody
    public User find(@RequestParam("id") int id) {
        User user = userService.selectUserById(id);
        return user;
    }


    /**
     * 返回json
     */
    @RequestMapping("/a")
    @ResponseBody
    public User aaa(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("测试方法的使用" + request.getMethod());


        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("bbbbbbbbbbbbbbb");
        User user = new User();
        user.setUserId(1111);
        user.setUserName("小宇哥36");
        user.setUserPassword("bbb");
        return user;
    }

    @RequestMapping("/abc")
    public ModelAndView getIndex() {
        System.out.println("ggggggggg");
        int i = 100;
        ModelAndView mav = new ModelAndView("index");
        User user = userService.selectUserById(1);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/abc3")
    public ModelAndView getabc3() {
        ModelAndView mav = new ModelAndView("indexDemo");
        return mav;
    }

    @RequestMapping("/index")
    public void getabc(@RequestParam("id") int id, HttpServletRequest request) {

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            System.out.println(request.getParameter(s));
        }

        System.out.println("index start...");
        //applicationContext.publishEvent(new DemoA(this));
        int i = 10000;
        ModelAndView mav = new ModelAndView("index");
        User user = userService.selectUserById(id);
        mav.addObject("user", user);
    }

    /**
     * 测试事物，
     * 1.首先插入
     * 2.更新
     * 3.抛出异常
     * 看数据是否会改变，如果有异常是不应该改变的
     */
    @RequestMapping("/optra")
    public void optra() {
        //System.out.println("op....");
        //userService.ope();

        //传递值看看
        List<DemoMaterial> list = new ArrayList<>();
        DemoMaterial demoMaterial = new DemoMaterial();
        demoMaterial.setMDM_TYPE("6666");

        DemoMaterial demoMaterial2 = new DemoMaterial();
        demoMaterial2.setMDM_TYPE("7777");

        list.add(demoMaterial);
        list.add(demoMaterial2);
        String jsonx = JSON.toJSONString(list);
        // restTemplate.postForLocation("http://127.0.0.1:8700/v1/api/ebs/materiel",jsonx);

        jsonx = "[{\"MDM_TYPE111\":\"gggg\"},{\"MDM_TYPE\":\"xxxx\"}]";
        //数据是要放到http中的哈
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonx, headers);
        String str = restTemplate.postForObject("http://127.0.0.1:8700/v1/api/ebs/materiel", httpEntity, String.class);
        //System.out.println("xxx:" + str);
    }


    /**
     * 修改多个值
     */
    @RequestMapping("/update2")
    @ResponseBody
    public int find() {
        return userService.updateProperty("aaa", "55555");
    }

    /**
     * 修改多个值
     */
    @RequestMapping("/update3")
    @ResponseBody
    public Student update3(HttpServletRequest request, HttpServletResponse response) {
        //Student std = new Student(student.getName(), student.getScore() + 13);
        Student std = new Student("adsf", 11d);
        Cookie cookie = new Cookie("name_test", "value_test");//创建新cookie
        cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie.setPath("/");//设置作用域

        Cookie cookie1 = new Cookie("cookie", "ggggg");//创建新cookie
        cookie1.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie1.setPath("/");//设置作用域
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
        response.addCookie(cookie1);
        return std;
    }

    @RequestMapping("/update31")
    @ResponseBody
    public Student update31() {
        //Student std = new Student(student.getName(), student.getScore() + 13);
        Student std = new Student("adsf11", 11d);
        return std;
    }

    @RequestMapping("/update4")
    public ModelAndView update4(Student student) {
        ModelAndView result = new ModelAndView("index");
        Student student1 = new Student("adsf", 11d);
        result.addObject("user", student1);
        return result;
    }

    @RequestMapping("/update5/{id}")
    @ResponseBody
    public Student update5(@PathVariable String id) {
        System.out.println("id is:"+id);
        Student std = new Student("adsf", 11d);
        return std;
    }

    /**
     * applicationContext的使用
     */
    @RequestMapping("/ope4")
    @ResponseBody
    public String ope4(String id) throws Exception {
        System.out.println("ope4 start...");
        applicationContext.publishEvent(new DemoA(this));
        System.out.println("ope4 end...");
        return "hello ope4";
    }

    @RequestMapping("/update6")
    public ModelAndView update6(Student stx) {
        ModelAndView result = new ModelAndView();
        Student student1 = new Student("33333", 11d);
        result.addObject("user11", student1);
        return result;
    }
}  