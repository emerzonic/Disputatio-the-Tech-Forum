//package com.emerzonic.SpringApp.repository;
//
//import com.emerzonic.SpringApp.entity.Post;
//import com.emerzonic.SpringApp.entity.PostComment;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//
//
//@Repository
//public class CommentDAOImpli implements CommentDAO {
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	@Override
//	public void AddComment(Integer postId,String comment) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		//Hardcoding currentUser for now
//		int userId = 1;
//		User currentUser = currentSession.get(User.class, userId);
//		Post post = currentSession.get(Post.class, postId);
//		PostComment newComment = new PostComment(comment, currentUser.getUsername(), postId);
//		post.add(newComment);
//		currentSession.save(post);
//	}
//
//
//	@Override
//	public PostComment getComment(int commentId) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		return currentSession.get(PostComment.class, commentId);
//	}
//
//
//	@Override
//	public void updateComment(int postId, PostComment comment) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		Post post = currentSession.get(Post.class, postId);
//		currentSession.createQuery("UPDATE PostComment set text = :text " + "WHERE id = :commentId")
//		   .setParameter("text",comment.getText())
//		   .setParameter("commentId",comment.getId())
//		   .executeUpdate();
//		currentSession.save(post);
//	}
//
//
//	@Override
//	public void deleteComment(int commentId) {
//		Session currentSession = sessionFactory.getCurrentSession();
//		PostComment comment = currentSession.get(PostComment.class, commentId);
//		currentSession.delete(comment);
//	}
//
//}
