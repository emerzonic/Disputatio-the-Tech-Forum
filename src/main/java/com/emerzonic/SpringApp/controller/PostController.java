package com.emerzonic.SpringApp.controller;


import com.emerzonic.SpringApp.entity.Post;
import com.emerzonic.SpringApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    //inject PostService
    @Autowired
    private PostService postService;


    //get all posts
    @GetMapping("/postlist")
    public List<Post> getAllPosts() {
       return postService.getAllPosts();
    }

////    //search posts
//    @PostMapping("/search")
//    public String searchPosts()  {
//        return postService.searchPosts(id);
////        model.addAttribute("posts", posts);
//        return  "show-posts";
//    }
//
//
//    //get new post form
    @GetMapping("/post/new")
    public String newPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "home";
    }
//
//
    //add new post
    @PostMapping("/post")
    public String addPost(@RequestBody Post post) {
        postService.addPost(post);
        return "done";
    }
//
//
//
    //get post details
    @GetMapping("/post/{postId}")
    public Post getPost(@PathVariable Integer postId) {
        return postService.getPost(postId);
    }
//
//
//
    //get post to be edited
    @GetMapping("/post/edit/{postId}")
    public String edit(@PathVariable Integer postId, Model model) {
        Post showPost = postService.getPost(postId);
        model.addAttribute("post", showPost);
        return "edit-form";
    }
//
//
//    //update post
    @PostMapping("/post/{postId}")
    public String updatePost(@RequestBody Post post, @PathVariable Integer postId) {
        postService.updatePost(post, postId);
        return "post-detail";
    }
//

    @GetMapping("/delete")
    public String deletePost(@RequestParam("postId") int postId) {
        postService.deletePost(postId);
        return "redirect:/post/list";
    }
}

