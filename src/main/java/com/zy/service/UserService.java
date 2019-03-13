package com.zy.service;

import com.zy.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {
	
	User selectUserById(Integer userId);  
	
	int insertUser(User user);   //插入
	
	int updateUserById(User user);   //修改

	int updateProperty(String name,String pwd);   //修改多参数
	
	void ope();   //测试事物
}