package com.utils;

public class AddUtil {

    public static void main(String[] args) {
        int z = new AddUtil().add(3, 5);
        System.out.println(z);
    }

    public int add(int x, int y) {
        return x + y;
    }

    public int divide(int x, int y) {
        return x / y;
    }

}