package com.techelevator.controller;

import com.techelevator.dao.FavoriteDao;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
public class FavoritesController {

    private final FavoriteDao favoriteDao;

    public FavoritesController(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    @GetMapping("/{userId}/favorites")
    public List<Integer> getFavorites(@PathVariable int userId) {
        return favoriteDao.getFavorites(userId);
    }

    @GetMapping("/favorited/{photoId}/{userId}")
    public boolean getFavorited(@PathVariable int photoId, @PathVariable int userId) {
        return favoriteDao.getUserFavorited(photoId, userId);
    }

    @PostMapping("/favorites/{photoId}/{userId}")
    public void addFavorite(@PathVariable int photoId, @PathVariable int userId) {
        favoriteDao.addFavorite(photoId, userId);
    }

    @DeleteMapping("/unfavorite/{photoId}/{userId}")
    public void removeFavorite(@PathVariable int photoId, @PathVariable int userId) {
        favoriteDao.removeFavorite(photoId, userId);
    }

}