<template>
    <div class="info">
        <div>
            <profile-picture :profilePic="user.picUrl" id="profile-picture" v-if="photoExists" class="profile-picture" />
            <img v-else
                :src="'https://media.istockphoto.com/id/1341046662/vector/picture-profile-icon-human-or-people-sign-and-symbol-for-template-design.jpg?s=612x612&w=0&k=20&c=A7z3OK0fElK3tFntKObma-3a7PyO8_2xxW0jtmjzT78='"
                class="profile-picture">
        </div>
        <div>
            <h1>{{ user.username }}</h1>
            <h2>{{ user.email }}</h2>
            <button id="follow" class="follow" v-if="!following" v-on:click="follow">Follow</button>
            <button id="unfollow" class="follow" v-if="following" v-on:click="unfollow">Unfollow</button>

        </div>
    </div>
</template>

<script>
import ProfilePicture from './ProfilePicture.vue';
import FollowGateway from '../services/FollowGateway';

export default {
    components: {
        ProfilePicture
    },
    data() {
        return {
            userId: this.$route.params.id,
            following: false,
            // user: {
            //     name: this.$store.state.searchedUser.username,
            //     email: this.$store.state.searchedUser.email,
            //     picUrl: this.$store.state.searchedUser.picUrl
            // },
        }
    },
    props: ['user'],
    computed: {
        photoExists() {
            return this.user.picUrl && this.user.picUrl.length > 0;
        }
    },
    methods: {
        follow() {
            if (this.$store.state.token !== '') {
                FollowGateway.addFollower(this.$store.state.user.id, this.userId);
                this.following = true;
            }
        },
        unfollow() {
            if (this.$store.state.token !== '') {
                FollowGateway.removeFollower(this.$store.state.user.id, this.userId);
                this.following = false;
            }
        }
    },
    created() {
        console.log("user prop = " + this.user);
        if (this.$store.state.token !== '') {
            FollowGateway.getUserFollowed(this.$store.state.user.id, this.userId)
                .then((response) => {
                    this.following = response.data;
                })
        }
    }
}
</script>

<style scoped>
.info {
    display: flex;
    justify-content: center;
    align-items: center;
}

h1 {
    font-size: 3em;
    color: rgb(55, 55, 157);
    margin: 0;
}

h2 {
    color: #555;
    margin-bottom: 30px;
}

.profile-picture {
    height: 200px;
    width: 200px;
    margin-top: 30px;
    margin-right: 40px;
    margin-bottom: 30px;
    border-radius: 50%;
}

.follow {
    color: aliceblue;
    font-size: 1.5rem;
    background-color: #7C93C3;
    border: #6d87bf 2px solid;
    border-radius: 3px;
    padding: 4px 11px;
}
</style>