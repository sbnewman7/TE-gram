import axios from "axios";

export default {

    getFollowed(followerUserId) {
        return axios.get(`/following/${followerUserId}`);
    },

    getUserFollowed(followerUserId, followedUserId) {
        return axios.get(`/follows/${followerUserId}/${followedUserId}`);
    },

    addFollower(followerUserId, followedUserId) {
        return axios.post(`/follow/${followerUserId}/${followedUserId}`);
    },

    removeFollower(followerUserId, followedUserId) {
        return axios.delete(`/unfollow/${followerUserId}/${followedUserId}`);
    }

}