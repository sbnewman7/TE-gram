<template>
    <article>
        <!-- <h2>{{ photo.caption }}</h2> -->
        <p>I am here!</p>
        <img :src="photo.photoUrl">
    </article>
</template>

<script>
export default {
    props: ['photo']
}
</script>

<style scoped>
article {
    border: 1px solid black;
}
</style>