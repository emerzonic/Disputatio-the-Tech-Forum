package com.emerzonic.SpringApp.DAO;

import com.emerzonic.SpringApp.entity.PostComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository <PostComment, Integer> {

  @EntityGraph(value = "comments.replies",type = EntityGraph.EntityGraphType.LOAD)
  PostComment getById(Integer commentId);


}
