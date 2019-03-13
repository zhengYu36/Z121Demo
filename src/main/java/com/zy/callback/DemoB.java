package com.zy.callback;

/**
 * <ul>
 * <li>文件包名 : com.zy.callback</li>
 * <li>创建时间 : 2018/10/24 14:42</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
interface Dx {
    public void say();
}
class Ax{
    public Ax(Dx dx){
        dx.say();
    }
}

public class DemoB {

    public static void main(String[] args) {
        System.out.println("start demo!");
        new Ax(new Dx() {
            @Override
            public void say() {
                System.out.println("ggg");
            }
        });

    }

}
