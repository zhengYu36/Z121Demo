package com.demo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/12/5 14:31</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： log4j2.xml 我感觉到了恶心，我现在还是弄不来，头疼
 *  //todo fuck.
 *
 * @author zhengyu
 */
public class Log4jDemo {

    //private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static void main(String[] args) throws Exception {

        Logger log = LogManager.getLogger("myLogger");

        log.debug("xxxxaaa");
        log.info("aaaaa");
        log.error("xxx");

    }
}
