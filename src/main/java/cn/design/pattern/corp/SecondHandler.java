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
public class SecondHandler extends Handler {

    public SecondHandler() {
        super();
        this.domain.put("www.tecent.com","183.60.38.45");
    }

    @Override
    String response(String domain) {
        return this.handlerMessage(domain);
    }
}
