package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.entity.Post;

import java.util.List;



public interface PostService {
	
	List<Post> getAllPosts();

	void addPost(Post post);

	Post getPost(Integer postId);

	void updatePost(Post post, Integer postId);

	void deletePost(int postId);

	List<Post> searchPosts(String searchTerm);
}
