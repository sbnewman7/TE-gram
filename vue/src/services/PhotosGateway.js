import axios from 'axios';

export default {

    getAll() {
        return axios.get("/photos");
    },

    addPhoto(photo) {
        return axios.post("/photos", photo);
    }
}