<template>
  <div class="container">
    <form class="panel" action="/action_page.php">
      <img v-if="picUploaded == false" class="demophoto"
        src="https://www.boredpanda.com/blog/wp-content/uploads/2023/07/ai-fails-39-64a2957c301bd__700.jpg"
        alt="Weiner Dog Race">
      <img v-else class="uploadphoto" :src="imgUrl" alt="Uploaded Pic">
      <div class="inputfield">
        <label for="caption">Caption:&nbsp;</label>
        <input class="textBox" v-model="caption" type="text" id="caption" name="caption" size="50">
      </div>
      <div>
        <label for="private">Private:</label>
        <input type="checkbox" name="private" id="private" v-model="isPrivate">
      </div>
    </form>
    <div class="buttons">
      <button class="control" v-on:click="upload">Upload</button><br />
      <button class="control" v-on:click="submit">Submit</button><br />
      <!-- <button class="control" v-on:click="home">Home</button><br /> -->
      <button v-if="showUpdated" class="edit updated">Photo Uploaded!</button>
      <button v-if="showError" class="edit updated error">There was an error, please try again later.</button>
    </div>
  </div>
</template>
    
<script>
import PhotosGateway from "../services/PhotosGateway";


export default {
  data() {
    return {
      picUploaded: false,
      imgUrl: "",
      caption: "",
      isPrivate: false,
      showUpdated: false,
      showError: false
    }
  },
  name: ':PhotoUploadComponent',
  methods: {
    submit() {
      let photo = {};
      photo.userId = this.$store.state.user.id;
      photo.caption = this.caption;
      photo.photoUrl = this.imgUrl;
      photo.private = this.isPrivate;
      PhotosGateway
        .addPhoto(photo)
        .then(response => {
          if (response.status === 201) {
            this.showUpdated = true;
            window.setTimeout(() => {
              this.showUpdated = false;
            }, 2500);
          }
        }).catch(error => {
          if (error.response || error.request) {
            this.showUpdated = false;
            this.showError = true;
            window.setTimeout(() => {
              this.showError = false;
            }, 4000)
          }
        });
    },
    home() {
      this.$router.push("/");
    },
    upload() {
      const uploadWidget = cloudinary.createUploadWidget({
        cloudName: import.meta.env.VITE_CLOUDINARY_CLOUD_NAME,
        uploadPreset: import.meta.env.VITE_CLOUDINARY_CLOUD_UPLOAD,
        sources: ['local', 'url', 'camera']
      }, (error, result) => {
        if (!error && result && result.event === "success") {
          this.imgUrl = result.info.url;
          this.picUploaded = true;
        }
      });

      let text = "Allow access to Photos and Camera";
      if (confirm(text) == true) {
        uploadWidget.open();
      } else {
        alert("Upload canceled!");
      }
    }
  }
}
</script>
    
<style scoped>
* {
  color: aliceblue;
  font-size: 1.5rem;
}

.textBox {
  color: #404040;
  font-size: 1rem;
}

.control {
  padding: 5px;
  margin: 10px;
  background-color: #7C93C3;
  border: #6d87bf 2px solid;
  border-radius: 3px;
}

.control:hover {
  background-color: #91a5cd;
  border: #93a6d0 2px solid;
}

label {
  display: inline-block;
  width: 110px;
  text-align: right;
}

.inputfield {
  display: flex;
  align-items: end;
  width: 100%;
  height: 23px;
  margin: 5px;
}

.demophoto {
  filter: grayscale(90%);
  opacity: 0.8;
  margin: 20px;
  width: 350px;
  text-align: center;
}

.uploadphoto {
  margin: 20px;
  width: 350px;
  text-align: center;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.panel {
  display: flex;
  flex-direction: column;
  background-color: #9eb8d9;
  border: #7C93C3 5px solid;
  border-radius: 5px;
  color: #ffffff;
  align-items: center;
}

.buttons {
  display: flex;
}


.updated {
  border: none;
  position: absolute;
  top: 44%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  max-width: 300px;
  width: 100%;
  text-align: center;
  background-color: green;
}

.error {
  background-color: rgb(225, 42, 42);
}
</style>