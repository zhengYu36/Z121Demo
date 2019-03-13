package cn.design.pattern.lod;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : cn.design.pattern.lod</li>
 * <li>创建时间 : 2018/12/14 9:53</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Butler {


    public void cook() {
        List<Maid> list = new ArrayList<>();
        for(int i=0;i<2;i++){
            list.add(new Maid("maid" + i));
        }
        list.forEach(i -> {
            i.cook();
        });
    }
}
