package com.techelevator.dao;

import com.techelevator.model.Comment;
import com.techelevator.model.Photo;

import java.util.List;

public interface PhotoDao {
    List<Photo> getAll();

    List<Photo> getPhotosByUserId(int userId);

    List<Comment> getCommentsByPhotoId(int id);

    Photo getPhotoByPhotoId(int photoId);

    int addPhoto (Photo photo);

}