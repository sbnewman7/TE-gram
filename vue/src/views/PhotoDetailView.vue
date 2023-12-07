<template>
  <div class="container">
    <section id="right-half">
      <div class="photo-container">
        <img class="photo" :src="photo.photoUrl" />
      </div>
      <h1>{{ photo.caption }}</h1>
    </section>
    <section id="left-half">
      <h3 v-for="comment in photo.comments" :key="comment.id">
        {{ comment.commentBody }}
      </h3>
    </section>
  </div>
</template>

<script>
import PhotosGateway from "../services/PhotosGateway";

export default {
  data() {
    return {
      photo: {},
    };
  },
  created() {
    PhotosGateway.getPhotoByPhotoId(this.$route.params.id).then((response) => {
      this.photo = response.data;
    });
  },
};
</script>

<style scoped>
section {
  margin: 15px;
}

.photo-container {
  height: 50vh;
  width: 50vw;
  border: #7c93c3 15px solid;
  border-radius: 5px;
}
.container {
  display: flex;
  margin: 15px;
}

h3{
    background-color: #9EB8D9;
    padding: 15px;
    width: 37vw;
    margin: 7px;
}
.photo {
  width: 100%;
  height: 100%;
  object-fit: cover;
  overflow: hidden;
}

</style>
