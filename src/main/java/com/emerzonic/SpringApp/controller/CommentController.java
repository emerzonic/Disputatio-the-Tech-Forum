package com.emerzonic.SpringApp.controller;

import com.emerzonic.SpringApp.entity.PostComment;
import com.emerzonic.SpringApp.service.CommentService;
import com.emerzonic.SpringApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    //inject PostService
    private CommentService commentService;
    private PostService postService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    //add new comment
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostComment addComment(@RequestBody PostComment comment) {
        commentService.addComment(comment);
        return comment;
    }


    //get comment to be edited
    @GetMapping("/edit/{commentId}")
    public PostComment edit(@PathVariable Integer commentId) {
        return commentService.getComment(commentId);
}

    //update comment mapping
    @PostMapping(value = "/update/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostComment update(@RequestBody PostComment comment,@PathVariable Integer commentId) {
        commentService.updateComment(commentId, comment);
        return comment;
    }

    //delete comment
    @GetMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return "deleted";
    }
}
