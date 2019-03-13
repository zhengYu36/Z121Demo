package com.spring.learn;

import com.demo.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/springWangYang.xml");
        Person wy = context.getBean("person", Person.class);
        System.out.println(wy.getText());

        //wy.sayName("wangyang-");
        /* String str = wy.saySth("hello--");
        System.out.println("str的值：" + str);*/

    }
}