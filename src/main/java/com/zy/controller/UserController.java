package com.zy.controller;

import com.alibaba.fastjson.JSON;
import com.demo.bean.DemoMaterial;
import com.demo.lambda.Student;
import com.utils.BasicJBZRestConfig;
import com.utils.CaptchaUtil;
import com.zy.dao.StudentDao;
import com.zy.dao.UserInfoDao;
import com.zy.dao.ZhengDao;
import com.zy.domain.DemoA;
import com.zy.domain.User;
import com.zy.domain.UserInfo;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * 1.自定义接口的实现和操作
 *
 * @ResponseBody 表示返回的时候是json
 * @RequestBody 表示请求的时候是json
 */

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    protected final HttpHeaders headers = BasicJBZRestConfig.getHeaders();
    protected RestTemplate restTemplate = BasicJBZRestConfig.getRestTemplate();

    @Resource
    ApplicationContext applicationContext;
    @Resource
    private UserService userService;

    @Resource
    private StudentDao studentDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private ZhengDao zhengDao;

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
        //为什么不能通过构造方法来呢?好神奇啊,妹的
        //ModelAndView mav = new ModelAndView("hello");
        ModelAndView mav = new ModelAndView("hello");
        User user = userService.selectUserById(10001);

        //mav.setViewName("hello");
        //mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public ModelAndView hello() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is welcome page!");
        User user = userService.selectUserById(10001);
        System.out.println(user);

        User param = new User();
        param.setUserId(10002);
        List<User> users = userService.queryList(param);

        model.setViewName("hello");
        return model;
    }


    //测试 spring security 关于登陆admin的验证
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Hello World");
        model.addObject("message", "This is protected page!");
        model.setViewName("admin");
        return model;

    }


    @RequestMapping("/abc3")
    public ModelAndView getabc3() {
        ModelAndView mav = new ModelAndView("indexDemo");
        return mav;
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam("id") int id, HttpServletRequest request) {

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            System.out.println(request.getParameter(s));
        }

        System.out.println("index start...");
        //applicationContext.publishEvent(new B(this));
        int i = 10000;
        ModelAndView mav = new ModelAndView("index");
        User user = userService.selectUserById(id);
        mav.addObject("user", user);
        return mav;
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
        System.out.println("id is:" + id);
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
        System.out.println("xxx");
        Student student1 = new Student("33333", 11d);
        result.addObject("user11", student1);
        return result;
    }

    @RequestMapping("/update7")
    @ResponseBody
    public String update7(com.zy.domain.Student student) {
        /*com.zy.domain.Student student = new com.zy.domain.Student();
        student.setId(1232141);
        student.setName("小宇哥");
        student.setAge(23);*/
        System.out.println("xxxx");
        if (student.getAge() == 1) {
            studentDao.save(student, "ds反倒是");
        } else if (student.getAge() == 2) {
            studentDao.update(student.getId());
        } else if (student.getAge() == 3) {
            List<Map> list = studentDao.select(student.getId().toString());
            System.out.println(list);
        } else if (student.getAge() == 4) {
            studentDao.delete(student.getId());
        } else if (student.getAge() == 5) {

            List<Integer> lista = new ArrayList<Integer>() {
                {
                    add(100007);
                    add(100008);
                    add(100009);
                }
            };
            List<Map> list = studentDao.select2("100");
            System.out.println("xxxx");

            System.out.println(list);
        }

        return "ok";
    }

    @RequestMapping("update8")
    @ResponseBody
    public String update8(UserInfo userInfo) {
        //Strx;
        userInfoDao.insert(userInfo);
        return "ok";
    }

    @RequestMapping("update9")
    @ResponseBody
    public String update9(@RequestParam("files") MultipartFile[] files) throws IOException {
        //1.获取文件 (这里没有判断文件后缀名，默认都为txt文件)
        if (files.length > 0) {

            for (int i = 0; i < files.length; i++) {
                //获取文件，然后再写入
                MultipartFile file = files[i];
                String name = file.getName();

                InputStream inputStream = file.getInputStream();

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(new File("E:/" + name + ".txt")));

                //需要通过一个 InputStreamReader 中间类来转换
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String str = "";
                while ((str = reader.readLine()) != null) {
                    writer.write(str);
                    writer.write("\n");
                }
                writer.flush();

                //通过方法来转换
                File fileNew = new File("E:/files3.mp3");
                if (!fileNew.exists()) {
                    fileNew.mkdirs();
                }
                //直接通过这种方法. 不用去获取然后又转换格式啥的，这个方法很牛叉的哈.
                file.transferTo(fileNew);

                //bufferedWriter.write(inputStream.);


                //
            }
        }

        return "ok";
    }


    //通过自定义的mapper来插入数据
    @RequestMapping("update10")
    @ResponseBody
    public String update10(UserInfo userInfo) {
        //Strx;
        //zhengDao.save(userInfo);
        return "ok";
    }


    @RequestMapping("/validBlob")
    @ResponseBody
    public String validBlob() {
        User user = new User();
        user.setUserId(40012);
        List<User> users = userService.queryList(user);
        System.out.println("users is length:"+users.size());
        return "ok";
    }


}  