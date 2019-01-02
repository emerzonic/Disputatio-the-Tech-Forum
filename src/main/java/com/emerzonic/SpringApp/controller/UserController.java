package com.emerzonic.SpringApp.controller;


import com.emerzonic.SpringApp.entity.User;
import com.emerzonic.SpringApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;

    //inject UserService
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


   //show new user signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) throws Exception{
        User user = new User();
        model.addAttribute("user", user);
        return "user-forms/signup-form";
    }


    //process new user
    @PostMapping("/signup")
    public String createUser(@ModelAttribute(value="user") @Valid User user,
                             BindingResult bindingResult,
                             Model model) throws Exception{
        if(bindingResult.hasErrors()){
            return "user-forms/signup-form";
        }
        if(userService.checkIfUserExist(user.getUsername())){
            model.addAttribute("exist" ,true);
            return "user-forms/signup-form";
        }
        userService.createUser(user);
        return "redirect:/user/login";
    }


    //show login form
    @GetMapping("/login")
    public String showLoginForm(Model model) throws Exception{
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("page-title", "Sign Up");
        return "user-forms/login-form";
    }

}

