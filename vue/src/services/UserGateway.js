import axios from 'axios';

export default {

    updateUser(user) {
        return axios.put(`/users/${user.id}`, user);
    },

    getUserById(userId) {
        return axios.get(`/user/${userId}`);
    },

    getUser(username) {
        return axios.get(`/user/${username}`);
    }

}