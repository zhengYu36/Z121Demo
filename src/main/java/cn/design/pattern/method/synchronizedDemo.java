package cn.design.pattern.method;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.method</li>
 * <li>创建时间 : 2018/12/14 11:00</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class synchronizedDemo {

    public synchronized void test1() throws  Exception{
        System.out.println("方法锁");
        Thread.sleep(2000);
    }

    static {
        System.out.println("static data");
    }

    public synchronized static void someThing() throws  Exception{
        System.out.println("类锁");
        Thread.sleep(2000);
    }

    public static void someThing2() throws  Exception{
        System.out.println("类锁2");
    }
}
