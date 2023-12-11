<template>
  <div id="navbar">
    <button @click="goToHome" class="logo-button">
      <img class="logo" src="../img/Telogo_2.gif" alt="Home">
    </button>
    <div id="search-control">
      <button @click="search" class="search-button">
        <i class="fa-solid fa-magnifying-glass"></i>
      </button>
      <input type="text" id="search" maxlength="50" v-model="username" v-on:keyup.enter="search">
    </div>
    <div id="user-message">
      <p v-if="emptySearch">Please type in a valid username.</p>
      <p v-if="userNotFound">User not found.</p>
    </div>

    <div class="dropdown">
      <button class="dropbtn">
        <i class="fa-solid fa-bars"></i>
      </button>
      <div class="dropdown-content">
        <router-link class="active-link" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link class="active-link"
          v-bind:to="{ name: 'favorites-page', params: { id: $store.state.user.id } }">Favorites</router-link>
        <router-link class="active-link"
          v-bind:to="{ name: 'following-page', params: { followerUserId: $store.state.user.id } }">Following</router-link>
        <router-link class="active-link" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Log
          in</router-link>
        <router-link class="active-link" v-bind:to="{ name: 'user', params: { id: $store.state.user.id } }"
          v-if="$store.state.token != ''">User</router-link>
        <router-link class="active-link" v-bind:to="{ name: 'photoupload' }">Photo Upload</router-link>
        <router-link class="active-link" v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>
  </div>
  <router-view />
</template>

<script>
import AuthService from "./services/AuthService";

export default {
  data() {
    return {
      username: "",
      users: [],
      emptySearch: false,
      userNotFound: false
    };
  },
  methods: {
    search() {
      if (this.username === "") {
        this.emptySearch = true;
        window.setTimeout(() => {
          this.emptySearch = false;
        }, 2000);
        return;
      }
      AuthService
        .getUsersByUsername(this.username)
        .then(response => {
          if (response.status == 200) {
            this.users = response.data;
            this.$store.commit("SET_SEARCHED_USER", this.users);
            this.$router.push(`/users/${this.users.id}/photos`);
            this.username = "";
          }
        })
        .catch(error => {
          this.userNotFound = true;
          window.setTimeout(() => {
            this.userNotFound = false;
          }, 2000);
          this.username = "";
        });
    },

    goToHome() {
      this.$router.push({ name: 'home' });
    },
  }
};
</script>

<style scoped>
* {
  font-family: Roboto;
  --nav-color: #A25772;
  --font-size: 2rem;
}

#navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: var(--nav-color);
  overflow: hidden;
  height: 7vh;
  font-size: var(--font-size);
  padding: 0px 3vh 0px 3vh;
}

.logo-button {
  border: none;
  background: none;
  padding: 0;
  cursor: pointer;
}

.logo {
  height: 7vh;
  min-height: 40px;
  margin-top: 4px;
}

#search-control {
  display: flex;
  justify-content: center;
}

.search-button {
  border: none;
  background: none;
  cursor: pointer;
}

.fa-magnifying-glass {
  font-size: 1em;
  color: white;
}

#search {
  height: 22px;
  width: 20vw;
  margin: 6px 10px 6px 10px;
}

#user-message {
  position: absolute;
  width: 30vw;
  top: 50px;
  left: 35vw;
  background-color: #4f6;
  color: white;
  font-size: 20px;
  padding-left: 20px;
  border-radius: 5px;
}

.dropdown {
  float: right;
  overflow: hidden;
}

.dropbtn {
  font-size: var(--font-size);
  border: none;
  outline: none;
  color: white;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.dropdown-content {
  right: 0;
  display: none;
  position: absolute;
  background-color: var(--nav-color);
  min-width: 80px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.4);
  z-index: 1;
}

.dropdown-content a {
  float: none;
  color: white;
  text-decoration: none;
  display: block;
  text-align: right;
}

.dropdown-content a:hover {
  color: #ABB9C8;
}

.dropdown:hover .dropdown-content {
  display: block;
}

/* ... (your existing styles) ... */</style>
