<template>
  <div id="capstone-app">
    <div id="nav">
      <button @click="goToHome" class="logo-button">
        <img class="logo" :src="'img/Telogo_2.gif'" alt="Home">
      </button>
      <div id="searchControl">
        <img src="../img/magnifyingGlass.png" alt="magnifying glass" id="magGlass">
        <input type="text" id="search" maxlength="50" v-on:keyup.enter="search">
      </div>
      <section>
        <router-link class="active-link" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link class="active-link" v-bind:to="{ name: 'login' }" v-if="this.$store.state.token == ''">Log
          in</router-link>
        <router-link class="active-link" v-bind:to="{ name: 'logout' }"
          v-if="this.$store.state.token != ''">Logout</router-link>
      </section>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  methods: {
    search() {
      // TODO: pass <username> prop or param to gallery
      // --- OR ---
      // do a user lookup:
      // if user not found:
      //   alert("Please type in a valid username.");
      // else
      this.$router.push("/gallery");
      //  and CLEAR search textbox
      document.getElementById("search").value = "";
    },
    goToHome() {
      this.$router.push({ name: 'home' });
    },
  },
};
</script>

<style>
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
}

.logo-button {
  border: none;
  background: none;
  padding: 0;
  cursor: pointer;
}

.logo {
  height: 7vh;
}

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
</style>
