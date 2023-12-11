<template>
    <div class="home">
        <h1>Following</h1>
        <section class="photo-list">
            <photo-card class="photo" :photo="photo" v-for="photo in photos" :key="photo.id" />
        </section>
    </div>
</template>
  
<script>
import PhotoCard from "../components/PhotoCard.vue"
import FollowGateway from "../services/FollowGateway";
import photoService from "../services/PhotosGateway"


export default {
    name: "Following",
    components: {
        PhotoCard
    },
    data() {
        return {
            photos: [],
            following: []
        }
    },
    created() {
        FollowGateway.getFollowing(this.$store.state.user.id)
            .then(response => {
                this.following = response.data;
            })
        photoService.getAll()
            .then(response => {
                this.photos = response.data.filter((photo) => { return this.following.includes(photo.id) });
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