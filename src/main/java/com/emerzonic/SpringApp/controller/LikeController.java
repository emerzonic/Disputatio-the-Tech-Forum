package com.emerzonic.SpringApp.controller;

import com.emerzonic.SpringApp.entity.Like;
import com.emerzonic.SpringApp.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    //inject  LikeService
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    //add or remove like
    @PostMapping(value = "/toggle-like",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addReply(@RequestBody Like like) {
        try {
            likeService.toggleLike(like);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

}
