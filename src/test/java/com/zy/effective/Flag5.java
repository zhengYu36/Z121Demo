package com.zy.effective;

/**
 * <ul>
 * <li>文件包名 : com.zy.effective</li>
 * <li>创建时间 : 2018/7/6 9:42</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：创建不必要的类
 *
 * @author zhengyu
 */
public class Flag5 {

    public static void main(String[] args) {
        // 通过new 表示在内存区新开辟一块
        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);

        // 目前的java虚拟机会对String 进行特殊的处理， 如果 "abc" 已经存在在内存中了，如果 s4又等于 "abc"
        // 这个时候不会再新创建，而只会引用 s3的地址
        String s3 = "abc";
        String s4 = "abc";
        System.out.println(s3 == s4);
        ////////////////////////////

        // 这里的sum 用 Long 和 用 long 执行的时间效率完全不一样，用Long则每次都会去
        // 实例化一个Long 对象，而long 基础数据类型，则不会，so, 如果是大量数量的
        // 修改，则考虑用基础数据类型，大不了再最后来进行类型的转换好了
        long sum = 0L;
        for(long i=0;i<Integer.MAX_VALUE;i++){
            sum +=i;
        }
        System.out.println(sum);

    }

}
