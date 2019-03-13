package com.spring.learn;

/**
 * <ul>
 * <li>文件包名 : com.spring.learn</li>
 * <li>创建时间 : 2018/12/6 14:34</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 1.类（抽象类）， 单继承，多实现
 * 2.接口， 多继承，无实现
 *
 * @author zhengyu
 */

abstract class a1 extends A implements c,d{
    public void say(){};
    abstract void say1();
}


interface c{}
interface d{}
interface e extends c,d{}

class A {}
class f {}
class b extends f implements  c,d,e{

}

public class Dex {

}
