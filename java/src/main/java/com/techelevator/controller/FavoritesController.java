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

    @GetMapping("/favorited/{photoId}/{userId}")
    public boolean getFavorited(@PathVariable int photoId, @PathVariable int userId ){
        return favoriteDao.getUserFavorited(photoId, userId);
    }

    @PostMapping("/favorites/{photoId}/{userId}")
    public void addFavorite(@PathVariable int photoId, @PathVariable int userId){
        favoriteDao.addFavorite(photoId, userId);
    }
    @DeleteMapping("/unfavorite/{photoId}/{userId}")
    public void removeFavorite(@PathVariable int photoId, @PathVariable int userId){
        favoriteDao.removeFavorite(photoId, userId);
    }
}
