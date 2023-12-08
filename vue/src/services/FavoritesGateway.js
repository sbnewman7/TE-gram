import axios from 'axios';

export default {

    getFavorited(userId, photoId) {
        return axios.get(`/favorited/${userId}/${photoId}`);
    },


    addFavorite(photoId, userId) {
        return axios.post(`/favorites/${photoId}/${userId}`);
    },
    removeFavorite(photoId, userId) {
        return axios.delete(`/unfavorite/${photoId}/${userId}`);
    }

}