package com.zy.baseTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.zy.baseTest</li>
 * <li>创建时间 : 2018/5/23 14:56</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Demo4 {

    public static void main(String[] args) throws Exception {

        String str = "2018-05-21 13:23:12.0";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(str);

        List<DemoApple> list = new ArrayList<DemoApple>();

        for (DemoApple d1 : list) {
            System.out.println(d1);
        }


        System.out.println(sdf.format(date));
    }
}
