<template>
    <div class="info">
        <profile-picture :profilePic="user.picUrl" id="profile-picture" v-if="photoExists" class="profile-picture" />
    
        <img v-else :src="'https://media.istockphoto.com/id/1341046662/vector/picture-profile-icon-human-or-people-sign-and-symbol-for-template-design.jpg?s=612x612&w=0&k=20&c=A7z3OK0fElK3tFntKObma-3a7PyO8_2xxW0jtmjzT78='" class="profile-picture">
        <div>
            <h1>{{ user.name }}</h1>
            <h2>{{ user.email }}</h2>
        </div>
    </div>
</template>

<script>

import ProfilePicture from './ProfilePicture.vue';

export default {
    components:{
        ProfilePicture
    },
    data() {
        return {
            userId: this.$route.params.id,
            user: {
                name: this.$store.state.searchedUser.username,
                email: this.$store.state.searchedUser.email,
                picUrl: this.$store.state.searchedUser.picUrl
            },
        }
    },
    computed: {
        photoExists() {
            return this.user.picUrl && this.user.picUrl.length > 0;
        }
    }
};
</script>

<style scoped>
.info {
    display: flex;
    justify-content: center;
    margin-top: 30px;
    margin-bottom: 30px;
    align-items: center;
}

h1 {
    font-size: 3em;
    color: rgb(55, 55, 157);
}

h2 {
    color: #555;
}

.profile-picture {
  height: 200px;
  width: 200px;
  margin-right: 40px;
  border-radius: 50%;
}
</style>