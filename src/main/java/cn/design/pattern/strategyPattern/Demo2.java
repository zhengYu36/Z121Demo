package cn.design.pattern.strategyPattern;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.strategyPattern</li>
 * <li>创建时间 : 2018/12/17 11:20</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Demo2 {

    public static final String NAME = "22";

    public static void main(String[] args) {
       // System.out.println(Demo.RED);

        String str = Demo.getName(2);
        System.out.println(str);
    }
}
