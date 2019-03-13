package com.zy.effective;

/**
 * <ul>
 * <li>文件包名 : com.zy.effective</li>
 * <li>创建时间 : 2018/7/9 17:29</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：关于覆盖clone方法，需要谨慎去考虑哈.这个东西还是挺复杂的好吧
 *
 * @author zhengyu
 */
public class Flag11 {

    int i = 10;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args){


        Flag11 flag11 = new Flag11();
        System.out.println(flag11.i);
        Flag11 fx = null;
        try {
            fx = (Flag11) flag11.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(fx.i);
    }
}
