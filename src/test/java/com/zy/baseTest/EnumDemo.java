package com.zy.baseTest;

/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/7/16 11:22</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public enum EnumDemo {
    aaa("aaabbb","aaa1"),bbb("bbbbbbb","bbb1");

    EnumDemo(String uid, String des) {
        this.uid = uid;
        this.des = des;
    }

    private String uid;
    private String des;


}
