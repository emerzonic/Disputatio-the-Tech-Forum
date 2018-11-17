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
        System.out.println(comment);
        commentService.addComment(comment);
        return comment;
    }


//    get comment to be edited
//    @GetMapping("/edit")
//    public String editComment(@RequestParam("postId")int postId, @RequestParam("commentId")int commentId, Model model) {
//        PostComment comment = commentService.getComment(commentId);
//        Post post = postService.getPost(postId);
//        PostCommentUtil postcomment = new PostCommentUtil();
//        postcomment.setPost(post);
//        postcomment.setComment(comment);
//        post.add(comment);
//        model.addAttribute("postcomment", postcomment);
//        return "edit-comment";
//
//}

    //update comment mapping
//    @PostMapping("/update")
//    public String updateComment(@ModelAttribute("postcomment") PostCommentUtil post, Model model) {
//        int postId = post.getPost().getId();
//        PostComment comment = post.getComment();
//        commentService.updateComment(postId, comment);
//        Post updatedPost = postService.getPost(postId);
//        model.addAttribute("post", updatedPost);
//        return "post-detail";
//    }
//
    //delete comment
    @GetMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Integer commentId) {
        System.out.println(commentId);
//        commentService.deleteComment(commentId);
        return null;
    }
}
