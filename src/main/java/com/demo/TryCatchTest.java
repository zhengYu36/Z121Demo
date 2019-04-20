package com.demo;

public class TryCatchTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TryCatchTest t1 = new TryCatchTest();
        System.out.println("test1方法执行完毕！result的值为：" + t1.test1());

        Throwable  throwable = new Throwable();
        throwable.printStackTrace();
    }

    /**
     * divider:除数
     * result:结果
     * try-catch捕获while循环
     * 每次循环，divider减一，result=result+100/divider
     * 如果：捕获异常，打印输出“异常抛出了”，返回-1
     * 否则：返回result
     *
     * @return
     */
    public int test1() {
        int divider = 10;
        int result = 100;
        try {
            while (divider > -1) {
                divider--;
                result = result + 100 / divider;
            }
            System.out.println("sdfasdf！！");
            return result;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("异常抛出了！！");
            return result=999;
        }finally{
            System.out.println("111这是finally，哈哈哈！！");
            System.out.println("result的值为："+result);
            return 44;//编译器警告
        }
    }

}