package cn.design.pattern.lod;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.lod</li>
 * <li>创建时间 : 2018/12/14 9:56</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class MasterTest {
    public static void main(String[] args) {
        Master master = new Master();
        master.cookComand(new Butler());
    }
}
