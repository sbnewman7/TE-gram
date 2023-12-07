package com.techelevator.controller;

import com.techelevator.dao.LikeDao;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LikesController {

    private final LikeDao likeDao;

    public LikesController(LikeDao likeDao){
        this.likeDao = likeDao;
    }

    @GetMapping("/liked/{userId}/{photoId}")
    public boolean getLiked(@PathVariable int userId, @PathVariable int photoId ){
        return likeDao.getUserLiked(userId, photoId);
    }
    @GetMapping("likes/{photoId}")
    public int getLikeCount(@PathVariable int photoId){
        return likeDao.getLikeCount(photoId);
    }
    @PostMapping("/likes/{userId}/{photoId}")
    public void addLike(@PathVariable int userId, @PathVariable int photoId){
        likeDao.addLike(userId, photoId);
    }
    @DeleteMapping("/unlike/{photoId}/{userId}")
    public void removeLike(@PathVariable int photoId, @PathVariable int userId){
        likeDao.removeLike(photoId,userId);
    }
}
