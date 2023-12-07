import axios from 'axios';

export default {

    getLiked(userId, photoId) {
        return axios.get(`/likes/${userId}/${photoId}`);
    },

}