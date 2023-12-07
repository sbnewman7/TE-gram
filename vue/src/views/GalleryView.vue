<template>
    <div class="gallery">
        <user-details />
        <section class="photo-list">
            <photo-card class="photo" :photo="photo" v-for="photo in photos" :key="photo.id" />
        </section>
    </div>
</template>
  
<script>
import UserDetails from "../components/UserDetails.vue";
import PhotoCard from "../components/PhotoCard.vue"
import photoService from "../services/PhotosGateway"

export default {
    name: "Gallery",
    components: {
        UserDetails,
        PhotoCard
    },
    data() {
        return {
            users: [],
            photos: []
        }
    },
    created() {
        // console.log(this.$store.state.searchedUser.id);
        photoService
            .getPhotosByUserId(this.$store.state.searchedUser.id)
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
    color: rgb(55, 55, 157);
    margin-top: 13px;
    margin-bottom: 13px;
    display: flex;
    justify-content: center;
}

.photo {

    margin: 16px;
}
</style>