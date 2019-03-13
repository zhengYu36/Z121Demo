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
public class Singleton02 {

    private static final Singleton02 singleton01 = new Singleton02();

    private Singleton02() {
    }

    public static Singleton02 getInstance() {
        return singleton01;
    }
}
