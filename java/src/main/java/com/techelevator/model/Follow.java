package com.techelevator.model;

public class Follow {


    private int follower_user_id;

    private int followed_user_id;

    public int getFollowerUserId() {
        return follower_user_id;
    }

    public int getFollowedUserId() {
        return followed_user_id;

    }

    public void setFollowerUserId(int followerUserId) {
        this.follower_user_id = followerUserId;
    }

    public void setFollowedUserId(int followedUserId) {
        this.followed_user_id = followedUserId;
    }

}





