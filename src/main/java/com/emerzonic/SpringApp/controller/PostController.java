package com.emerzonic.SpringApp.controller;


import com.emerzonic.SpringApp.entity.Post;
import com.emerzonic.SpringApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {
    private PostService postService;

    //inject PostService
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    //get all posts
    @GetMapping("/list")
    public String getAllPosts(Model model) {
       List<Post> posts = postService.getAllPosts();
       model.addAttribute("posts", posts);
       return "post/posts";
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
    @GetMapping("/new")
    public String newPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "post/new";
    }
//
//
    //add new post
    @PostMapping("/add")
    public String addPost(@RequestBody Post post) {
        System.out.println(post.toString());
//        postService.addPost(post);
        return "redirect:/post/list";
    }
//
//
//
    //get post details
    @GetMapping("/details/{postId}")
    public String getPost(@PathVariable Integer postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "post/detail";
    }
//
//
//
    //get post to be edited
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable Integer postId, Model model) {
        Post showPost = postService.getPost(postId);
        model.addAttribute("post", showPost);
        return "post/edit";
    }
//
//
//    //update post
    @PostMapping("/post/{postId}")
    public String updatePost(@RequestBody Post post, @PathVariable Integer postId) {
        postService.updatePost(post, postId);
        return "detail";
    }
//

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return "redirect:/post/list";
    }
}

