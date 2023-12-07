import axios from 'axios';

export default {

    getLiked(userId, photoId) {
        return axios.get(`/liked/${userId}/${photoId}`);
    },
    getLikeCount(photoId) {
        return axios.get(`/likes/${photoId}`);
    },
    addLike(photoId, userId) {
        return axios.post(`/likes/${photoId}/${userId}`);
    }

}