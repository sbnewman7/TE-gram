package com.techelevator.dao;

public interface LikeDao {

    boolean getUserLiked(int userId, int photoId);

    int getLikeCount(int photoId);

    void addLike(int userId, int photoId);

}
