package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


public class BasicJBZRestConfig {
    private static final HttpHeaders headers;
    private static final Logger logger = LoggerFactory.getLogger(BasicJBZRestConfig.class);
    private static final String UTF_8 = "utf-8";
    private static RestTemplate restTemplate = new RestTemplate();

    static {
        headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    }


    public static HttpHeaders getHeaders() {
        return headers;
    }

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }


}
