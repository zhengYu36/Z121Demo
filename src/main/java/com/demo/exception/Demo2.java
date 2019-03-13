package com.demo.exception;

/**
 * <ul>
 * <li>文件包名 : com.demo.exception</li>
 * <li>创建时间 : 2019/1/4 13:56</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */

class Ax11 {
    public Ax11() {
        System.out.println("xxx11");
    }
}

class Ax211 extends Ax11 {
    public Ax211() {
        super();
        System.out.println("xxxxx");
    }
}

public class Demo2 {
    public static void main(String[] args) throws Exception {
        Ax211 ax = new Ax211();
    }

}
