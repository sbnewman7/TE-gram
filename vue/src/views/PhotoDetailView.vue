<template>
  <div class="container">
    <section id="right-half">
      <div class="photo-container">
        <img class="photo" :src="photo.photoUrl" />
      </div>
      <h1>{{ photo.caption }}</h1>
    </section>
    <section id="left-half">
      <div id="comment" v-for="comment in photo.comments" :key="comment.id">
        <h3>{{ comment.commentBody }}</h3>
        <p>{{ formatDateTime(comment.timestamp) }} - {{ getUserById(comment.userId) }}</p>

      </div>
    </section>
  </div>
</template>

<script>
import PhotosGateway from "../services/PhotosGateway";
import UserGateway from "../services/UserGateway";

export default {
  data() {
    return {
      photo: {},
      comment: {},
      user: ""
    };
  },
  methods: {
    getUserById(userId) {
      UserGateway
        .getUserById(userId)
        .then((response) => {
          this.user = response.data.username;
        })
      return this.user;
    },

    formatDateTime(dateTimeString) {
      const date = new Date(dateTimeString);

      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
      const year = date.getFullYear().toString();

      let hours = date.getHours();
      const ampm = hours >= 12 ? 'pm' : 'am';
      hours = hours % 12;
      hours = hours ? hours : 12; // Convert 0 to 12
      const minutes = date.getMinutes().toString().padStart(2, '0');

      const formattedDateTime = `${month}-${day}-${year} ${hours}:${minutes} ${ampm}`;
      return formattedDateTime;
    }

  },
  created() {
    PhotosGateway
      .getPhotoByPhotoId(this.$route.params.id)
      .then((response) => {
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

#comment {
  background-color: #9EB8D9;
  width: 37vw;
  margin: 7px;
}

#comment>h3 {
  color: white;
  margin: 0;
  padding: 10px 0 8px 15px;
}

#comment>p {
  color: rgb(55, 55, 157);
  font-size: 12px;
  font-weight: bold;
  margin: 0;
  padding: 0 0 5px 15px;
}

.photo {
  width: 100%;
  height: 100%;
  object-fit: cover;
  overflow: hidden;
}
</style>
