package com.emerzonic.SpringApp.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "post")
@DynamicUpdate
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "text")
	private String text;

	@Column(name = "created_on", nullable = false, updatable = false)
	private Timestamp createdOn;

	@Transient
	private String dateString;

	@Column(name = "author",nullable = false)
	private String author;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	@OrderBy("id DESC")
	private List<PostComment> comments;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	@MapKey(name = "author")
	private Map<String, Like> likes;

	public Post() {

	}

	public Post(String title, String text, String author) {
		this.title = title;
		this.text = text;
		this.author = author;
		setCreatedOn();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn() {
		System.out.println("reached here!");
		this.createdOn = new Timestamp(System.currentTimeMillis());
	}

	public String getDateString() {
		if (dateString == null) {
			dateString = DateTimeFormatter.ofPattern("E, MMM. dd yyyy").format(createdOn.toLocalDateTime());
		}
		return dateString;
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

	public List<PostComment> getComments() {
		return comments;
	}

	public void setComments(List<PostComment> comments) {
		this.comments = comments;
	}

	public Map<String, Like> getLikes() {
		return likes;
	}

	public void setLikes(Map<String, Like> likes) {
		this.likes = likes;
	}

	public void add(PostComment newComment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		comments.add(newComment);
	}

	
	public boolean toggleLike(Like newLike) {
		boolean feedback = true;
		if (likes == null) {
			likes = new HashMap<>();
		}
		String authorkey = newLike.getAuthor();
		Like like = likes.get(authorkey);
		if (like == null) {
			likes.put(authorkey, newLike);
			System.out.println("like added");
			feedback = false;

		} else {
			likes.remove(authorkey);
			System.out.println("like removed");
		}
		return feedback;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + ", createdOn=" + createdOn + ", dateString="
				+ dateString + ", author=" + author + "]";
	}

}
