import { createStore as _createStore } from 'vuex';
import axios from 'axios';
const NOTIFICATION_TIMEOUT = 6000;


export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      pictureUrl: '',
      notification: null,

      searchedUser: {}

    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_PIC_URL(state, picUrl) {
        state.pictureUrl = picUrl;
      },
      UPDATE_USER(state, user) {
        state.user = user;

      },
      SET_SEARCHED_USER(state, user) {
        state.searchedUser = user;
      }


    },
    getters: {
      currentUser: state => state.user
    }
  });
  return store;
}
