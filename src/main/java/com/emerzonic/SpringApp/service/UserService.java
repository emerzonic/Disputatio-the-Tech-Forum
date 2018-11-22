package com.emerzonic.SpringApp.service;


import com.emerzonic.SpringApp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	void addUser(User user);

	User getUser(Integer UserId);

	User findByUserName(String userName);


}
