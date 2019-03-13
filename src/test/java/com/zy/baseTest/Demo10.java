package com.zy.baseTest;

/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/8/6 13:50</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：匿名函数,但是该怎么去调用尼
 *
 * @author zhengyu
 */
class Demo10a{
    public void sayHi(){
        System.out.println("xxx");
    }
}
public class Demo10 {

    public static void main(String[] args) {
        System.out.println("bbbb");
        String str = "xiaoyu";

        Demo10a demo = new Demo10a(){
            @Override
            public void sayHi() {
                super.sayHi();
            }
        };


    }
}
