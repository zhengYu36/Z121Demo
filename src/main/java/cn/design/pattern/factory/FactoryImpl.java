package cn.design.pattern.factory;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.factory</li>
 * <li>创建时间 : 2018/12/14 14:15</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 工厂模式通过泛型来实现
 *
 * @author zhengyu
 */
public class FactoryImpl implements Factory {


    //传统的方式实现工厂模式
   @Override
    public Car createCar(String carType) {
        Car car = null;
        if("HongQi".equals(carType)){
            car = new HongQI();
        }else if("BYD".equals(carType)){
            car = new BYD();
        }

        return  car;
    }

    //采用泛型实现工厂模式
    @Override
    public <T extends Car> T createCar(Class<T> clazz) {
       Car car = null;
       if(clazz != null){
           try {
               car = (Car)Class.forName(clazz.getName()).newInstance();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
        return (T)car;
    }
}
