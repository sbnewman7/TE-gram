<template>
  <div class="home">
    <h1>Home</h1>
    <section class="photo-list">
      <photo-card class="photo" :photo="photo" v-for="photo in photos" :key="photo.id" />
    </section>
  </div>
</template>

<script>
import PhotoCard from "../components/PhotoCard.vue"
import photoService from "../services/PhotosGateway"


export default {
  name: "Home",
  components: {
    PhotoCard
  },
  data() {
    return {
      photos: []
    }
  },
  created() {
    photoService.getAll()
      .then(response => {
        this.photos = response.data;
      })
  }
};
</script>

<style scoped>
.photo-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

template {
  background-color: #eef5ff;
}

h1 {
  font-size: 3.5rem;
<<<<<<< HEAD
  color: rgb(55, 55, 157);
  margin-top: 13px;
  margin-bottom: 13px;
  display: flex;
  justify-content: center;
=======
  color: rgb(42, 42, 53);
margin-top: 13px;
margin-bottom: 13px;
display: flex;
justify-content: center;
>>>>>>> 0bac6efb072d69adc14908af0b2bf92b7a51ee9c
}

.photo {

  margin: 16px;
}
</style>