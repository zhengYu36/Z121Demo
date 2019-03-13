package com.demo;

public class Test2 {

    public static void main(String[] arg) {

        //这个到根源竟然还是用的 hashtable里面的put方法，好吧，牛叉，牛叉
        String str = System.setProperty("AA","AAA1");
        System.out.println(str);
        System.out.println(System.getProperty("AA"));

    }

}