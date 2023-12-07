<template>
    <article>
        <img :src="photo.photoUrl">
        <div class="like-caption">
            <img v-if="liked" v-on:click="unlike" class="heart"
                src="https://upload.wikimedia.org/wikipedia/commons/3/35/Red-simple-heart-symbol-only.png" alt="">
            <img v-if="!liked" v-on:click="like" class="heart-dark"
                src="https://upload.wikimedia.org/wikipedia/commons/3/35/Red-simple-heart-symbol-only.png" alt="">
            <h2>{{ photo.caption }}</h2>
        </div>

        <div v-if="hasComments">
            <span>
                {{ photo.comments[0].commentBody }}
            </span>
        </div>
    </article>
</template>

<script>
import LikesGateway from '../services/LikesGateway';

export default {
    data() {
        return {
            liked: false
        }
    },
    created() {
        if (this.$store.state.token !== '') {
            console.log(this.$store.state.user.id);
            LikesGateway.getLiked(this.$store.state.user.id, this.photo.id)
                .then((response) => {
                    console.log(response.data)
                    if (response.data) this.like();
                });
            // console.log(isLiked);
            // if (isLiked) {
            //     this.like();
            // } else {
            //     this.unlike();
            // }
        }
    },
    props: ['photo'],
    computed: {
        hasComments() {
            return this.photo.comments && this.photo.comments.length != 0;
        }
    },
    methods: {
        like() {
            if (this.$store.state.token !== '') {
                this.liked = true;
            }
        },
        unlike() {
            this.liked = false;
        }
    }

}
</script>

<style scoped>
article {
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
}

.heart-dark {
    max-width: 50px;
    opacity: 30%;
    border: none;
}

.like-caption {
    display: flex;
    align-items: center;
}

img {
    width: 300px;
    margin: .75vw;
    border: #c3d1e4 5px solid;
    border-radius: 5px;
}

h2 {
    margin: 0 10px;
    width: 30vh;
}

span {
    display: block;
    font-size: 1.1rem;
    width: 30vh;
    box-sizing: border-box;
    padding: 1vh 1vh 3vh 1vh;

}
</style>