package com.emerzonic.SpringApp.controller;


import com.emerzonic.SpringApp.entity.Post;
import com.emerzonic.SpringApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String getAllPosts(Model model) throws Exception{
       List<Post> posts = postService.getAllPosts();
       model.addAttribute("posts", posts);
//        throw new NullPointerException("this is error");

        return "post/posts";
    }

    //search posts
    @PostMapping("/search")
    public String searchPosts(@RequestParam(defaultValue = "") String searchTerm, Model model) throws Exception{
        List<Post> posts = postService.searchPost(searchTerm);
        model.addAttribute("posts", posts);
        System.out.println(posts);
        return "post/posts";
    }

    //get new post form
    @GetMapping("/new")
    public String newPost(Model model) throws Exception{
        Post post = new Post();
        model.addAttribute("post", post);
        return "post/form";
    }


    //add new post
    @PostMapping("/add")
    public String addPost(@ModelAttribute(value="post") @Valid Post post,
                          BindingResult bindingResult,
                          Model model)throws Exception{
        if(bindingResult.hasErrors()){
            return "post/form";
        }
        postService.addPost(post);
        return "redirect:/post/list";
    }


    //get post details
    @GetMapping("/details/{postId}")
    public String getPost(@PathVariable Integer postId, Model model) throws Exception{
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "post/detail";
    }


    //get post to be edited
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable Integer postId, Model model) throws Exception{
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "post/edit";
    }


    //update post
    @PostMapping("/update/{postId}")
    public String updatePost(@ModelAttribute(value="post") @Valid Post post,
                             BindingResult bindingResult,
                             @PathVariable Integer postId, Model model)throws Exception{
        if(bindingResult.hasErrors()){
            return "post/edit";
        }
        postService.updatePost(post, postId);
        return "redirect:/post/details/"+postId;
    }

    //delete post
    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Integer postId) throws Exception{
        postService.deletePost(postId);
        return "redirect:/post/list";
    }
}

