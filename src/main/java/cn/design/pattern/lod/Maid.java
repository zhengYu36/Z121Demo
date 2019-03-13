package cn.design.pattern.lod;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.lod</li>
 * <li>创建时间 : 2018/12/14 9:44</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Maid {

    private String maidName;

    public Maid() {
        super();
    }

    public Maid(String maidName) {
        super();
        this.maidName = maidName;
    }

    public void cook(){
        System.out.println(this.maidName + ">>做饭！");
    }

}
