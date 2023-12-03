import axios from "axios";
export default {

    getCommentsByPhotoId(photoId) {
        return axios.get(`/photos/${photoId}/comments`)
    }
}