<template>
    <div class="info">
        <img v-bind:src="user.picUrl" alt="user photo">
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
            user: {
                name: "Joe Mama",
                email: "jm@gmail.com",
                picUrl: "https://cdn.pixabay.com/photo/2021/11/30/11/04/login-6835087_640.png"
            },

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