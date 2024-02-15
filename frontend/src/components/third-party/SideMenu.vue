<template>
  <v-layout justify-center align-center>
    <v-app-bar app color="blue" dark dense elevate-on-scroll>
      <v-app-bar-title>SnowLiftTracker</v-app-bar-title>
      <v-spacer></v-spacer>

      <v-btn to="/">Home Page</v-btn>
      <v-btn to="/contact">Contacts</v-btn>

      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn v-bind="attrs" v-on="on" icon>
            <v-icon>mdi-information</v-icon>
          </v-btn>
        </template>
        <v-list dark>
          <v-list-item>
            <v-list-item-content>
              <UserCard :keycloak="keycloak"></UserCard>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </v-layout>
</template>

<script>
import UserCard from "@/components/UserCard"; // Adjust the path accordingly

export default {
  data() {
    return {
      isShow: false,
    };
  },
  components: {
    UserCard,
  },
};
</script>


<script>
import UserCard from './UserCard.vue';

export default {
    name: "ToggleDiv",
    data: function () {
        return { isShow: false };
    },
    props: ["keycloak"],
    components: { UserCard }
};

</script>

<style scoped>
.centered-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 100%; /* Adjust the height as needed */
}
</style>