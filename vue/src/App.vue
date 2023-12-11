<template>
  <div id="capstone-app">
    <div id="nav">
      <button @click="goToHome" class="logo-button">
        <img class="logo" src="../img/Telogo_2.gif" alt="Home">
      </button>
      <div id="search-control">
        <!-- <img src="../img/magnifyingGlass.png" alt="magnifying glass" id="magGlass" @click="search"> -->
        <i class="fa-solid fa-magnifying-glass" @click="search"></i>
        <input type="text" id="search" maxlength="50" v-model="username" v-on:keyup.enter="search">
      </div>
      <div id="user-message">
        <p v-if="emptySearch">Please type in a valid username.</p>
        <p v-if="userNotFound">User not found.</p>
      </div>
      <section>
        <router-link class="active-link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link class="active-link"
          v-bind:to="{ name: 'favorites-page', params: { id: this.$store.state.user.id } }">Favorites</router-link>&nbsp;|&nbsp;
        <router-link class="active-link" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Log in
        </router-link>
        <!-- &nbsp;|&nbsp; -->
        <router-link class="active-link" v-bind:to="{ name: 'user', params: this.$store.state.user.id }"
          v-if="$store.state.token != ''">User</router-link> &nbsp;|&nbsp;
        <router-link class="active-link" v-bind:to="{ name: 'photoupload' }">Photo Upload</router-link>&nbsp;|&nbsp;
        <router-link class="active-link" v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''">Logout</router-link>
      </section>
    </div>
    <router-view />
  </div>
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
        // alert("Please type in a valid username.");
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
            // this.$router.push("/gallery");
            this.$router.push(`/users/${this.users.id}/photos`)
            this.username = "";
          }
        })
        .catch(error => {
          // alert("User not found. " + error);
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

<style scoped>
* {
  font-family: Roboto;
}

.active-link {
  text-decoration: none;
  color: #FFFFFF;
  font-weight: bold;
}

.active-link:hover {
  color: #ABB9C8;
}

body {
  background-color: #EEF5FF;
  margin: 0;
  padding: 0;
}

#nav {
  background-color: #A25772;
  height: 7vh;
  font-size: 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: azure;
  padding: 0px 3vh 0px 3vh;
  min-height: 50px;
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
</style>