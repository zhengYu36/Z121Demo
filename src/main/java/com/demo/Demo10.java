package com.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.demo</li>
 * <li>创建时间 : 2019/7/25 10:36</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 * 参考url:  http://www.zuidaima.com/share/3039487115938816.htm
 * list 进行分页
 *
 * @author zhengyu
 */
public class Demo10 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        int page = 2;
        int count = 3;

        int size = list.size();
        int fromIndex = count * (page - 1);
        int toIndex = fromIndex + count;
        if (toIndex >= size) {
            toIndex = size;
        }

        System.out.println(list.subList(fromIndex, toIndex));
    }
}
