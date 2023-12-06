<template>
  <div class="pic">
    <div class="currentPic">
      <img v-on:click="switchCloudinaryForm" class="icon"
        :src="'https://cdn1.iconfinder.com/data/icons/essential-21/128/Edit-512.png'">
      <span>Current Profile Pic:</span>
      <div v-if="pictureUrl" class="circular--landscape">
        <img :src="pictureUrl">
      </div>
      <div v-if="!pictureUrl" class="circular--landscape">
        <img :src="user.picUrl">
      </div>
    </div>
    <cloudinary-upload v-if="showCloudinaryForm" @uploaded="onUpload" v-model:pictureUrl="pictureUrl" />

  </div>
  <form id="form" v-on:submit.prevent="">
    <div role="alert" v-if="registrationErrors">
      {{ registrationErrorMsg }}
    </div>
    <section>
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
    </section>

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
        // password: this.$store.state.user.password,
        // confirmPassword: this.$store.state.user.confirmPassword,
        picUrl: this.$store.state.user.picUrl,
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      showUserForm: false,
      showEmailForm: false,
      showSubmit: false,
      showCloudinaryForm: false
    };
  },
  components: {
    CloudinaryUpload
  },
  props: ['user'],
  created() {
    this.$store.commit('SET_PIC_URL', null);
    console.log(this.user.id);
    console.log('User object:', this.changeUser);
  },

  methods: {
    onUpload(url) {
      this.changeUser.picUrl = url;
      alert(url);
    },
    switchUserForm() {
      this.showUserForm = !this.showUserForm;
      this.switchShowSubmit(this.showUserForm, this.showEmailForm, this.showCloudinaryForm)
    },
    switchEmailForm() {
      this.showEmailForm = !this.showEmailForm;
      this.switchShowSubmit(this.showEmailForm, this.showCloudinaryForm, this.showUserForm)
    },
    switchCloudinaryForm() {
      this.showCloudinaryForm = !this.showCloudinaryForm;
      this.switchShowSubmit(this.showCloudinaryForm, this.showEmailForm, this.showUserForm)
    },
    switchShowSubmit(boo1, boo2, boo3) {
      if (boo1) this.showSubmit = true;
      else if (!boo2 && !boo3) this.showSubmit = false;
    },
    onSubmit() {
      this.$store.commit('UPDATE_USER', this.changeUser);
      console.log(this.changeUser);
      this.changeUser.picUrl = this.pictureUrl || this.changeUser.picUrl;
      this.changeUser.id = this.user.id;
      UserGateway.updateUser(this.changeUser);
      this.showEmailForm = false;
      this.showUserForm = false;

    }
  }

}

</script>

<style scoped>
.circular--landscape {
  display: inline-block;
  justify-content: center;
  width: 150px;
  height: 150px;
  overflow: hidden;
  border-radius: 50%;
  background-color: black;
  border: #7C93C3 2px solid;
  margin-left: 10px;
}

.icon {
  max-width: 20px;
  max-height: 20px;

}

.pic {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #9EB8D9;
  border-radius: 5px;
  border: #7C93C3 3px solid;
}

section {
  background-color: #9EB8D9;
  border-radius: 5px;
  border: #7C93C3 3px solid;
  margin-top: 5px;
  padding: 10px;

}

.currentPic {
  display: flex;
  align-items: center;
  padding: 10px;
}

.circular--landscape img {
  width: auto;
  display: flex;
  height: 100%;
}

#submit {
  border: #7C93C3 3px solid;
  border-radius: 4px;
  padding: 5px 2vw 5px 2vw;
  background-color: #9EB8D9;
  margin-top: 10px;
}

#submit:hover {
  background-color: #b8d0f0;

}
</style>