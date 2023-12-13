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
        <p v-html="converted(comment.commentBody)"></p>
        
        <p>{{ formatDateTime(comment.timestamp) }} - {{ comment.username }}</p>

      </div>
    </section>
  </div>
  <form class="comment-form" v-on:submit.prevent="addComment">
    <textarea class="comment" v-model="newComment.commentBody" rows="4" cols="50" placeholder="Add a comment"></textarea>
    <br>
    <button id="submit" type="submit">Submit</button>
    <button v-if="showError" class="edit updated error">Limit one comment per photo.</button>
  </form>
</template>

<script>
import PhotosGateway from "../services/PhotosGateway";
import UserGateway from "../services/UserGateway";
import LikesGateway from '../services/LikesGateway';
import CommentGateway from "../services/CommentGateway";
import FavoritesGateway from "../services/FavoritesGateway";
import markdownit from 'markdown-it'

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
      commentUser: "",
      newComment: {
        commentBody: '',
        userId: this.$store.state.user.id,
      },
      showError: false,
      rendered: ''
    }
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
    addComment() {
      CommentGateway.getUserCommented(this.$store.state.user.id, this.photo.id)
        .then(response => {
          if (!response.data) {
            CommentGateway.postComment(this.photo.id, this.newComment);
            this.newComment.timestamp = new Date();
            UserGateway
              .getUserById(this.$store.state.user.id)
              .then((response) => {
                this.newComment.username = response.data.username;
              })
            this.photo.comments.unshift(this.newComment);
          }
          else {
            this.showError = true;
            window.setTimeout(() => {
              this.showError = false;
            }, 3000)
          }
        })
    },
    clearForm() {
      this.newComment.commentBody = '';
    },

    // this method caused a bug where the username was flickering and changing
    // I think b/c it was doing an async db call repeatedly within a for loop.
    // I solved it by creating a dto that included username.
    // getUserById(userId) {
    //   UserGateway
    //     .getUserById(userId)
    //     .then((response) => {
    //       this.commentUser = response.data.username;
    //     })
    //   return this.commentUser;
    // },

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
    },
    converted(commentBody) {
      const md = markdownit();
      this.rendered = md.render(commentBody);
      console.log(this.rendered);
      return this.rendered;
    }

  },
  computed: {
    // converted(commentBody) {
    //   const md = markdownit();
    //   this.rendered = md.render(commentBody);
    //   return this.rendered;
    // }
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

.comment-form {
  display: flex;
  margin-left: 30px;
  align-content: space-between;

}

.comment {
  font-size: 16px;
  background-color: #9EB8D9;
  color: white;
  margin-right: 40px;
  width: 80vw;
  font-size: 18px;
  border: #7c93c3 3px solid;

}

#submit {
  margin-top: 20px;
  font-size: 14px;
  max-height: 40px;

}


::placeholder {
  color: white;
  font-size: 18px;
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

.edit {
  background-color: #7C93C3;
  border: #6d87bf 2px solid;
  border-radius: 3px;
  margin-right: 6px;
  padding: 2px 9px;
  font-size: 1.5rem;
}

.updated {
  border: none;
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

.error {
  background-color: rgb(225, 42, 42);
}

button {
  border: #7C93C3 3px solid;
  border-radius: 4px;
  padding: 5px 2vw 5px 2vw;
  background-color: #9EB8D9;
  margin-top: 10px;
}
</style>