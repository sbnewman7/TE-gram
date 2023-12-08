package com.techelevator.dao;

public interface FavoriteDao {

    boolean getUserFavorited(int userId, int photoId);

    void addFavorite(int userId, int photoId);

    void removeFavorite(int photoId, int userId);

}
