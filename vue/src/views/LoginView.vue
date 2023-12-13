<template>
  <div id="login">
    <form id="form" v-on:submit.prevent="login">
      <h1 id="title">Login</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <input type="text" id="username" placeholder="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <input type="password" id="password" placeholder="password" v-model="user.password" required />
      </div>
      <button id="submit" type="submit">Submit</button>
      <hr id="bar">
      <p>
        Don't have an account?
        <router-link v-bind:to="{ name: 'register' }">Sign up.</router-link>
      </p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
a {
  color: #7393C3;
}

.form-input-group {
  margin-bottom: 1rem;
}

#login {
  display: flex;
  justify-content: center;
}

#form {
  display: flex;
  width: 40vw;
  flex-direction: column;
  align-items: center;
}

label {
  margin-right: 0.5rem;
}

#title {
  text-align: center;
  margin-bottom: 60px;
  margin-top: 20px;
}

#bar {
  width: 30vw;
  margin-top: 30px;
}

#username,
#password {
  padding-top: 5px;
  padding-bottom: 5px;
  width: 25vw;
  padding-left: 5px;
}

#username,
#password,
#submit {
  background-color: #D9D9D9;
  border: none;
  border-radius: 4px;
}

#submit {
  padding: 5px 2vw 5px 2vw;
  background-color: #7C93C3;
  border: #6d87bf 2px solid;
  border-radius: 3px;
  color: white;
}

#submit:hover {
  background-color: #91a5cd;
  border: #93a6d0 2px solid;
}
</style>