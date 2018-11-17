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
        model.addAttribute("page-title", "New Post");
        return "post/form";
    }
//
//
    //add new post
    @PostMapping("/add")
    public String addPost(@ModelAttribute(value="post") Post post) {
        postService.addPost(post);
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


    //get post to be edited
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable Integer postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        model.addAttribute("page-title", "Edit Post");
        return "post/edit";
    }


    //update post
    @PostMapping("/update/{postId}")
    public String updatePost(@ModelAttribute(value="post") Post post, @PathVariable Integer postId, Model model) {
        System.out.println(post.toString());
        postService.updatePost(post, postId);
        return "redirect:/post/details/"+postId;
    }

    //delete post
    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return "redirect:/post/list";
    }
}

