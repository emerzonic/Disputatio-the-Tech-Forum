package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.CommentRepository;
import com.emerzonic.SpringApp.DAO.ReplyRepository;
import com.emerzonic.SpringApp.entity.PostComment;
import com.emerzonic.SpringApp.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReplyServiceImpli implements ReplyService {
    private CommentRepository commentRepository;
    private UserService userService;
    private ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImpli(CommentRepository commentRepository, UserService userService, ReplyRepository replyRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.replyRepository = replyRepository;
    }


	@Override
	@Transactional
	public void addReply(Reply reply) {
        String author = userService.getCurrentUserUsername();
        PostComment comment = commentRepository.findById(reply.getCommentId()).orElse(null);
        reply.setCreatedOn();
        reply.setAuthor(author);
        comment.add(reply);
        replyRepository.save(reply);
	}


	@Override
	@Transactional
	public Reply getReply(Integer replyId) {
		return replyRepository.findById(replyId).orElse(null);
	}


	@Override
	@Transactional
	public void updateReply(Integer replyId, Reply reply) {
        Reply updatedReply = replyRepository.findById(replyId).orElse(null);
        updatedReply.setText(reply.getText());
	}


	@Override
	@Transactional
	public void deleteReply(Integer replyId) {
        replyRepository.deleteById(replyId);
	}
}
