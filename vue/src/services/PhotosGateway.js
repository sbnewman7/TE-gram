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
    },
    getPhotoByPhotoId(photoId) {
        return axios.get(`/photos/${photoId}`);
    },
    deletePhoto(photoId) {
        return axios.delete(`/photo/${photoId}`);
    }
}