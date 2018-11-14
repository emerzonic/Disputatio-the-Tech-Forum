package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.repository.PostRepository;
import com.emerzonic.SpringApp.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;




@Service
public class PostServiceImpli implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Override
	@Transactional
	public List<Post> getAllPosts() {
		List<Post> posts = new ArrayList<>();
		postRepository.findAll().forEach(posts::add);
		return posts;
	}
	
	
	@Override
	@Transactional
	public void addPost(Post post) {
	    postRepository.save(post);

	}
//
//
//
	@Override
	@Transactional
	public Post getPost(Integer postId) {
	    return postRepository.findById(postId).orElse(null);
	}
//

	@Override
	@Transactional
	public void updatePost(Post post, Integer postId) {
	    postRepository.save(post);
	}
//
//
//
	@Override
	@Transactional
	public void deletePost(int postId) {
	    postRepository.deleteById(postId);

	}
//
//
	@Override
	@Transactional
	public List<Post> searchPosts(String searchTerm) {
		return null;
	}
	
	
}
