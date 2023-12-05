import axios from 'axios';

export default {

    updateUser(user) {
        return axios.put(`/users/${user.id}`, user);
    }

}