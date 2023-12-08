package com.techelevator.dao;

import com.techelevator.model.Comment;

public interface CommentDao {

    boolean addComment(Comment comment);

    boolean getUserCommentedOnPhoto(int userId, int photoId);

}
