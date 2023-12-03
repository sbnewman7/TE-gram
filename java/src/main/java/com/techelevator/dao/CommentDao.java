package com.techelevator.dao;

import com.techelevator.model.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> getCommentsByPhotoId(int id);


}
