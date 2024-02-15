import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import Keycloak from 'keycloak-js'

Vue.config.productionTip = false

let initOptions = {
   url: 'http://localhost:8886',
   realm: 'snowlifttracker-realm',
   clientId: 'snowlifttracker-frontend',
   onLoad: 'login-required',
   enableLogging: true
   }

let keycloak = new Keycloak(initOptions)

keycloak.init({ onLoad: initOptions.onLoad, "checkLoginIframe": false}).then(
   auth => {
  
   if(!auth) {
    window.location.reload();
   } 
   else {
    sessionStorage.setItem("vue-token", keycloak.token);
    console.log("--> keycloak-token : ", keycloak.token);
    console.log("--> sessionStorage('vue-token') : ",
    sessionStorage.getItem("vue-token"));

    new Vue({
      router,
      store,
      vuetify,
      render: h => h(App, { props: { keycloak : keycloak }})
    }).$mount('#app')

    let payload = {
      idToken: keycloak.idToken,
      accessToken: keycloak.idToken
      }

      if(keycloak.token && keycloak.idToken && keycloak.token != '' &&
        keycloak.idToken != '') {

        store.commit("login", payload);
        payload = {
           username: keycloak.tokenParsed.preferred_username,
           givenName: keycloak.tokenParsed.given_name,
           familyName: keycloak.tokenParsed.family_name,
           email: keycloak.tokenParsed.email,
           adminRole: keycloak.hasResourceRole('admin')
           };

        store.commit("setName", payload);
           } 
           else {
           store.commit("logout");
           sessionStorage.setItem("vue-token", null);
           }
           }
           }).catch( () => {
           alert("Login failure")
           });
          
           Vue.prototype.$keycloak = keycloak
