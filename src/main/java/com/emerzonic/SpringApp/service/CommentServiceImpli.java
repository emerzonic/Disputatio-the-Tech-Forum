package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.CommentRepository;
import com.emerzonic.SpringApp.DAO.PostRepository;
import com.emerzonic.SpringApp.DAO.UserRepository;
import com.emerzonic.SpringApp.entity.Post;
import com.emerzonic.SpringApp.entity.PostComment;
import com.emerzonic.SpringApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service
public class CommentServiceImpli implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpli(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


	@Override
	@Transactional
	public void addComment(PostComment comment) {
        User user = userRepository.findById(1).orElse(null);
        Post post = postRepository.findById(comment.getPostId()).orElse(null);
        comment.setCreatedOn();
        comment.setAuthor(user.getUsername());
        post.add(comment);
        commentRepository.save(comment);
	}


	@Override
	@Transactional
	public PostComment getComment(Integer commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}


	@Override
	@Transactional
	public void updateComment(Integer commentId, PostComment comment) {
        PostComment updatedComment = commentRepository.findById(commentId).orElse(null);
        updatedComment.setText(comment.getText());
	}


	@Override
	@Transactional
	public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
	}
}
