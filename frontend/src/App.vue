<template>
   <v-app>
    <v-main>
      
      <SideMenu :keycloak="keycloak"></SideMenu>
      <v-img :src="require('./assets/photo1.png')" min-height="100%" min-width="100%">
      <router-view></router-view>
    </v-img>
    </v-main>
  </v-app>
</template>

<script>

import SideMenu from '@/components/third-party/SideMenu.vue'

export default {
  name:'App',
  props: ["keycloak"],
  components: {
    SideMenu
},
  };
</script>
