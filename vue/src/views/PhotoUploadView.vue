<template>
    <div>
      <h2 class="centertext"> <em> Photo Upload </em> </h2>
      <PhotoUploadComponent />
    </div>
  </template>
  
  
  <script>
  // @ is an alias to /src
  import PhotoUploadComponent from '../components/PhotoUploadComponent.vue'
  
  export default {
    name: 'PhotoUploadView',
    components: {
      PhotoUploadComponent,
    },
    data(){
        return{
        }
    },
    created() {
    }
    
  }
  </script>
    
  <style scoped>
  
  .centertext {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  
  </style>
  