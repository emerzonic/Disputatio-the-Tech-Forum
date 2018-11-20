package com.emerzonic.SpringApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

//	public Like() {}
	
//	public Like(String author, Integer postId, Integer commentId, Integer replyId) {
//		this.author = author;
//		this.postId = postId;
//		this.commentId = commentId;
//		this.replyId = replyId;
//	}
	
	
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

    @JsonIgnore
	public int getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		if(postId == null) {
			this.postId = null;
		}else {
			this.postId = postId;
		}

	}

    @JsonIgnore
	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
		if(commentId == null) {
			this.commentId = null;
		}else {
			this.commentId = commentId;
		}
	}

    @JsonIgnore
	public int getReplyId() {
		return replyId;
	}


	public void setReplyId(Integer replyId) {
		if(replyId == null) {
			this.replyId = null;
		}else {
			this.replyId = replyId;
		}
	}


	@Override
	public String toString() {
		return "Like [id=" + id + ", author=" + author + ", postId=" + postId + ", commentId=" + commentId
				+ ", replyId=" + replyId + "]";
	}
	
	

}
