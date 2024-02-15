<template>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        persistent
        width="1024"
      >
        <v-card>
          <v-card-title>
            <h5>Ajout d'une station</h5>
          </v-card-title>
          <v-card-text>
             <v-text-field v-model="station.name" label="Nom*" placeholder="Nom de la station" required></v-text-field>
            <v-text-field v-model="station.longitude" label="Longitude*" type="number"></v-text-field>
            <v-text-field v-model="station.latitude" label="Latitude*" type="number"></v-text-field>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="dialog = false"
            >
              Annuler
            </v-btn>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="saveStation"
              :disabled="!isAllFieldsFilled"
            >
              Ajouter
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </template>



<script>
export default {
    name: "AddStation",
    data() {
        return {
            station: {
                id: null,
                name: "",
                longitude: "",
                latitude: ""
            },

            defaultStation: {
                id: null,
                name: "",
                longitude: "",
                latitude: ""
            },
            dialog: true,
        }
    },
    methods: {
        saveStation() {
            var data = {
            id: null,
            name: this.station.name,
            longitude: this.station.longitude,
            latitude: this.station.latitude,
            
            };

            console.log(data);
            this.$store.dispatch("stations/createStation", data);
            Object.assign(this.station, this.defaultStation);

            this.dialog = false
        }
    },
    computed: {
    isAllFieldsFilled() {
      return (
        this.station.name.trim() !== '' &&
        this.station.longitude.trim() !== '' &&
        this.station.latitude.trim() !== ''
      );
    }
    },
}
</script>
