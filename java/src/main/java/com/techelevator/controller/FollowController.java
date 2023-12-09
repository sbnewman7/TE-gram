package com.techelevator.controller;


import com.techelevator.dao.FollowDao;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.attribute.FileAttributeView;
import java.util.List;

@RestController
@CrossOrigin
public class FollowController {

    private final FollowDao followDao;

    public FollowController(FollowDao followDao) {
        this.followDao = followDao;

    }

    @GetMapping("/following/{followerUserId}")
    public List<Integer> getFollowed(@PathVariable int followerUserId) {
        return followDao.getFollowed(followerUserId);
    }
    @GetMapping("/follows/{followerUserId}/{followedUserId}")
    public boolean getFollowed(@PathVariable int followerUserId, @PathVariable int followedUserId){
        return followDao.getUserFollowed(followerUserId, followedUserId);
    }
    @PostMapping("/follow/{followerUserId}/{followedUserId}")
    public void addFollow(@PathVariable int followerUserId, @PathVariable int followedUserId){
        followDao.addFollower(followerUserId, followedUserId);
    }
    @DeleteMapping("/unfollow/{followerUserId}/{followedUserId}")
    public void removeFollower(@PathVariable int followerUserId, @PathVariable int followedUserId){
        followDao.removeFollower(followerUserId, followedUserId);
    }



}
