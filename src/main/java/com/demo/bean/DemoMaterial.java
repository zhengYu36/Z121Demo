package com.demo.bean;



/**
 * <ul>
 * <li>文件包名 : com.demo.bean</li>
 * <li>创建时间 : 2018/11/9 10:31</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */

public class DemoMaterial {

    private String  MDM_TYPE;

    public String getMDM_TYPE() {
        return MDM_TYPE;
    }

    public void setMDM_TYPE(String MDM_TYPE) {
        this.MDM_TYPE = MDM_TYPE;
    }

    @Override
    public String toString() {
        return "DemoMaterial{" +
                "MDM_TYPE='" + MDM_TYPE + '\'' +
                '}';
    }
}
