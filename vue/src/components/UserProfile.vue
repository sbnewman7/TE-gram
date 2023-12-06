<template>
  <div class="pic">
    <div class="currentPic">
      <button v-on:click="switchCloudinaryForm" class="edit">Edit</button>
       <span>Current Profile Pic:</span>
      <profile-picture :profilePic="user.picUrl" id="profile-picture"/>
    </div>
    <cloudinary-upload v-if="showCloudinaryForm" @uploaded="onUpload" v-model:pictureUrl="pictureUrl" />

  </div>
  <form id="form" v-on:submit.prevent="">
    <div role="alert" v-if="registrationErrors">
      {{ registrationErrorMsg }}
    </div>
    <section>
      <div class="form-input-group">
        <button v-on:click="switchUserForm" class="edit">Edit</button>
        <input v-if="showUserForm" type="text" id="username" v-model="changeUser.username" required autofocus class="input" />
        <span v-if="!showUserForm">User Name: {{ user.username }}
        </span>
      </div>
      <div class="form-input-group">
        <button v-on:click="switchEmailForm" class="edit">Edit</button>

        <input v-if="showEmailForm" type="text" id="email" v-model="changeUser.email" required autofocus class="input" />
        <span v-if="!showEmailForm">User Email: {{ user.email }}</span>

      </div>
    </section>

    <!-- button changed from type "submit" to type "button" to prevent cloudinary component from triggering submit -->
    <button v-if="showSubmit" id="submit" type="button" v-on:click.prevent="onSubmit">Submit</button>
    <button v-if="showUpdated" class="edit updated">Information Updated!</button>
    <button v-if="showError" class="edit updated error">There was an error, please try again later.</button>

  </form>
</template>
  
<script>

import CloudinaryUpload from '../components/CloudinaryUpload.vue';
import UserGateway from '../services/UserGateway';
import ProfilePicture from '../components/ProfilePicture.vue'

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
      showCloudinaryForm: false,
      showUpdated: false,
      showError: false    };
  },
  components: {
    CloudinaryUpload,
    ProfilePicture
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
      UserGateway.updateUser(this.changeUser).then(response => {
        if (response.status == 200){
          this.showUpdated = true;
          window.setTimeout(() => {
      this.showUpdated = false;
    }, 2500);
        }
      }).catch(error => {
        if(error.response||error.request){
          this.showUpdated = false;
          this.showError = true;
          window.setTimeout(() => {
      this.showError = false;
    }, 4000)
        }
      });
      this.showEmailForm = false;
      this.showUserForm = false;
      this.showSubmit = false;
      
      

    }
  }

}

</script>

<style scoped>

*{
  color: aliceblue;
  font-size: 1.5rem;
}

#profile-picture{
  height: 200px;
  width: 200px;
}

.edit{
  background-color: #7C93C3;
  border: #6d87bf 2px solid;
  border-radius: 3px;
  margin-right: 6px;
  padding: 2px 9px;
}
.input{
  color:#5e75a8;
  padding-left: 6px;
  border-radius: 5px;
  border: #6d87bf 2px solid;
}

.edit:hover{
  background-color: #91a5cd;
  border: #93a6d0 2px solid;

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

.form-input-group{
  display: flex;
  align-items: center;
  margin: 8px
}

.updated{
  border:none;
  position: absolute;
    top: 44%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    max-width: 300px;
    width: 100%;
    text-align: center;
  background-color: green;
}

.error{
  background-color: rgb(225, 42, 42);
}
</style>