package com.emerzonic.SpringApp.service;


import com.emerzonic.SpringApp.entity.User;

public interface UserService  {

	void createUser(User user);

	User findByUserName(String username);

}
