<template>
  <div class="home">
    <h1>Photos</h1>
    <section class="photo-list">
      <photo-card class="photo" :photo="photo" v-for="photo in photos"
        :key="photo.id" />
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
      .then(r => {
        this.photos = r.data;
      })
  }
};
</script>

<style scoped>
.photo-list {
  display: flex;
  flex-wrap: wrap;
}

.photo {
  flex-basis: 30rem;
}
</style>