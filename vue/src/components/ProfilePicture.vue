<template>
    
    <div class="circular--landscape">
        <img :src="profilePic">
    </div>

</template>

<script>
    export default {
        props:['profilePic']
    }
</script>

<style scoped>

.circular--landscape {
  margin-left: 10px;
  border-radius: 50%;
  background-color: black;
  border: #7C93C3 2px solid;
  position: relative;
  width: 50px;
  height: 50px;
  overflow: hidden;
}
.circular--landscape img {
  position: absolute;
  left: 50%;
  top: 50%;
  height: 100%;
  width: auto;
  -webkit-transform: translate(-50%,-50%);
      -ms-transform: translate(-50%,-50%);
          transform: translate(-50%,-50%);
}

</style>