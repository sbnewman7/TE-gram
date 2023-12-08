<template>
  <div class="container">
    <section id="right-half">
      <div class="photo-container">
        <img class="photo" :src="photo.photoUrl" />
      </div>

      <div class="like-caption">
        <img v-if="liked" v-on:click="unlike" class="heart"
          src="https://upload.wikimedia.org/wikipedia/commons/3/35/Red-simple-heart-symbol-only.png" alt="">
        <img v-if="!liked" v-on:click="like" class="heart-dark"
          src="https://upload.wikimedia.org/wikipedia/commons/3/35/Red-simple-heart-symbol-only.png" alt="">
        <p class="likeCount">{{ likeCount }}</p>

        <img v-if="favorited" v-on:click="unfavorite" class="star"
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Orange_star.svg/300px-Orange_star.svg.png"
          alt="star">
        <img v-if="!favorited" v-on:click="favorite" class="star-dark"
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Orange_star.svg/300px-Orange_star.svg.png"
          alt="star">

        <h1>{{ photo.caption }}</h1>
      </div>

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
import LikesGateway from '../services/LikesGateway';
import FavoritesGateway from "../services/FavoritesGateway";

export default {
  data() {
    return {
      photo: {
        id: 0
      },

      liked: false,
      likeCount: 0,
      favorited: false,

      comment: {},
      commentUser: ""
    };
  },
  methods: {
    like() {
      if (this.$store.state.token !== '') {
        this.liked = true;
        this.likeCount++;
        LikesGateway.addLike(this.photo.id, this.$store.state.user.id)
      }
    },
    unlike() {
      if (this.$store.state.token !== '') {
        this.liked = false;
        this.likeCount--;
        LikesGateway.removeLike(this.photo.id, this.$store.state.user.id);
      }
    },
    favorite() {
      if (this.$store.state.token !== '') {
        this.favorited = true;
        FavoritesGateway.addFavorite(this.photo.id, this.$store.state.user.id);
      }
    },
    unfavorite() {
      if (this.$store.state.token !== '') {
        this.favorited = false;
        FavoritesGateway.removeFavorite(this.photo.id, this.$store.state.user.id);
      }
    },

    getUserById(userId) {
      UserGateway
        .getUserById(userId)
        .then((response) => {
          this.commentUser = response.data.username;
        })
      return this.commentUser;
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
    this.photo.id = this.$route.params.id;
    PhotosGateway
      .getPhotoByPhotoId(this.photo.id)
      .then((response) => {
        this.photo = response.data;
      });

    if (this.$store.state.token !== '') {
      LikesGateway
        .getLiked(this.$store.state.user.id, this.photo.id)
        .then((response) => {
          if (response.data)
            this.liked = true;
        });
      LikesGateway
        .getLikeCount(this.photo.id)
        .then((response) => {
          if (response.data)
            this.likeCount = response.data;
        });
      FavoritesGateway
        .getFavorited(this.photo.id, this.$store.state.user.id)
        .then((response) => {
          if (response.data)
            this.favorited = true;
        });
    }

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

.like-caption {
  display: flex;
  font-weight: bold;
  /* justify-content: star; */
}

.like-caption>h1 {
  font-size: 25px;
  margin-left: 12px;
}

.heart {
  max-width: 50px;
  border: none;
}

.heart-dark {
  max-width: 50px;
  opacity: 30%;
  border: none;
}

.likeCount {
  padding-top: 5px;
  align-content: center;
}

.like-caption {
  display: flex;
  align-items: center;
}

.star {
  max-width: 43px;
  border: none;
  margin-left: 10px;
}

.star-dark {
  max-width: 43px;
  opacity: 30%;
  border: none;
  margin-left: 10px;
}
</style>