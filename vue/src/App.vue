<template>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

  <div id="navbar">
    <button @click="goToHome" class="logo-button">
      <img class="logo" src="../img/Telogo_2.gif" alt="Home">
    </button>
    <div id="searchControl">
      <img src="../img/magnifyingGlass.png" alt="magnifying glass" id="magGlass" @click="search">
      <input type="text" id="search" maxlength="50" v-model="username" v-on:keyup.enter="search">
    </div>

    <div class="dropdown">
      <button class="dropbtn">
        <!-- <i class="fa fa-caret-down"></i> -->
        <i class="fa-solid fa-bars"></i>
      </button>
      <div class="dropdown-content">
        <router-link class="active-link" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link class="active-link"
          v-bind:to="{ name: 'favorites-page', params: { id: this.$store.state.user.id } }">Favorites</router-link>
        <router-link class="active-link" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Log in
        </router-link>
        <router-link class="active-link" v-bind:to="{ name: 'user', params: this.$store.state.user.id }"
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
      users: []
    };
  },
  methods: {
    search() {
      if (this.username === "") {
        alert("Please type in a valid username.");
        return;
      }
      AuthService
        .getUsersByUsername(this.username)
        .then(response => {
          if (response.status == 200) {
            this.users = response.data;

            this.$store.commit("SET_SEARCHED_USER", this.users);

            // this.$router.push("/gallery");
            this.$router.push(`/users/${this.users.id}/photos`)
            this.username = "";

          }
        })
        .catch(error => {
          alert("User not found. " + error);
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

<style scoped>
* {
  font-family: Roboto;
  --nav-color: #A25772;
  --font-size: 2rem;
}

/* Navigation bar */
#navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: var(--nav-color);
  overflow: hidden;
  height: 7vh;
  font-size: var(--font-size);
  padding: 0px 3vh 0px 3vh;
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
  margin-top: 4px;
}

/* Search control - mag glass and input */
#searchControl {
  display: flex;
  justify-content: center;
}

#magGlass {
  width: 25px;
  height: 25px;
  margin-top: 4px;
}

#search {
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

.dropdown {
  font-size: 1.3rem;
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
</style>