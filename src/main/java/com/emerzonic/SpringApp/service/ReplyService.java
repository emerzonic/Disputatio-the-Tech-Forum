package com.emerzonic.SpringApp.service;


import com.emerzonic.SpringApp.entity.Reply;

public interface ReplyService{


	public void addReply(Reply reply);

	public Reply getReply(Integer replyId);

	public void updateReply(Integer replyId, Reply reply);

	public void deleteReply(Integer replyId);
}
