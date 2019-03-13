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
class D {

    public D(int num){
        this.i = num;
    }

    public int i ;

    public void ShowContext() {
        System.out.println("hello xiaoyu:"+i);
    }

    public void sax(){
        System.out.println("sax:"+this.i);
    }
}

public class DemoX {

    public static void main(String[] args) {
        System.out.println("start demo!");

        //这里的意思是首先设置值，然后再执行相关的方法
        new D(6) {

            @Override
            public void ShowContext() {
                super.ShowContext();
            }

        }.sax();  //当然这里还可以不执行方法体里面复写的方法，还可以执行该类中本来就有的反复.


    }

}
