package com.emerzonic.SpringApp.service;


import com.emerzonic.SpringApp.entity.PostComment;

public interface CommentService {


	public void addComment(PostComment comment);

	public PostComment getComment(int commentId);

	public void updateComment(int postId, PostComment comment);

	public void deleteComment(Integer commentId);
}
