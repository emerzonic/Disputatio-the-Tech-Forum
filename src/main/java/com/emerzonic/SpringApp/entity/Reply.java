package com.emerzonic.SpringApp.entity;

import com.emerzonic.SpringApp.util.GenerateDateString;
import com.emerzonic.SpringApp.util.HandleLike;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

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

        @Transient
        private HandleLike handleLike;


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
            GenerateDateString generateDateString = new GenerateDateString();
            return generateDateString.getDateString(dateString, createdOn);
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

		public boolean toggleLike(Like newLike) {
            handleLike = new HandleLike();
            return handleLike.toggleLike(likes, newLike);
		}


		@Override
		public String toString() {
			return "Reply [id=" + id + ", text=" + text + ", dateString=" + dateString + ", author=" + author + ", commentId="
					+ commentId + "]";
		}	

}
