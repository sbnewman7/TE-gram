<template>
    <article @click="goToDetails">
        <img :src="photo.photoUrl">
        <h2>{{ photo.caption }}</h2>
        <div v-if="hasComments">
            <span>
                {{ photo.comments[0].commentBody }}
            </span>
        </div>
    </article>
</template>

<script>

export default {
    props: ['photo'],
    computed: {
        hasComments() {
            return this.photo.comments && this.photo.comments.length != 0;
        }
    },
    methods: {
        goToDetails() {
            this.$router.push('/photos/'+this.photo.id)
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