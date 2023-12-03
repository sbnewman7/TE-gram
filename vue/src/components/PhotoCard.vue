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
    padding: 10px 0px 10px 0px;
    margin: 0px;
    width: 1000px;
}

h2{
    background-color: black;
    margin: 0px;
    padding: 0px;
}

img {
    height: 150px;
    margin: 0px;
    width: auto;
    
}
</style>