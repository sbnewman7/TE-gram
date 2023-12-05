<template>
  <div>
    <span>Profile Pic: </span>
    <div class="circular--landscape">
      <img v-if="pictureUrl" :src="pictureUrl">
    </div>
    <div class="circular--landscape">
      <img v-if="!pictureUrl" :src="user.picUrl">
    </div>

    <cloudinary-upload v-model:pictureUrl="pictureUrl" />

  </div>
  <form id="form" v-on:submit.prevent="">
    <div role="alert" v-if="registrationErrors">
      {{ registrationErrorMsg }}
    </div>
    <div class="form-input-group">
      <img v-on:click="switchUserForm" class="icon"
        :src="'https://cdn1.iconfinder.com/data/icons/essential-21/128/Edit-512.png'">
      <input v-if="showUserForm" type="text" id="username" v-model="changeUser.username" required autofocus />
      <span v-if="!showUserForm">User Name: {{ user.username }}
      </span>
    </div>
    <div class="form-input-group">
      <img v-on:click="switchEmailForm" class="icon"
        :src="'https://cdn1.iconfinder.com/data/icons/essential-21/128/Edit-512.png'">

      <input v-if="showEmailForm" type="text" id="email" v-model="changeUser.email" required autofocus />
      <span v-if="!showEmailForm">User Email: {{ user.email }}</span>

    </div>
    <!-- button changed from type "submit" to type "button" to prevent cloudinary component from triggering submit -->
    <button v-if="showSubmit" id="submit" type="button" v-on:click.prevent="onSubmit">Submit</button>

  </form>
</template>
  
<script>

import CloudinaryUpload from '../components/CloudinaryUpload.vue';
import UserGateway from '../services/UserGateway';

export default {
  data() {
    return {
      changeUser: {
        username: this.$store.state.user.username,
        email: this.$store.state.user.email,
        password: this.$store.state.user.password,
        confirmPassword: this.$store.state.user.confirmPassword,
        picUrl: this.$store.state.user.picUrl,
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      showUserForm: false,
      showEmailForm: false,
      showSubmit: false
    };
  },
  components: {
    CloudinaryUpload
  },
  props: ['user'],
  created() {
    console.log('User object:', this.user);
  },

  methods: {
    updateProfile() {

    },
    switchUserForm() {
      this.showUserForm = !this.showUserForm;
      if (this.showUserForm) this.showSubmit = true;
      else this.showSubmit = false;
    },
    switchEmailForm() {
      this.showEmailForm = !this.showEmailForm;
      if (this.showEmailForm) this.showSubmit = true;
      else this.showSubmit = false;
    },
    onSubmit() {
      this.$store.commit('UPDATE_USER', this.changeUser);
      UserGateway.updateUser(this.changeUser);
      this.showEmailForm = false;
      this.showUserForm = false;
    }
  },
  computed: {
    pictureUrl() {
      return this.$store.state.pictureUrl;
    },
  }
}

</script>

<style scoped>
.circular--landscape {
  display: inline-block;
  justify-content: center;
  width: 200px;
  height: 200px;
  overflow: hidden;
  border-radius: 50%;
  background-color: black;
}

.icon {
  max-width: 20px;
}

.circular--landscape img {
  width: auto;
  display: flex;
  height: 100%;
  margin-left: -50px;
}
</style>