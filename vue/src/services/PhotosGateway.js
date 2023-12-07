import axios from 'axios';

export default {

    getAll() {
        return axios.get("/photos");
    },

    getPhotosByUserId(userId) {
        return axios.get(`/users/${userId}/photos`);
    },

    addPhoto(photo) {
        return axios.post("/photos", photo);
    }
}