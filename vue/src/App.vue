<template>
  <div id="capstone-app">
    <div id="nav">
      <img class="logo" :src="'https://www.diabetes.ie/wp-content/uploads/2021/05/logo-Placeholder.jpg'" v-on:click="">
      <div id="searchControl">
        <label for="search">search</label>
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
      alert("i'm in search");
    }
  }
}
</script>

<style>
* {
  font-family: Roboto;
}

.active-link {
  text-decoration: none;
  color: #ffffff;
  font-weight: bold;
}

.active-link:hover {
  color: #abb9c8;
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

.logo {
  height: 5vh;
}

#searchControl {
  display: flex;
}

#search {
  width: 20vw;
  margin: 6px 10px 6px 10px;
}
</style>
