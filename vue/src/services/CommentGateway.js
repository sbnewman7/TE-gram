import axios from 'axios';

export default {
    addComment(photoId, comment) {
        return axios.post(`/${photoId}/comments`, comment);
    },
    getUserCommented(userId, photoId) {
        return axios.get(`/comments/${userId}/${photoId}`);
    }

}