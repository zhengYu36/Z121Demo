package com.zy.callback;

public class Test {
    InterfaceA a = new InterfaceA() {
    };//成员匿名类

    public static void main(String[] args) {
        InterfaceA a = new InterfaceA() {
        };//局部匿名类
        //以上两种是通过实现接口实现匿名类，称为接口式匿名类，也可以通过继承类
        Test test = new Test() {
        };//继承式匿名类

        //还可以是位于参数上
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();//属于局部匿名类一种
    }

    private interface InterfaceA {}
}