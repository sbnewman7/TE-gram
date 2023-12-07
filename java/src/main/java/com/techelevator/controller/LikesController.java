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

    @GetMapping("/likes/{userId}/{photoId}")
    public boolean getLiked(@PathVariable int userId, @PathVariable int photoId ){
        return likeDao.getUserLiked(userId, photoId);
    }
}
