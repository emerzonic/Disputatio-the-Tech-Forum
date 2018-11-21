package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.CommentRepository;
import com.emerzonic.SpringApp.DAO.PostRepository;
import com.emerzonic.SpringApp.DAO.UserRepository;
import com.emerzonic.SpringApp.entity.Post;
import com.emerzonic.SpringApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
public class PostServiceImpli implements PostService {
	private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Autowired
    public PostServiceImpli(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;

    }

    @Autowired


	@Override
	@Transactional
	public List<Post> getAllPosts() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
		return postRepository.findAll(sort);
	}
	
	
	@Override
	@Transactional
	public void addPost(Post post) {
        //hardcoding current user
	    User user = userRepository.findById(1).orElse(null);
	    post.setAuthor(user.getUsername());
        post.setCreatedOn();
	    postRepository.save(post);

	}

	@Override
	@Transactional
	public Post getPost(Integer postId) {
	    Post post = postRepository.findById(postId).orElse(null);
	    post.setComments(post.getComments());
	    return post;
	}


	@Override
	@Transactional
	public void updatePost(Post post, Integer postId) {
        Post updatedPost = postRepository.findById(postId).orElse(null);
        updatedPost.setText(post.getText());
        updatedPost.setTitle(post.getTitle());
	}


	@Override
	@Transactional
	public void deletePost(int postId) {
	    postRepository.deleteById(postId);

	}


	@Override
	@Transactional
	public List<Post> searchPosts(String searchTerm) {
		return null;
	}
	
	
}
