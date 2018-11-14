package com.emerzonic.SpringApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_like")
public class Like {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="author")
	private String  author;
	
	@Column(name="post_id")
	private Integer postId;
	
	@Column(name="comment_id")
	private Integer commentId;
	
	@Column(name="reply_id")
	private Integer replyId;

	public Like() {}
	
	public Like(String author, Integer postId, Integer commentId, Integer replyId) {
		this.author = author;
		this.postId = postId;
		this.commentId = commentId;
		this.replyId = replyId;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public int getReplyId() {
		return replyId;
	}


	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}


	@Override
	public String toString() {
		return "Like [id=" + id + ", author=" + author + ", postId=" + postId + ", commentId=" + commentId
				+ ", replyId=" + replyId + "]";
	}
	
	

}
