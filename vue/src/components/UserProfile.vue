<template>
    <div>
      <span>Profile Pic: </span> 
      <img v-if="!pictureUrl" :src="user.picUrl"> <img v-else :src="pictureUrl">
      <cloudinary-upload v-model:pictureUrl="pictureUrl"/>
      <p>User Name: {{ user.username }}</p>
      <p>User Email: {{ user.email }}</p>
      <p>User Password: *********</p>
      
    </div>
  </template>
  
  <script>
    
import CloudinaryUpload from '../components/CloudinaryUpload.vue';


  export default {
    components: {
    CloudinaryUpload
  },
    props: ['user'],
    created() {
    console.log('User object:', this.user);
  },
  computed:{
    pictureUrl(){
        return this.$store.state.pictureUrl;
    }
  }
    }
  
  </script>

<style scoped>

</style>