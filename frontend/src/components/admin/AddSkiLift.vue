<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent width="1024">
      <v-card>
        <v-card-title>
          <h5>Ajout d'une remontée</h5>
        </v-card-title>
        <v-card-text>
          <v-text-field v-model="skilift.name" label="Nom*" placeholder="Nom de la Skilift" required></v-text-field>
          <v-text-field v-model="skilift.longitude" label="Longitude*" type="number"></v-text-field>
          <v-text-field v-model="skilift.latitude" label="Latitude*" type="number"></v-text-field>
          <v-checkbox label="Ouvert" v-model="ouvert"></v-checkbox>
          <v-checkbox label="Fermé" v-model="ferme"></v-checkbox>
          <small>*indicates required field</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue-darken-1" variant="text" @click="dialog = false">Annuler</v-btn>
          <v-btn color="blue-darken-1" variant="text" @click="saveSkiLift" :disabled="!isAllFieldsFilled">Ajouter</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: "AddSkilift",
  props: {
    id: String,
  },
  data() {
    return {
      skilift: {
        id: null,
        name: "",
        longitude: "",
        latitude: "",
        open: this.ouvert,
        stationId: this.id
      },
      defaultSkilift: {
        id: null,
        name: "",
        longitude: "",
        latitude: "",
        open: true,
        stationId: this.id
      },
      dialog: true,
      ouvert: false,
      ferme: false,
    }
  },
  methods: {
    saveSkiLift() {

        var data1 = {
          id: null,
          name: this.skilift.name,
          longitude: this.skilift.longitude,
          latitude: this.skilift.latitude,
          open: this.skilift.open,
          stationId: this.id
        };
        this.$store.dispatch("skiLifts/createSkiLiftInStation", data1);
        Object.assign(this.skilift, this.defaultSkilift);

      this.dialog = false;
    }
  },

  computed: {
    isAllFieldsFilled() {
      return (
        this.skilift.name.trim() !== '' &&
        this.skilift.longitude.trim() !== '' &&
        this.skilift.latitude.trim() !== ''
      );
    }
  },
  watch: {
    ouvert(newVal) {
      if (newVal) {
        this.ferme = false;
        this.skilift.open = true;
      }
    },
    ferme(newVal) {
      if (newVal) {
        this.ouvert = false;
        this.skilift.open = false;
      }
    }
  }
}
</script>
