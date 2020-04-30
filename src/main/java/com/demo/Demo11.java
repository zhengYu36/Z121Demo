package com.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2020/1/13 13:47</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：map 数据求和
 *
 * @author zhengyu
 */
public class Demo11 {

    public static void main(String[] args) {
        List<Map> mapList = new ArrayList<>();

        Map m1 =new HashMap();
        m1.put("score",2);
        m1.put("name","zhangsan");

        Map m2 =new HashMap();
        m2.put("score",12);
        m2.put("name","lishi");

        Map m3 =new HashMap();
        m3.put("score",22);
        m3.put("name","wangwu");

        mapList.add(m1);
        mapList.add(m2);
        mapList.add(m3);

        //数据求和的相关问题信息
        long aaa= mapList.stream().collect(Collectors.summarizingInt(e->Integer.parseInt(e.get(
                "score").toString()))).getSum();

        System.out.println(aaa);
        System.out.println("我不晓得自己怎么了，就是感觉自己点都不好，感觉被感冒了");

    }
}
