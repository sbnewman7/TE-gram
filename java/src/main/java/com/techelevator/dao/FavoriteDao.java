package com.techelevator.dao;

public interface FavoriteDao {

    boolean getUserFavorited(int photoId, int userId);

    void addFavorite(int photoId, int userId);

    void removeFavorite(int photoId, int userId);

}
