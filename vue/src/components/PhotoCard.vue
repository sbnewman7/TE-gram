<template>
    <article>
        <img :src="photo.photoUrl">
        <h2>{{ photo.caption }}</h2>
        
        <!-- <h3>{{ photo.comments[0] }}</h3> -->
    </article>
</template>

<script>
export default {
    props: ['photo']
}
</script>

<style scoped>
article {
    /* border: 1px solid rgb(0, 0, 0); */
    display: flex;
    background-color: #9eb8d9;
    flex-direction: column;
    align-items: center;
    padding: 10px, 0px, 10px, 0px;
    border: #7C93C3 5px solid;
border-radius: 5px;
color: rgb(255, 255, 255);
}

img {
    width: 25vw;
    margin: 10px 0; /* Adjust the margin */
    border: #c3d1e4 5px solid;
    border-radius: 5px;
}

h2 {
    margin: 0 10px; 
    /* padding: 10px;  */
}
</style>