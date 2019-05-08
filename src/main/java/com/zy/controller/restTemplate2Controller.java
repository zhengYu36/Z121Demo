package com.zy.controller;

import com.utils.BasicJBZRestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.net.*;
import java.util.List;

//获取cookie值
public class restTemplate2Controller {

    protected static final HttpHeaders headers = BasicJBZRestConfig.getHeaders();
    private static final Logger log = LoggerFactory.getLogger(restTemplate2Controller.class);
    protected static RestTemplate restTemplate = BasicJBZRestConfig.getRestTemplate();

    public static void main(String[] args) throws Exception{
        //通过URL获取cookie
       /* CookieManager manager=new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);*/
        String urlxx="http://localhost:8083/Z121Demo_war_exploded/update3";
        try{
            CookieManager manager=new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
            CookieHandler.setDefault(manager);
            URL	url=new URL(urlxx);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.getHeaderFields();
            CookieStore store = manager.getCookieStore();
            List<HttpCookie> lCookies=store.getCookies();
            System.out.printf("共%s个cookie\n",lCookies.size());
            for (HttpCookie cookie: lCookies) {
                System.out.printf("原:%s  名称:%s  解码值:%s\n",
                        cookie.toString(),
                        cookie.getName(),
                        URLDecoder.decode(cookie.getValue(), "UTF8"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}  