package cn.design.pattern.corp;

import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.corp</li>
 * <li>创建时间 : 2018/12/24 9:36</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public abstract class Handler {

    private Handler nextHandler;

    protected Map<String,String> domain = new HashMap<>();

    public final String handlerMessage(String domain){
        if(this.domain !=null && this.domain.containsKey(domain)){
            return this.domain.get(domain);
        }else{
            return this.nextHandler.handlerMessage(domain);
        }
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void setDomain(Map<String, String> domain) {
        this.domain = domain;
    }

    //返回的方法
    abstract String response(String domain);

}
