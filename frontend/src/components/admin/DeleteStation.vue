<template>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        persistent
        width="auto"
      > 
        <v-card>
          <v-card-title>Selectionner la station à supprimer</v-card-title>
          <v-divider></v-divider>
          <v-card-text style="max-height: 1024px;">
            <v-list-item v-for="station in stations" :key="station.id" @click="ClickOnStationDelete(station)">
                  <template>
  
                      <v-list-item-action>
                          <v-icon >mdi-delete</v-icon>
                      </v-list-item-action>
  
                      <v-list-item-content>
                        <v-list-item-title>{{station.name}}</v-list-item-title>
                        <v-list-item-subtitle>ID : {{station.id}} </v-list-item-subtitle>
                      </v-list-item-content>

                    <v-dialog v-model="dialogDelete" max-width="500">
                        <v-card>
                            <v-card-title>
                                <h4>Etes-vous sûr de vouloir supprimer {{ stationToDelete.name }} ?</h4>
                            </v-card-title>
                            <v-card-actions>
                                <v-spacer/>
                                <v-btn text @click="toggleDelete">Annuler</v-btn>
                                <v-btn text @click="confirmDelete()">OK</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>

                </template>
            </v-list-item>
          </v-card-text>
          <v-divider></v-divider>

          <v-card-actions>
            <v-btn
              color="blue-darken-1"
              variant="text"
              @click="dialog = false"
            >
              Annuler
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </template>


<script>
export default {
    name: "DeleteStation",
    data() {
        return {
           stations: [],
           dialogDelete: false,
           stationToDelete: [],
           dialog:true

        }
    },
    methods: {
        ClickOnStationDelete(station_delete) {
            this.dialogDelete = true;
            this.stationToDelete = station_delete;
            },
        
        toggleDelete() {
            this.dialogDelete = false;
        },

        confirmDelete() {
            this.$store.dispatch("stations/deleteStation", this.stationToDelete.id);
            this.dialogDelete = false;
            this.dialog = false;
            }
    },
    computed: {
		stations1() {
			return this.$store.getters['stations/getAllStations'];
		}
	},
	watch: {
		stations1: function(stations1) {
			this.stations = stations1;
		}
	},
	mounted() {
		this.$store.dispatch('stations/getStations')
	}
}
</script>
