<template>
    <div class="info">
        <img v-bind:src="user.picUrl" alt="user photo" v-if="photoExists">
        <div>
            <h1>{{ user.name }}</h1>
            <h2>{{ user.email }}</h2>
        </div>
    </div>
</template>

<script>
export default {
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

img {
    max-width: 200px;
    /* height: auto; */
    border-radius: 50%;
    margin-right: 40px;
}
</style>