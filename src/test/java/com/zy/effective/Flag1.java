package com.zy.effective;

/**
 * <ul>
 * <li>文件包名 : com.zy.effective</li>
 * <li>创建时间 : 2018/7/4 10:29</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： effective java  第一条
 *
 * @author zhengyu
 */
class Demo{
    private static Demo demo = new Demo();
    private Demo(){}

    //为了验证单例模式下，修改了之后，第二次调用是否还可以得到该数
    public int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static Demo newInstance(){
        if(demo != null){
            return demo;
        }
        demo = new Demo();
        return demo;
    }
}

public class Flag1 {

    public static void main(String[] args) {

        //单例模式，就是说，如果该类是公用的基础的，那么就不必要每次都去实例化它，只需要在程序加载的时候，创建就可以了
        //后面就来直接用就好了
        Demo d1 = Demo.newInstance();
        Demo d2 = Demo.newInstance();
        d1.setNumber(999);
        System.out.println(d1.getNumber());
        System.out.println(d2.getNumber());
    }
}
