package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.lambda.Student;
import com.utils.BasicJBZRestConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2019/4/18 9:30</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Demo6 {


    public static void main(String[] args) {

        HttpHeaders headers = BasicJBZRestConfig.getHeaders();
        RestTemplate restTemplate = BasicJBZRestConfig.getRestTemplate();

        Student student = new Student("a", 12d);

        String s = JSON.toJSONString(student);
        HttpEntity httpEntity = new HttpEntity<>(s, headers);
        String str = restTemplate.postForObject(
                "http://localhost:8083/Z121Demo_war_exploded/update4",
                httpEntity, String.class);

        System.out.println(str);

    }
}
