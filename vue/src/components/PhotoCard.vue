<template>
    <article>
        <img :src="photo.photoUrl">
        <h2>{{ photo.caption }}</h2>

    </article>
</template>

<script>
export default {
    props: ['photo']
}
</script>

<style scoped>
article {
    border: 1px solid rgb(0, 0, 0);
    display: flex;
    background-color: #9eb8d9;
    flex-direction: column;
    align-items: center;
    padding: 10px, 0px, 10px, 0px;


}

img {
    height: 150px;
}
</style>