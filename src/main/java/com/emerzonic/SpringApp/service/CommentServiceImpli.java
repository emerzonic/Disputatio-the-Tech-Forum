package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.CommentRepository;
import com.emerzonic.SpringApp.DAO.PostRepository;
import com.emerzonic.SpringApp.entity.Post;
import com.emerzonic.SpringApp.entity.PostComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service
public class CommentServiceImpli implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public CommentServiceImpli(CommentRepository commentRepository, PostRepository postRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }


	@Override
	@Transactional
	public void addComment(PostComment comment) {
        String author = userService.getCurrentUserUsername();
        Post post = postRepository.findById(comment.getPostId()).orElse(null);
        comment.setCreatedOn();
        comment.setAuthor(author);
        post.addComment(comment);
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
