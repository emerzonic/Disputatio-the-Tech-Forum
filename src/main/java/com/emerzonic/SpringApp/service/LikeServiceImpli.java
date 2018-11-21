package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.*;
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
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private ReplyRepository replyRepository;

    public LikeServiceImpli(CommentRepository commentRepository, PostRepository postRepository, LikeRepository likeRepository, ReplyRepository replyRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    @Transactional
    public void toggleLike(Like like) {
        if (like.getPostId() != 0) {
            Post post = postRepository.findById(like.getPostId()).orElse(null);
            boolean found = post.toggleLike(like);
            like.setCommentId(null);
            like.setReplyId(null);
            addOrDeleteLike(found, like);
        }else if(like.getCommentId() !=0){
            PostComment comment = commentRepository.findById(like.getCommentId()).orElse(null);
            boolean found = comment.toggleLike(like);
            like.setPostId(null);
            like.setReplyId(null);
            addOrDeleteLike(found, like);
        }else {
            Reply reply = replyRepository.findById(like.getReplyId()).orElse(null);
            boolean found = reply.toggleLike(like);
            like.setPostId(null);
            like.setCommentId(null);
            addOrDeleteLike(found, like);

        }

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
