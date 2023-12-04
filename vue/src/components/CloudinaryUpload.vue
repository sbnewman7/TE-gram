<template>
    <div class="cloud-upload">
        <button id="upload" v-on:click="upload">Upload Photo</button><br />
        <!-- Linking the  -->
        <h3>Last uploaded image:</h3>
        <!-- this will bind the image tag to the imgUrl variable loaded below 
           in createUploadWidget -->
        <img id="cloud-image" v-bind:src="imgUrl">
    </div>
</template>
  
<script>

export default {
    data() {
        return {
            imgUrl: "",
        }
    },
    props: ['pictureUrl'],
    name: 'CloudinaryUploadComponent',
    methods: {
        upload() {
            // This is the upload widget through Cloudinary; this demo uses an unsigned version for simplicity
            // This means anyone with some technical knowhow can look up our cloud name and upload to our demo site
            // Obviously not good from a security perspective!
            // We could set up an endpoint in a Java application to generate a signature if we wanted to be more
            // secure, but this demo is intended to show this as simply as possible.
            // More documentation here: https://cloudinary.com/documentation/upload_widget
            const uploadWidget = cloudinary.createUploadWidget({
                // Setting the values from the .env file; could be done manually if preferred
                // These values can be obtained through the Cloudinary site
                cloudName: import.meta.env.VITE_CLOUDINARY_CLOUD_NAME,
                uploadPreset: import.meta.env.VITE_CLOUDINARY_CLOUD_UPLOAD,
                // sources allows us to choose where the Cloudinary app gets files from
                // in this case, going to only allow file upload, URL, or camera image
                // Full list can be found on the Cloudinary documentation site linked above
                sources: ['local', 'url', 'camera']
            }, (error, result) => {
                if (!error && result && result.event === "success") {
                    // Some troubleshooting steps you can uncomment if needed
                    // console.log('Done! Here is the image info: ', result.info); 
                    // console.log("Image URL: " + result.info.url);
                    // window.alert("Successful upload!");
                    // At this point you can save the URL to an object:
                    // result.info.url

                    this.imgUrl = result.info.url;
                    this.$store.commit('SET_PIC_URL', result.info.url);

                    // There are other properties that might be useful, including:
                    // * result.info.height and result.info.width
                    //     can be used to size the image
                    // * result.info.bytes
                    //     can be used to determine file size
                    // * result.info.thumbnail_url
                    //     can be used to show a small version of the image                   
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
  
<style>
.cloud-upload {
    display: flex;
    flex-direction: column;
    align-items: center;

}

#cloud-image {
    max-width: 60%;
    height: auto;
    margin-bottom: 20px;
    border-radius: 5px;
}

#upload {
    background-color: #D9D9D9;
    border: none;
    border-radius: 4px;
    padding: 5px 2vw 5px 2vw;
    max-width: 60%;


}
</style>