package cn.design.pattern.corp;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.corp</li>
 * <li>创建时间 : 2018/12/24 9:44</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class FirstHandler extends Handler {

    public FirstHandler() {
        super();
        this.domain.put("www.alibaba.com","140.205.94.232");
    }

    @Override
    String response(String domain) {
        return this.handlerMessage(domain);
    }
}
