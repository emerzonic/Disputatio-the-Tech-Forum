package com.emerzonic.SpringApp.entity;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
	@Table(name="reply")
	public class Reply {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		@Column(name="text")
		private String text;
		
		@Column(name = "created_on", nullable = false, updatable = false)
		private Timestamp createdOn;

		@Transient
		private String dateString;
		
		@Column(name="author")
		private String author;
		
		@Column(name="comment_id")
		private int commentId;
		
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name="reply_id")
		@MapKey(name = "author")
		private Map<String, Like> likes;

		public Reply() {}

		public Reply(String text, String author, int commentId) {
			this.text = text;
			this.author = author;
			this.commentId = commentId;
			setCreatedOn();

		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
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

		public int getCommentId() {
			return commentId;
		}

		public void setCommentId(int commentId) {
			this.commentId = commentId;
		}

		public Map<String, Like> getLikes() {
			return likes;
		}

		public void setLikes(Map<String, Like> likes) {
			this.likes = likes;
		}
		
		public void toggleLike(Like newLike) {
			if (likes == null) {
				likes = new HashMap<>();
			}
			String authorkey = newLike.getAuthor();
			Like like = likes.get(authorkey);
			if (like == null) {
				likes.put(authorkey, newLike);
				System.out.println("like added");
			} else {
				likes.remove(authorkey);
				System.out.println("like removed");
			}
		}

		@Override
		public String toString() {
			return "Reply [id=" + id + ", text=" + text + ", dateString=" + dateString + ", author=" + author + ", commentId="
					+ commentId + "]";
		}	

}
