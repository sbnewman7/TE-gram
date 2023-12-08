package com.techelevator.dao;

import java.util.List;

public interface FavoriteDao {

    List<Integer> getFavorites(int userId);
}
