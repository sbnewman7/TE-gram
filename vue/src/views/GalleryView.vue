<template>
    <div class="gallery">
        <user-details v-model:user="users" />
        <section class="photo-list">
            <photo-card class="photo" :photo="photo" v-for="photo in photos" :key="photo.id" />
        </section>
    </div>
</template>
  
<script>
import UserDetails from "../components/UserDetails.vue";
import PhotoCard from "../components/PhotoCard.vue"
import photoService from "../services/PhotosGateway"
import UserGateway from "../services/UserGateway";

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
        if (this.$route.params.id == this.$store.state.searchedUser.id) {
            console.log("ids match: param.id=" + this.$route.params.id + ", searchedUser.id=" + this.$store.state.searchedUser.id);
        } else {
            console.log("ids don't match: param.id=" + this.$route.params.id + ", searchedUser.id=" + this.$store.state.searchedUser.id);
        }
        photoService
            .getPhotosByUserId(this.$store.state.searchedUser.id)
            .then(response => {
                this.photos = response.data;
            })
    },
    beforeRouteUpdate(to, from, next) {
        // the 'to' param is a route object for the route we want to navigate to
        console.log('Before route update:', to.params.id);
        console.log(to.query);
        UserGateway.getUserById(to.params.id)
            .then(response => {
                this.users = response.data;
            })

        // this.users[0].id = to.params.id;
        // this.$store.commit('SET_SEARCHED_USER', )
        photoService
            .getPhotosByUserId(to.params.id)
            .then(response => {
                this.photos = response.data;
            })
        next();
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