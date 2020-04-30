package com.zy.service;

import com.zy.dao.UserDao;
import com.zy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public User selectUserById(Integer userId) {
        return userDao.selectUserById(userId);
    }


    public int insertUser(User user) {
        return userDao.insertUser(user);
    }


    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    public List<User> queryList(User user) {
        System.out.println("00000000");
        //List<User> users = userDao.queryList(user);
        userDao.queryList2();
        System.out.println("11111");
        return null;
    }


    public int updateProperty(String name,String pwd) {
        return userDao.updateProperty(name,pwd);
    }

    //测试事物
    @Transactional(rollbackFor = Exception.class)
    public void ope() {
       /* //模拟数据
        User user = new User(1003, "ttt", "22222");
        //插入
        insertUser(user);
        //修改
        User user1 = new User(1003, "ttt3333", "22222");
        updateUserById(user1);*/
        //抛出异常
       /* if (1 > 0) {
            System.out.println("cccc");
            throw new RuntimeException();
        }*/

    }
}