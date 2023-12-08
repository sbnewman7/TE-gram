import axios from 'axios';

export default {

    getFavorited(photoId, userId) {
        return axios.get(`/favorited/${photoId}/${userId}`);
    },

    addFavorite(photoId, userId) {
        return axios.post(`/favorites/${photoId}/${userId}`);
    },

    removeFavorite(photoId, userId) {
        return axios.delete(`/unfavorite/${photoId}/${userId}`);
    }

}