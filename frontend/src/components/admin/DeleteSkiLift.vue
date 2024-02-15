<template>
    <v-row justify="center">
      <v-dialog
        v-model="dialog"
        persistent
        width="auto"
      > 
        <v-card>
          <v-card-title>Selectionner la remontée à supprimer</v-card-title>
          <v-divider></v-divider>
          <v-card-text style="max-height: 1024px;">
            <v-list-item v-for="skilift in skilifts" :key="skilift.id" @click="ClickOnSkiliftDelete(skilift)">
                  <template>
  
                      <v-list-item-action>
                          <v-icon >mdi-delete</v-icon>
                      </v-list-item-action>
  
                      <v-list-item-content>
                        <v-list-item-title>{{skilift.name}}</v-list-item-title>
                        <v-list-item-subtitle>ID : {{skilift.id}} </v-list-item-subtitle>
                      </v-list-item-content>

                    <v-dialog v-model="dialogDelete" max-width="500">
                        <v-card>
                            <v-card-title>
                                <h4>Etes-vous sûr de vouloir supprimer {{ skiliftToDelete.name }} ?</h4>
                                <h5> (Les capteurs et les mesures affiliés seront supprimer)</h5>
                            </v-card-title>
                            <v-card-actions>
                                <v-spacer/>
                                <v-btn text @click="toggleDelete">Annuler</v-btn>
                                <v-btn text @click="confirmDelete(skiliftToDelete.id)">OK</v-btn>
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
    name: "DeleteSkiLift",
    props: {
		id: String,
	},

    data() {
        return {
          skilifts: [],

          sensorsToDeleteAnalog: [],
          sensorsToDeleteNum: [],

          measuresToDeleteAnalog: [],
          measuresToDeleteNum: [],

          dialogDelete: false,
          skiliftToDelete: [],
          dialog:true,

        }
    },

    methods: {
        ClickOnSkiliftDelete(skilift_delete) {
            this.dialogDelete = true;
            this.skiliftToDelete = skilift_delete;
            },
//===============================================================================================        
        toggleDelete() {
            this.dialogDelete = false;
        },

        confirmDelete() {
            this.$store.dispatch("skiLifts/deleteSkiLift", this.skiliftToDelete.id);
            this.dialogDelete = false;
            this.dialog = false;
            }


    },

    computed: {
		skilift1() {
            return this.$store.getters['stationInterfaces/getStationInterfacesById_'];
		},

	},
	watch: {
		skilift1: function(skilift1) {
			this.skilifts = skilift1;
		},
	},
	mounted() {
		this.$store.dispatch('stationInterfaces/getStationInterfacesById', this.id)
	}
}
</script>
