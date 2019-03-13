package com.zy.effective;

/**
 * <ul>
 * <li>文件包名 : com.zy.effective</li>
 * <li>创建时间 : 2018/7/9 15:09</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：finalizer 方法的使用,这个目前来看，不是我这个级别可以理解的东西哈.
 *
 * @author zhengyu
 */
public class Flag7 {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finazlize");
        super.finalize();
    }

    public static void main(String[] args) {

        Flag7 flag7 = new Flag7();
        try {
            flag7.finalize();
            System.gc();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println(flag7);
        System.out.println("end");
    }
}
