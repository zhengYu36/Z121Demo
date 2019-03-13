package cn.design.pattern.corp;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.corp</li>
 * <li>创建时间 : 2018/12/24 9:42</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class MainTest {

    public static void main(String[] args) {
        Handler local = new LocalHandler();
        Handler first = new FirstHandler();
        Handler second = new SecondHandler();

        local.setNextHandler(first);
        first.setNextHandler(second);

        /*String ip = local.handlerMessage("www.alibaba.com1");
        System.out.println(ip);*/

        System.out.println(local.response("www.alibaba.com"));
    }
}
