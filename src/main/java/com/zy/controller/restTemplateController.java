package com.zy.controller;

import com.utils.BasicJBZRestConfig;
import com.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import peccancy.AlarmPush;
import peccancy.CapturePush;

@Controller
public class restTemplateController {

    private static final Logger log = LoggerFactory.getLogger(restTemplateController.class);
    protected  static  final HttpHeaders headers = BasicJBZRestConfig.getHeaders();
    protected static RestTemplate restTemplate = BasicJBZRestConfig.getRestTemplate();

    public static void main(String[] args) {

        //CapturePush();
        AlarmPush capturePush = new AlarmPush();
        capturePush.setBim_station_id("6383686960287981568");
        String imageBase64 = FileUtils.ReadFile(null);
        capturePush.setFace_base64(imageBase64);
        capturePush.setScene_base64(imageBase64);
        capturePush.setTime(1552878941);
        capturePush.setDevice_id("28:ed:e0:74:38:bd");
        capturePush.setDevice_name("鹤洞东站北侧点位(生活区附近一侧)");
        capturePush.setDescription("否");
        capturePush.setSecurity_code("gzdt");
        capturePush.setPerson_url("xxxx");
        capturePush.setPerson_name("aaa");
        capturePush.setPerson_id("4444444");
        capturePush.setRepository_id("1111");
        capturePush.setSimilarity(22.22);

        //这里可以使json也可以是实体数据
        HttpEntity httpEntity = new HttpEntity<>(capturePush, headers);
        String str = restTemplate.postForObject("http://localhost:8080/scbim/6245721945602523136/server/alarmPush/saveInfo.json",
                httpEntity, String.class);
        System.out.println(str);

    }

    private static void CapturePush() {
        CapturePush capturePush = new CapturePush();
        capturePush.setBim_station_id("6383686960287981568");
        String imageBase64 = FileUtils.ReadFile(null);
        capturePush.setFace_base64(imageBase64);
        capturePush.setScene_base64(imageBase64);
        capturePush.setTime(1552878941);
        capturePush.setDevice_id("28:ed:e0:74:38:bd");
        capturePush.setDevice_name("鹤洞东站北侧点位(生活区附近一侧)");
        capturePush.setDescription("否");
        capturePush.setSecurity_code("12345678901234567890");

        //这里可以使json也可以是实体数据
        HttpEntity httpEntity = new HttpEntity<>(capturePush, headers);
        String str = restTemplate.postForObject("http://localhost:8080/scbim/6245721945602523136/server/capturePush/saveInfo.json",
                httpEntity, String.class);
    }


}  