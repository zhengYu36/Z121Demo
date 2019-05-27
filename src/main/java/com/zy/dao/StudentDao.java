package com.zy.dao;

import com.zy.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>文件包名 : com.zy.dao</li>
 * <li>创建时间 : 2019/5/24 16:44</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明： student dao
 *
 * @author zhengyu
 */
public interface StudentDao {

    void save(@Param("student") Student student, @Param("name") String name);

    void update(int id);

    List<Map> select(String id);

    void delete(int id);

    List<Map> select2(@Param("id") String id);
}
