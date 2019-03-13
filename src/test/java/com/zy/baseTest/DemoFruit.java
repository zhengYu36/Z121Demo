package com.zy.baseTest;

/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/5/21 10:17</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class DemoFruit{
    private String name;
    private int price;

    public String getName() {
        //return name;
        return "fruit name";
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
