<template>
    <article>
        <img :src="photo.photoUrl">
        <h2>{{ photo.caption }} {{ photo.caption }} {{ photo.caption }} {{ photo.caption }}</h2>
        <div>
            <span>
        I am a comment! this comment 
is about this photo! I am a comment! this comment 
is about this photo!I am a comment! this comment 
is about this photo!I am a comment! this comment 
is about this photo!I am a comment! this comment 
is about this photo!
</span>
        </div>
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
    display: flex;
    background-color: #9eb8d9;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    padding: 10px, 0px, 10px, 0px;
    border: #7C93C3 5px solid;
border-radius: 5px;
color: rgb(255, 255, 255);
}

div{
   
   display: flex;
   justify-content: center;
    white-space: break-spaces;
}

img {
    width: 300px;
    margin: .75vw; 
    border: #c3d1e4 5px solid;
    border-radius: 5px;
}

h2 {
    margin: 0 10px; 
    /* padding: 10px;  */
    width: 30vh;
}

span {
    display: block;
    font-size: 1.1rem;
    width: 30vh;
    box-sizing: border-box; /* Include padding and border in the total width */ 
    padding: 1vh;   
}

</style>