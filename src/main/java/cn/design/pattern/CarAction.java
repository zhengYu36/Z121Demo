package cn.design.pattern;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern</li>
 * <li>创建时间 : 2018/12/13 16:04</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public interface CarAction {

    /** 启动 */
    void starUp();

    /** 运行 */
    void run();

    /** 停止 */
    void stop();
}
