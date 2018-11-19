package com.emerzonic.SpringApp.service;


import com.emerzonic.SpringApp.entity.PostComment;

public interface CommentService {


	public void addComment(PostComment comment);

	public PostComment getComment(Integer commentId);

	public void updateComment(Integer commentId, PostComment comment);

	public void deleteComment(Integer commentId);
}
