package com.techelevator.controller;

import com.techelevator.dao.FavoriteDao;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FavoritesController {

    private final FavoriteDao favoriteDao;

    public FavoritesController(FavoriteDao favoriteDao){
        this.favoriteDao = favoriteDao;
    }

    @GetMapping("/favorited/{userId}/{photoId}")
    public boolean getFavorited(@PathVariable int userId, @PathVariable int photoId ){
        return favoriteDao.getUserFavorited(userId, photoId);
    }

    @PostMapping("/favorites/{photoId}/{userId}")
    public void addFavorite(@PathVariable int userId, @PathVariable int photoId){
        favoriteDao.addFavorite(userId, photoId);
    }
    @DeleteMapping("/unfavorite/{photoId}/{userId}")
    public void removeFavorite(@PathVariable int photoId, @PathVariable int userId){
        favoriteDao.removeFavorite(photoId,userId);
    }
}
