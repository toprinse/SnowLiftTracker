<template>
    <v-container fluid>
      <v-row no-gutters>
        <v-col cols="5" class="mx-auto">
          <MapSkiLift style="height: 65vh;"></MapSkiLift>
        </v-col>
        <v-col cols="5" class="mx-auto" > 
          <ListStationInterfaces :id= "StationId"></ListStationInterfaces>

          <!-- Bouton pour afficher/masquer AddStation -->
          <v-btn @click="toggleAddStation" v-if="$store.state.user.adminRole">
            Ajouter une remontée
          </v-btn>
          <!-- Bouton pour afficher/masquer DeleteStation -->
          <v-btn @click="toggleDeleteStation" v-if="$store.state.user.adminRole">
            Supprimer une remontée
          </v-btn>

          <!-- Composant AddStation conditionnel -->
          <v-container>
          <AddSkiLift :id= "StationId" v-if="showAddStation" style="max-width: 50%; margin-top: 1%;"></AddSkiLift>
          <!-- Composant AddStation conditionnel -->
          <DeleteSkiLift :id= "StationId" v-if="showDeleteStation" style="max-width: 500; margin-left: 1%;"></DeleteSkiLift>
          </v-container>

       </v-col>
    </v-row>
  </v-container>
</template>

<script>

// Third parties imports 

// SnowLift Tracker imports
import ListStationInterfaces from '@/components/snowlifttracker/ListStationInterfaces.vue';
import MapSkiLift from '@/components/third-party/MapSkiLift.vue';
import AddSkiLift from '@/components/admin/AddSkiLift.vue';
import DeleteSkiLift from '@/components/admin/DeleteSkiLift.vue';

export default {
  components: {
    ListStationInterfaces,
    MapSkiLift,
    AddSkiLift,
    DeleteSkiLift
},
  data() {
    return {
      showAddStation: false,
      showDeleteStation: false
      };
    },
  computed: {
    // Return station id given in URL
    StationId () {
      console.log(this.$route.params.id);
      return (this.$route.params.id);
    }
  },
  methods: {
        toggleAddStation() {
          this.showAddStation = !this.showAddStation;
          this.showDeleteStation = false;
        },

        toggleDeleteStation() {
          this.showDeleteStation = !this.showDeleteStation;
          this.showAddStation = false;
        }
      },
};

</script>