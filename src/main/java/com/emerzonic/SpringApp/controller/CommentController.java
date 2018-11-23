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

    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
    }

    //add new comment
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addComment(@RequestBody PostComment comment) {
        System.out.println(comment);
        commentService.addComment(comment);
        return "success";
    }


    //get comment to be edited
    @GetMapping("/edit/{commentId}")
    @ResponseBody
    public PostComment edit(@PathVariable Integer commentId) {
        return commentService.getComment(commentId);
}

    //update comment mapping
    @PostMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String update(@RequestBody PostComment comment) {
        commentService.updateComment(comment.getId(), comment);
        return "success";
    }

    //delete comment
    @GetMapping("/delete/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return "Success";
    }
}
