<template>
  <div class="home" id="lis">
    <h1>Home</h1>
    <section class="photo-list">
      <photo-card class="photo" :photo="photo" v-for="photo in photos" :key="photo.id" />
    </section>
  </div>
</template>

<script>
import PhotoCard from "../components/PhotoCard.vue"
import FavoritesGateway from "../services/FavoritesGateway";
import photoService from "../services/PhotosGateway"


export default {
  name: "Home",
  components: {
    PhotoCard
  },
  data() {
    return {
      photos: [],
      favorites: []
    }
  },
  created() {
    photoService.getAll()
      .then(response => {
        this.photos = response.data.filter((photo) => {
          return photo.private == false
            || photo.userId == this.$store.state.user.id
        });
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
  color: rgb(42, 42, 53);
  margin-top: 13px;
  margin-bottom: 13px;
  display: flex;
  justify-content: center;
}

.photo {

  margin: 16px;
}
</style>