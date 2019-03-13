package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.bean.Ab;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2018/9/19 16:12</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 * fastjson 阿里巴巴的快速学习
 *
 * 关于json 和 对象 的互相转换，需要特别注意
 * 参考url: https://www.cnblogs.com/sunp823/p/5601399.html
 *
 * @author zhengyu
 */

public class JSONDemo {

    public static void main(String[] args) {

        //String str = "{\"name\":\"1234\",\"age\":123}";

        String str = "[{\"name\":\"1234\",\"age\":123},{\"name\":\"aaa\",\"age\":66}]";

        List<Ab> list = new ArrayList<Ab>();
        Ab ab = new Ab("aa", 222);
        Ab ab1 = new Ab("a1a", 3234);
        Ab ab2 = new Ab("a2a", 52);
        list.add(ab);
        list.add(ab1);
        list.add(ab2);
        str = JSON.toJSONString(list);
        System.out.println(str);

        // 转换成对象
        //Object obj = JSON.parse(str);
        //JSONObject obj = JSON.parseObject(str);
        // Ab obj = JSON.parseObject(str,Ab.class);
        //JSONArray obj = JSON.parseArray(str);
        List<Ab> obj = JSON.parseArray(str, Ab.class);
       // obj.stream().forEach(i -> System.out.println(i));
        System.out.println(obj);

    }
}
