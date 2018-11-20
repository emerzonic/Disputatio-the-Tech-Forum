package com.emerzonic.SpringApp.controller;

import com.emerzonic.SpringApp.entity.Like;
import com.emerzonic.SpringApp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
    //inject  ReplyService
    private ReplyService replyService;

    @Autowired
    public LikeController(ReplyService replyService) {
        this.replyService = replyService;
    }


    //toggle post like
    @PostMapping(value = "/toggle-like",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Like addReply(@RequestBody Like like) {
        System.out.println(like);
//        if(){
//
//        }
//        likeService.toggle(like);
        return like;
    }

//    //toggle comment like
//    @PostMapping(value = "/comment",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Reply addReply(@RequestBody Reply reply) {
//        System.out.println(reply);
//        replyService.addReply(reply);
//        return reply;
//    }
//
//    //toggle reply like
//    @PostMapping(value = "/reply",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Reply addReply(@RequestBody Reply reply) {
//        System.out.println(reply);
//        replyService.addReply(reply);
//        return reply;
//    }
}
