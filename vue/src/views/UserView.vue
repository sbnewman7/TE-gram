<template>
    <span class="megacontainer">
        <div class="container">
            <h1>Edit Profile</h1>
            <UserProfile :user="currentUser" />
        </div>
    </span>
</template>

<script>

import UserProfile from '../components/UserProfile.vue';

export default {
    name: "User Profile",
    components: {
        UserProfile
    },
    computed: {
        currentUser() {
            return this.$store.getters.currentUser;
        }
    }
}
</script>

<style scoped>
h1 {
    font-size: 3.5rem;
    color: rgb(42, 42, 53);
    margin-top: 13px;
    margin-bottom: 13px;
    display: flex;
    justify-content: center;
}

.megacontainer {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}


.container {
    min-height: 100vh;
    padding: 20px
}
</style>