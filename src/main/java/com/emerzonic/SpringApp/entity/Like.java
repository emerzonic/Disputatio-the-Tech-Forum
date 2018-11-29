package com.emerzonic.SpringApp.entity;

import javax.persistence.*;

@Entity
@Table(name="user_like")
public class Like {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
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
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
			this.postId = postId;
	}


	public Integer getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
			this.commentId = commentId;
	}


	public Integer getReplyId() {
		return replyId;
	}


	public void setReplyId(Integer replyId) {
			this.replyId = replyId;
	}


	@Override
	public String toString() {
		return "Like [id=" + id + ", author=" + author + ", postId=" + postId + ", commentId=" + commentId
				+ ", replyId=" + replyId + "]";
	}
	
	

}
