package com.techelevator.dao;

import java.util.List;

public interface FavoriteDao {

    List<Integer> getFavorites(int userId);

    boolean getUserFavorited(int photoId, int userId);

    void addFavorite(int photoId, int userId);

    void removeFavorite(int photoId, int userId);

}
