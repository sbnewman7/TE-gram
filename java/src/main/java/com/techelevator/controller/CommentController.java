package com.techelevator.controller;

import com.techelevator.dao.CommentDao;
import com.techelevator.model.Comment;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class CommentController {

    private final CommentDao commentDao;

    public CommentController(CommentDao commentDao){
        this.commentDao = commentDao;
    }

    @PostMapping("/{photoId}/comments")
    public boolean addComment(@PathVariable int photoId, @RequestBody Comment comment){
        comment.setPhotoId(photoId);
        comment.setTimestamp(LocalDateTime.now());
        return commentDao.addComment(comment);
    }
    @GetMapping("/comments/{userId}/{photoId}")
    public boolean getCommented(@PathVariable int userId, @PathVariable int photoId ){
        return commentDao.getUserCommentedOnPhoto(userId, photoId);
    }

}
