<template>
    <article>
        <div class="photo-container">
            <img :src="photo.photoUrl" @click="goToDetails" class="photo">
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
                alt="dark star">


            <h2>{{ photo.caption }}</h2>
        </div>
        <div v-if="hasComments">
            <span v-html="renderMarkdown(photo.comments[0].commentBody)">

            </span>
        </div>
    </article>
</template>

<script>
import FavoritesGateway from '../services/FavoritesGateway';
import LikesGateway from '../services/LikesGateway';
import markdownit from 'markdown-it'

export default {
    data() {
        return {
            liked: false,
            likeCount: 0,
            favorited: false
        }
    },
    created() {
        if (this.$store.state.token !== '') {
            LikesGateway.getLiked(this.$store.state.user.id, this.photo.id)
                .then((response) => {
                    if (response.data) this.liked = true;
                });
            LikesGateway.getLikeCount(this.photo.id)
                .then((response) => {
                    if (response.data)
                        this.likeCount = response.data;
                })
            FavoritesGateway.getFavorited(this.photo.id, this.$store.state.user.id)
                .then((response) => {
                    if (response.data) this.favorited = true;
                })
        }
    },
    props: ['photo'],
    computed: {
        hasComments() {
            return this.photo.comments && this.photo.comments.length != 0;
        }
    },
    methods: {
        goToDetails() {
            this.$router.push('/photos/' + this.photo.id)
        },
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
                LikesGateway.removeLike(this.photo.id, this.$store.state.user.id)
            }
        },
        favorite() {
            if (this.$store.state.token !== '') {
                this.favorited = true;
                FavoritesGateway.addFavorite(this.photo.id, this.$store.state.user.id)
            }
        },
        unfavorite() {
            if (this.$store.state.token !== '') {
                this.favorited = false;
                FavoritesGateway.removeFavorite(this.photo.id, this.$store.state.user.id)
            }
        },
        renderMarkdown(commentBody) {
            const md = markdownit();
            return md.render(commentBody);
        }
    }
}

</script>

<style scoped>
article {
    max-width: 400px;
    display: flex;
    background-color: #9eb8d9;
    flex-direction: column;
    align-items: center;
    padding: 10px, 0px, 10px, 0px;
    border: #7C93C3 5px solid;
    border-radius: 5px;
    color: rgb(255, 255, 255);
}

div {
    display: flex;
    justify-content: center;
}


.heart {
    max-width: 50px;
    border: none;
    margin-right: 0;
}

.heart-dark {
    max-width: 50px;
    opacity: 30%;
    border: none;
    margin-right: 0;
}

.likeCount {
    padding: 5px 5px 5px 0;

    align-content: center;

}

.like-caption {
    display: flex;
    align-items: center;
    width: 350px;
}

.star {
    max-width: 43px;
    border: none;
    margin-right: 5px;
}

.star-dark {
    max-width: 43px;
    opacity: 30%;
    border: none;
    margin-right: 5px;

}

img {
    width: 300px;
    margin: .75vw;
    border: #c3d1e4 5px solid;
    border-radius: 5px;
}

h2 {
    width: 30vh;
    word-wrap: break-word;
    padding-right: 15px;
}

span {
    display: block;
    font-size: 1.1rem;
    width: 30vh;
    box-sizing: border-box;
    padding: 1vh 1vh 3vh 1vh;
}

.photo-container {
    height: 250px;
    width: 400px;
}

.photo {
    object-fit: cover;
    overflow: hidden;
}
</style>