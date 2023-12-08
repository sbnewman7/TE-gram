package com.techelevator.controller;

import com.techelevator.dao.FavoriteDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class FavoritesController {

    private final FavoriteDao favoriteDao;

    public FavoritesController(FavoriteDao favoriteDao){
        this.favoriteDao = favoriteDao;
    }

    @GetMapping("/{userId}/favorites")
    public List<Integer> getFavorites(@PathVariable int userId){
        return favoriteDao.getFavorites(userId);
    }
}
