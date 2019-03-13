package cn.design.pattern.factory;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.factory</li>
 * <li>创建时间 : 2018/12/14 14:10</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class BYD implements Car {

    private String name;
    private String color;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void setCarName(String name) {
        this.name = name;
        System.out.println("BYD name:"+name);
    }

    @Override
    public void setCarColor(String color) {
        this.color = color;
        System.out.println("BYD color:"+color);
    }
}
