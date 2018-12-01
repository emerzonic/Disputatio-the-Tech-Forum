package com.emerzonic.SpringApp.entity;

import com.emerzonic.SpringApp.util.GenerateDateString;
import com.emerzonic.SpringApp.util.HandleLike;
import com.emerzonic.SpringApp.util.Toggleable;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Entity
@NamedEntityGraph(
        name = "post.comments.replies",
        includeAllAttributes = true,
        attributeNodes = {
                @NamedAttributeNode(value = "likes"),
                @NamedAttributeNode(value = "comments", subgraph = "comments.replies")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "comments.replies",
                        attributeNodes = {
                                @NamedAttributeNode(value = "replies"),
                                @NamedAttributeNode(value = "likes")
                        }
                )
        }
)
@Table(name = "post")
@DynamicUpdate
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @OrderBy("id DESC")
  private Integer id;

  @NotNull
  @NotEmpty
  @Column(name = "title")
  private String title;

  @NotNull
  @NotEmpty
  @Column(name = "text")
  private String text;

  @Column(name = "created_on", nullable = false, updatable = false)
  private Timestamp createdOn;

  @Transient
  private String dateString;

  @Column(name = "author",nullable = false)
  private String author;

  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  @OrderBy("id DESC")
  private Set<PostComment> comments;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String value) {
    this.title = value.trim();
  }

  public String getText() {
    return text;
  }

  public void setText(String value) {
    this.text = value.trim();
  }

  public Timestamp getCreatedOn() {
    return createdOn;
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

  public Set<PostComment> getComments() {
    return comments;
  }

  public void setComments(Set<PostComment> comments) {
    this.comments = comments;
  }

  public Map<String, Like> getLikes() {
    return likes;
  }

  public void setLikes(Map<String, Like> likes) {
    this.likes = likes;
  }

  public void addComment(PostComment newComment) {
    if (comments == null) {
      comments = new HashSet<>();
    }
    comments.add(newComment);
  }


  public boolean toggleLike(Like newLike) {
    Toggleable toggleable = new HandleLike();
    return toggleable.toggleLike(likes,newLike);
  }

  @Override
  public String toString() {
    return "Post [id=" + id + ", title=" + title + ", text=" + text + ", createdOn=" + createdOn + ", dateString="
            + dateString + ", author=" + author + "]";
  }

}
