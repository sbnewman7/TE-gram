import axios from 'axios';

export default {

    getFavorites(userId) {
        return axios.get(`/${userId}/favorites`);
    },
}