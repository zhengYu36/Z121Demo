package com.zy.dao;

import java.util.List;

/**
 * <ul>
 * <li>文件包名 : com.zy.dao</li>
 * <li>创建时间 : 2019/5/31 10:18</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： 基本mapper 基类
 *
 * @author zhengyu
 */
public interface BaseMapper<T> {

    void save(T t);

    T findById(String id);

    List<T> queryList(T t);
}
