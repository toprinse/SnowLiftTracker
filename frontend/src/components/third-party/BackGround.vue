<template>
    <div :style="image"></div>
</template>
  
  <script>
  export default {
    data() {
      return {
        image: { backgroundImage: "url(https://jeromeobiols.com/wordpress/wp-content/uploads/photo-montagne-vallee-blanche-chamonix-mont-blanc.jpg)", 
                 }
      };
    }
  };
  </script>