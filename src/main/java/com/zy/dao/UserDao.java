package com.zy.dao;

import com.zy.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    /**
     * @param userId
     * @return User
     * 根据id查询数据
     */
    User selectUserById(Integer userId);
    
    //根据实体插入数据
    int insertUser(User user);
    
    //根据实体修改数据(实体中的uerid来修改数据)
    int updateUserById(User user);

    //更新操作
    int updateProperty(@Param(value = "name") String name,@Param(value = "pwd") String pwd);

    int insert2(User user);

}