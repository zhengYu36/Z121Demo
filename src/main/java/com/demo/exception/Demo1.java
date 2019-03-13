package com.demo.exception;

/**
 * <ul>
 * <li>文件包名 : com.demo.exception</li>
 * <li>创建时间 : 2019/1/4 13:56</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {

        /*if(true){
            throw new InterfaceServiceException("测试自定义异常");
        }*/

        if(true){
            //为什么不能推荐这样用，上面这种方式是其他类已经抛出了异常了
            throw new Exception("zzz");
        }


    }
}
