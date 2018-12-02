package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.CommentRepository;
import com.emerzonic.SpringApp.DAO.LikeRepository;
import com.emerzonic.SpringApp.DAO.PostRepository;
import com.emerzonic.SpringApp.DAO.ReplyRepository;
import com.emerzonic.SpringApp.entity.Like;
import com.emerzonic.SpringApp.entity.Post;
import com.emerzonic.SpringApp.entity.PostComment;
import com.emerzonic.SpringApp.entity.Reply;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LikeServiceImpli implements LikeService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserService userService;
    private LikeRepository likeRepository;
    private ReplyRepository replyRepository;

    public LikeServiceImpli(CommentRepository commentRepository, PostRepository postRepository, UserService userService, LikeRepository likeRepository, ReplyRepository replyRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeRepository = likeRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    @Transactional
    public void toggleLike(Like like) {
        String author = userService.getCurrentUserUsername();
        boolean hasLike = false;
        like.setAuthor(author);
        if (like.getPostId() != 0) {
            Post post = postRepository.getById(like.getPostId());
            hasLike = post.toggleLike(like);
            like.setCommentId(null);
            like.setReplyId(null);
        }else if(like.getCommentId() != 0){
            PostComment comment = commentRepository.getById(like.getCommentId());
            hasLike = comment.toggleLike(like);
            like.setPostId(null);
            like.setReplyId(null);
        }else {
            Reply reply = replyRepository.findById(like.getReplyId()).orElse(null);
            hasLike = reply.toggleLike(like);
            like.setPostId(null);
            like.setCommentId(null);
        }
        addOrDeleteLike(hasLike, like);
    }


    @Override
    public void addOrDeleteLike(Boolean found, Like like) {
        if (!found) {
            likeRepository.save(like);
        } else {
            likeRepository.delete(like);
        }

    }
}
