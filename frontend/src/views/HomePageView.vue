<template>
  <v-responsive width="100%">
    <v-container fluid>
      <v-row>

        <v-col cols="5" class="mx-auto">
          <MapStation style="height: 65vh;"></MapStation>
        </v-col> 
 
        <v-col cols="5" class="mx-auto">
          <ListStations style="width: 100%;" class="mb-2"></ListStations>
          
          <!-- Bouton pour afficher/masquer AddStation -->
          <v-btn @click="toggleAddStation" v-if="$store.state.user.adminRole">
            Ajouter une station
          </v-btn>
          <!-- Bouton pour afficher/masquer DeleteStation -->
          <v-btn @click="toggleDeleteStation" v-if="$store.state.user.adminRole">
            Supprimer une station
          </v-btn>

          <!-- Composant AddStation conditionnel -->
          <v-container>
          <AddStation v-if="showAddStation" style="max-width: 50%; margin-top: 1%;"></AddStation>
          <!-- Composant AddStation conditionnel -->
          <DeleteStation v-if="showDeleteStation" style="max-width: 500; margin-left: 1%;"></DeleteStation>
          </v-container>
        </v-col>
    </v-row>
    </v-container>
  </v-responsive>
</template>

<script>

  // Third parties imports 
  import MapStation from '@/components/third-party/MapStation.vue';

  // SnowLift Tracker imports
  import ListStations from '@/components/snowlifttracker/ListStations.vue';
  import AddStation from '@/components/admin/AddStation.vue';
  import DeleteStation from '@/components/admin/DeleteStation.vue';

  export default {
    components: {
    MapStation,
    ListStations,
    AddStation,
    DeleteStation
},

    data() {
    return {
      showAddStation: false,
      showDeleteStation: false
      };
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