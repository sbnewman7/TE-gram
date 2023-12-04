<template>
  <div id="register" class="text-center">
    <form id="form" v-on:submit.prevent="">
      <!-- register -->
      <h1 id="title">Register</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <input type="text" id="username" placeholder="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <input type="text" id="email" placeholder="email" v-model="user.email" required autofocus />
      </div>
      <div class="form-input-group">
        <input type="password" id="password" placeholder="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <input type="password" id="confirmPassword" placeholder="confirm password" v-model="user.confirmPassword"
          required />
      </div>
      <cloudinary-upload />
      <!-- <button id="submit" type="submit">Submit</button> -->
      <button id="submit" type="button" v-on:click="register">Submit</button>
      <hr id="bar">
      <p>
        Already have an account?
        <router-link v-bind:to="{ name: 'login' }">Log in.</router-link>
      </p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';
import CloudinaryUpload from '../components/CloudinaryUpload.vue';

export default {
  components: {
    CloudinaryUpload
  },
  data() {
    return {
      user: {
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        picUrl: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  computed: {
    pictureUrl() {
      return this.$store.state.pictureUrl;
    }
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        // getting picture url
        console.log('inside register view--calling computed property');
        this.user.picUrl = this.pictureUrl;
        console.log(this.user.picUrl);
        // if (this.user.picUrl != '') {
        //   this.$store.commit('SET_PIC_URL', '');
        // }

        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

a {
  color: #7393C3;
}



.form-input-group {
  margin-bottom: 1rem;
}

#register {
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
  margin-bottom: 40px;
}

#bar {
  width: 30vw;
  margin-top: 30px;
}

#username,
#email,
#password,
#confirmPassword {
  padding-top: 5px;
  padding-bottom: 5px;
  width: 25vw;
  padding-left: 5px;
}

#username,
#email,
#password,
#confirmPassword,
#submit {
  background-color: #D9D9D9;
  border: none;
  border-radius: 4px;

}

#submit {
  padding: 5px 2vw 5px 2vw;

}
</style>
