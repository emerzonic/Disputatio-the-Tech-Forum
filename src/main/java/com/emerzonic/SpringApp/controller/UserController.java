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

    //inject UserService
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    //get new post form
    @GetMapping("/signup")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("page-title", "Sign Up");
        return "user/user-form";
    }
//
//
    //add new post
    @PostMapping("/signup")
    public String addUser(@ModelAttribute(value="user") User user) {
        System.out.println(user);
        userService.addUser(user);
        return "redirect:/post/list";
    }




}

