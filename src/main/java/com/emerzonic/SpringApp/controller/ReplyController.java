package com.emerzonic.SpringApp.controller;

import com.emerzonic.SpringApp.entity.Reply;
import com.emerzonic.SpringApp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
public class ReplyController {
    //inject  ReplyService
    private ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }


    //add new reply
    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addReply(@RequestBody Reply reply) throws Exception{
        replyService.addReply(reply);
        return "Success";
    }


    //get reply to be edited
    @GetMapping("/edit/{replyId}")
    public Reply edit(@PathVariable Integer replyId) throws Exception{
        return replyService.getReply(replyId);
    }


    //update reply mapping
    @PostMapping(value = "/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String update(@RequestBody Reply reply) throws Exception{
        replyService.updateReply(reply.getId(), reply);
        return "Success";
    }


    //delete reply
    @GetMapping("/delete/{replyId}")
    @ResponseBody
    public String deleteReply(@PathVariable Integer replyId) throws Exception{
        replyService.deleteReply(replyId);
        return "Success";
    }
}
