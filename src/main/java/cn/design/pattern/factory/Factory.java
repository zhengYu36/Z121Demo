package cn.design.pattern.factory;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.factory</li>
 * <li>创建时间 : 2018/12/14 14:14</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public interface Factory {

    Car createCar(String carType);

    //<T extends Car> T createCar(Class<T> clazz);
    <T extends Car> T createCar(Class<T> clazz);

}
