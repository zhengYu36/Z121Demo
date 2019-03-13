package cn.design.pattern;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern</li>
 * <li>创建时间 : 2018/12/13 16:06</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class CarImpl implements CarOpe {
    @Override
    public void starUp() {
        System.out.println("启动");
    }

    @Override
    public void run() {
        System.out.println("运行");
    }

    @Override
    public void stop() {
        System.out.println("停止");
    }


    @Override
    public void setCarName(String name) {
        System.out.println("名称为:" + name);
    }

    @Override
    public void setCarColor(String color) {
        System.out.println("颜色为:" + color);

    }
}
