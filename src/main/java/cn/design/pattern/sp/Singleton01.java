package cn.design.pattern.sp;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.sp</li>
 * <li>创建时间 : 2018/12/14 10:17</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Singleton01 {

    private static volatile Singleton01 singleton01 = null;

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        if (singleton01 == null) {
            synchronized (Singleton01.class) {
                if (singleton01 == null) {
                    singleton01 = new Singleton01();
                }
            }
        }
        return singleton01;
    }
}
