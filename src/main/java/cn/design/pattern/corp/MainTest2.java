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
public class MainTest2 {

    public String getName(String name,Object ... args){
        System.out.println(name);
        for(Object obj: args){
            System.out.println(obj);
        }
        return "xxx";
    }

    public static void main(String[] args) {
        MainTest2 mainTest2 = new MainTest2();
        mainTest2.getName("aa","aaaa",2,"4");

    }
}
