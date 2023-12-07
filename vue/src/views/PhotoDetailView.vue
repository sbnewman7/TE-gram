<template>
    <div>
        <img :src="photo.photoUrl">
        <h1>{{ photo.caption }}</h1>
        <h3 v-for="comment in photo.comments" :key="comment.id">{{ comment.commentBody }}</h3>
    </div>
</template>

<script>

import PhotosGateway from '../services/PhotosGateway';


    export default {
        data(){
            return{
                photo: {}
            }
        },
        created() {
            PhotosGateway.getPhotoByPhotoId(this.$route.params.id).then(response => {
                this.photo = response.data;
            });
        }
    }
</script>

<style scoped>

</style>