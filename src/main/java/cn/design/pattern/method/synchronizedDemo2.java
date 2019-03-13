package cn.design.pattern.method;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.method</li>
 * <li>创建时间 : 2018/12/14 11:23</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class synchronizedDemo2 {

    public static void main(String[] args) throws Exception{

        synchronizedDemo syx = new synchronizedDemo();
        for (int i=0 ;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronizedDemo.someThing();
                        synchronizedDemo.someThing2();
                        //syx.test1();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
