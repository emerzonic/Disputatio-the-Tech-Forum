//package com.emerzonic.SpringApp.service;
//
//import com.emerzonic.SpringApp.repository.CommentDAO;
//import com.emerzonic.SpringApp.entity.PostComment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//
//
//
//@Service
//public class CommentServiceImpli implements CommentService {
//	@Autowired
//	private CommentDAO commentDAO;
//
//
//	@Override
//	@Transactional
//	public void addComment(Integer postId, String comment) {
//		commentDAO.AddComment(postId, comment);
//
//	}
//
//
//	@Override
//	@Transactional
//	public PostComment getComment(int commentId) {
//		return commentDAO.getComment(commentId);
//	}
//
//
//	@Override
//	@Transactional
//	public void updateComment(int postId, PostComment comment) {
//		commentDAO.updateComment(postId, comment);
//
//	}
//
//
//	@Override
//	@Transactional
//	public void deleteComment(int commentId) {
//		commentDAO.deleteComment(commentId);
//
//	}
//
//
//
//
//
//
//
//
//
//}
