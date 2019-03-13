package cn.design.pattern.dp;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.dp</li>
 * <li>创建时间 : 2018/12/14 16:24</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class SourceImpl implements Sourcable {
    @Override
    public void operation() {
        System.out.println("原始的方法");
    }
}
