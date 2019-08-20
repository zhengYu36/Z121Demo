package com.zy.service;

import com.zy.dao.UserDao;
import com.zy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //User user = userService.selectUserById(10001);
        User user = new User();
        user.setUserName(username);
        List<User> users = userService.queryList(user);
        if(users !=null && users.size() > 0){

            return users.get(0);
        }

    return null;
        //return user;
    }
}
