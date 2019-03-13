package cn.design.pattern.dp;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.dp</li>
 * <li>创建时间 : 2018/12/14 16:25</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class DecoratorSource implements Sourcable {

    private Sourcable sourcable;

    public DecoratorSource(Sourcable sourcable){
        super();
        this.sourcable = sourcable;
    }

    @Override
    public void operation() {
        System.out.println("第一个装饰前");
        sourcable.operation();
        System.out.println("第一个装饰后");
    }
}
