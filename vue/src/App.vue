<template>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

  <div id="navbar">
    <div id="logo-container">
      <button @click="goToHome" class="logo-button">
        <img class="logo" src="../img/Telogo_2.gif" alt="Home">
      </button>
      <p id="logo-title">TE-gram</p>
    </div>
    <div id="search-control">
      <span @click="search">
        <i class="fa-solid fa-magnifying-glass"></i>
      </span>
      <input type="text" id="search" maxlength="50" v-model="username" v-on:keyup.enter="search">
    </div>
    <div id="user-message">
      <p v-if="emptySearch">Please type in a valid username.</p>
      <p v-if="userNotFound">User not found.</p>
    </div>

    <div class="dropdown">
      <button class="dropbtn">
        <!-- <i class="fa fa-caret-down"></i> -->
        <i class="fa-solid fa-bars"></i>
      </button>
      <div class="dropdown-content">
        <router-link class="active-link" v-bind:to="{ name: 'home' }"><font-awesome-icon :icon="['fas', 'house-chimney']"
            pull="left" />&nbsp;Home</router-link>
        <router-link class="active-link" v-if="!loggedIn" v-bind:to="{ name: 'login' }"><font-awesome-icon
            :icon="['fas', 'arrow-right-to-bracket']" pull="left" />&nbsp;Log in
        </router-link>
        <router-link class="active-link" v-if="loggedIn"
          v-bind:to="{ name: 'favorites-page', params: { id: this.$store.state.user.id } }"><font-awesome-icon
            :icon="['fas', 'star']" pull="left" />&nbsp;Favorites</router-link>
        <router-link class="active-link" v-if="loggedIn"
          v-bind:to="{ name: 'following-page', params: { followerUserId: this.$store.state.user.id } }"><font-awesome-icon
            :icon="['fas', 'user-plus']" pull="left" />&nbsp;Following</router-link>
        <router-link class="active-link" v-if="loggedIn"
          v-bind:to="{ name: 'user', params: this.$store.state.user.id }"><font-awesome-icon :icon="['fas', 'user']"
            pull="left" />&nbsp;Edit Profile</router-link>
        <router-link class="active-link" v-if="loggedIn" v-bind:to="{ name: 'photoupload' }"><font-awesome-icon
            :icon="['fas', 'cloud-arrow-up']" pull="left" />&nbsp;Photo Upload</router-link>
        <router-link class="active-link" v-if="loggedIn" v-bind:to="{ name: 'logout' }"><font-awesome-icon
            :icon="['fas', 'arrow-right-from-bracket']" pull="left" />&nbsp;Logout</router-link>
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
  computed: {
    loggedIn() {
      return this.$store.state.token != '';
    }
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
            // this.$store.commit("SET_SEARCHED_USER", this.users);
            this.$router.push(`/users/${this.users.id}/photos`)
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
    // goToFavorites() {
    //   this.$store.commit('PAGE_FILTER', true);
    //   console.log(this.$store.state.user.id);
    //   this.$router.push({ name: 'favorites-page', params: { id: this.$store.state.user.id } });
    // }
  }
};
</script>

<style>
* {
  font-family: Roboto;
  margin: 0;
  --nav-color: #A25772;
  --font-size: 2rem;
}

#logo-container {
  display: flex;
}

#logo-title {
  color: #bcd9ff;
  font-family: Kanit;
  font-size: 2.2rem;
}

/* Navigation bar */
#navbar {
  display: flex;
  position: sticky;
  top: 0;
  z-index: 999;
  justify-content: space-between;
  align-items: center;
  background-color: var(--nav-color);
  /* overflow: hidden; */
  height: 7vh;
  font-size: var(--font-size);
  padding: 0px 3vh 0px 3vh;
  min-height: 50px;
  border: 0;
}

/* fancy Home button */
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
  /* Search control - mag glass and input */
  display: flex;
  justify-content: center;
}

/* #magGlass {
  width: 25px;
  height: 25px;
  margin-top: 4px;
} */

.fa-magnifying-glass {
  font-size: 1em;
  color: white;
}

#search {
  height: 22px;
  width: 20vw;
  margin: 6px 10px 6px 10px;
}

/* Drop down menu bar  */
.active-link {
  float: left;
  color: white;
  text-align: center;
  text-decoration: none;
  margin: 5px 10px 5px 10px;
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

.dropdown {
  float: right;
  overflow: hidden;
  font-size: 1.3rem;
  border: none;
  outline: none;
  color: white;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

#user-message {
  position: absolute;
  width: 30vw;
  top: 50px;
  left: 35vw;
  background-color: #b22;
  color: white;
  font-size: 20px;
  padding-left: 20px;
  border-radius: 5px;
}

.dropdown-content {
  right: 0;
  display: none;
  position: absolute;
  background-color: var(--nav-color);
  border-radius: 3px;
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

#lis {
  position: relative;
  /* Defaults */
  --size: 1;
  --fallDuration: 10s;
  --swayDuration: 0.8s;
  --fallSlideStrength: 0.5;
  --slideStrength: 0.5;
  --position: 0;
}

#lis>div {
  position: absolute;
  top: 0;
  left: calc(var(--position) - 20%);

  width: calc(var(--size) * 15px);
  aspect-ratio: 1;

  animation: var(--fallDuration) snowFall linear forwards;
}

@keyframes snowFall {
  to {
    top: 100%;
    transform: translateX(calc(var(--cWidth) * var(--fallSlideStrength) / 8));
  }
}
</style>