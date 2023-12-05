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
  methods: {
    changePic() {
      this.$store.state.pictureUrl;
    }
  },
  computed: {
    pictureUrl() {
      return this.$store.state.pictureUrl;
    }
  }
}

</script>

<style scoped>
.circular--landscape {
  display: inline-block;
  position: relative;
  width: 200px;
  height: 200px;
  overflow: hidden;
  border-radius: 50%;
}

.circular--landscape img {
  width: auto;
  height: 100%;
  margin-left: -50px;
}
</style>