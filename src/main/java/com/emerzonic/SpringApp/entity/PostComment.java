package com.emerzonic.SpringApp.entity;

import com.emerzonic.SpringApp.util.GenerateDateString;
import com.emerzonic.SpringApp.util.HandleLike;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Entity
@NamedEntityGraph(
				name = "comments.replies",
				includeAllAttributes = true,
				attributeNodes = {
								@NamedAttributeNode(value = "likes"),
								@NamedAttributeNode(value = "replies", subgraph = "replies.likes")
				},
				subgraphs = {
								@NamedSubgraph(
												name = "replies.likes",
												attributeNodes = {
																@NamedAttributeNode(value = "likes")
												}
								)
				}
)
@Table(name = "comment")
public class PostComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "text")
	private String text;

	@Column(name = "created_on", nullable = false, updatable = false)
	private Timestamp createdOn;

	@Transient
	private String dateString;

	@Column(name = "author")
	private String author;

	@Column(name = "post_id")
	private Integer postId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id")
	@OrderBy("id DESC")
	private Set<Reply> replies;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id")
	@MapKey(name = "author")
	private Map<String, Like> likes;


	public PostComment() {
	}

	public PostComment(String text, String author, Integer postId) {
		this.text = text;
		this.author = author;
		this.postId = postId;
		setCreatedOn();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCreatedOn() {
		this.createdOn = new Timestamp(System.currentTimeMillis());
	}


	public String getDateString() {
	    GenerateDateString date = new GenerateDateString();
		return date.getDateString(dateString, createdOn);
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
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

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public Map<String, Like> getLikes() {
		return likes;
	}

	public void setLikes(Map<String, Like> likes) {
		this.likes = likes;
	}

	public void add(Reply newReply) {
		if (replies == null) {
			replies = new HashSet<>();
		}
		replies.add(newReply);
	}

	public boolean toggleLike(Like newLike) {
        HandleLike handleLike = new HandleLike();
        return handleLike.toggleLike(likes, newLike);
	}


	@Override
	public String toString() {
		return "PostComment [id=" + id + ", text=" + text + ", createdOn=" + createdOn + ", dateString=" + dateString
				+ ", author=" + author + ", postId=" + postId + "]";
	}

}
