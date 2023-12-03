package com.techelevator.controller;

import com.techelevator.dao.CommentDao;
import com.techelevator.model.Comment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CommentsController {

    private final CommentDao commentDao;

    public CommentsController(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @GetMapping("/photos/{id}/comments")
    public List<Comment> getPhotos(@PathVariable int id) {
        return commentDao.getCommentsByPhotoId(id);
    }

}