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
public class DemoApple extends  DemoFruit{

    private String appleType;

    private String name;

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getAppleType() {
        return appleType;
    }

    public void setAppleType(String appleType) {
        this.appleType = appleType;
    }
}
