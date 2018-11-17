package com.emerzonic.SpringApp.controller;


import com.emerzonic.SpringApp.entity.User;
import com.emerzonic.SpringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;

    //inject PostService
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    //get new post form
    @GetMapping("/signup")
    public String newPost(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("page-title", "Sign Up");
        return "user/form";
    }
//
//
    //add new post
    @PostMapping("/signup")
    public String addPost(@ModelAttribute(value="user") User user) {
        userService.addUser(user);
        return "redirect:/post/list";
    }
//
//
//
    //get post details
//    @GetMapping("/details/{postId}")
//    public String getPost(@PathVariable Integer postId, Model model) {
//        Post post = postService.getPost(postId);
//        model.addAttribute("post", post);
//        return "post/detail";
//    }
////
////
////
//    //get post to be edited
//    @GetMapping("/edit/{postId}")
//    public String edit(@PathVariable Integer postId, Model model) {
//        Post post = postService.getPost(postId);
//        model.addAttribute("post", post);
//        return "post/edit";
//    }
////
////
////    //update post
//    @PostMapping("/update/{postId}")
//    public String updatePost(@RequestBody Post post, @PathVariable Integer postId, Model model) {
//        postService.updatePost(post, postId);
//        model.addAttribute("postId", postId);
//        return "forward:/detail?postId=postId";
//    }
////
//
//    @GetMapping("/delete/{postId}")
//    public String deletePost(@PathVariable Integer postId) {
//        postService.deletePost(postId);
//        return "redirect:/post/list";
//    }
}

