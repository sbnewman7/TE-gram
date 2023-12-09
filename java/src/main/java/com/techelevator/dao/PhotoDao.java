package com.techelevator.dao;

import com.techelevator.model.CommentDto;
import com.techelevator.model.Photo;

import java.util.List;

public interface PhotoDao {
    List<Photo> getAll();

    List<Photo> getPhotosByUserId(int userId);

    List<CommentDto> getCommentsByPhotoId(int id);

    Photo getPhotoByPhotoId(int photoId);

    int addPhoto (Photo photo);

}