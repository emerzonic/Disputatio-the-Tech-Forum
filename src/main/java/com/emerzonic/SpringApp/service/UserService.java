package com.emerzonic.SpringApp.service;


import com.emerzonic.SpringApp.entity.User;

public interface UserService {

	void addUser(User user);

	User getUser(Integer UserId);

//	void updatePost(Post post, Integer postId);
//
//	void deletePost(int postId);


}
