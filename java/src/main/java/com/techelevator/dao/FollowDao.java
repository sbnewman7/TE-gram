package com.techelevator.dao;

import java.util.List;

public interface FollowDao {


    List<Integer> getFollowers(int followerUserId);

    boolean getUserFollowed(int followerUserId, int followedUserId);

    void addFollower(int followerUserId, int followedUserId);

    void removeFollower(int followerUserId, int followedUserId);
}
