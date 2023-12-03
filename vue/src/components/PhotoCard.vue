<template>
    <article>
        <h2>{{ photo.title }}</h2>
        <img :src="photo.posterUrl">
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