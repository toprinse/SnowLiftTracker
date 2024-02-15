<template>
  <l-map
  :center="center"
  :zoom="zoom"
  :minZoom=10
  :maxZoom=15
  :maxBounds="maxBounds"
  style="height: 100%; 
          width: 45%;
          position: fixed;
          border: 2mm ridge rgba(203, 211, 234, .6);" 
  
  class=""
  ref="map"
  @update:zoom="zoomUpdated"
  @update:center="centerUpdated"
  >
      <l-tile-layer
      :url="url"
      >
      </l-tile-layer>

      <l-marker v-for="skilift in skiLifts" :key=skilift.id
       :lat-lng="[skilift.latitude, skilift.longitude]" 
       :icon="icon" 
       @click="GoToSkiLiftInterface(skilift.id)"></l-marker>

  </l-map>
</template>

<script>

  import { LMap, LTileLayer, LMarker} from 'vue2-leaflet';
  import L from 'leaflet';
  import 'leaflet/dist/leaflet.css';

  export default {
    
    components: {
    LMap,
    LTileLayer,
    LMarker
    },

  data () {   // Toutes les valeurs appellée dans le template doivent être décrit içi
    return {
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      center: [ 45.086804968573205, 5.95757480202633 ], // Point centrale de la carte
      zoom: 14,   // Zoom initial
      skiLifts: [],

      maxBounds:([    
      [42.71704703780538, -2.017872598941808], //Limite de la carte : SouthWest
        [50.96179502998038, 7.817087802116389]    //Limite de la carte : NorthEast
    ]),

    icon: L.icon({    //Structure icone "skieur"
      iconUrl: 'https://cdn-icons-png.flaticon.com/512/9954/9954834.png',
      iconSize: [32, 37],
      iconAnchor: [16, 37]
    }),

    }
  },

  methods: {
    zoomUpdated (zoom) {        
          this.zoom = zoom;
          console.log(this.markers);
    },

    centerUpdated (center) {
      this.center = center;
    },

    //Go to station interface linked by the id
    GoToSkiLiftInterface (id) {
      console.log(id);
      this.$router.push(`/skiliftinterface/${id}`);
      }

  },

  computed:{

    skiLifts1() {
            const station_id = Number(this.$route.params.id);
            let stations = this.$store.getters['stations/getStationSkiLift'];
            let skiLiftsTampon = [];
            let skiLifts = [];

            for(let j = 0; j < stations.length; j++){
              if(stations[j].id == station_id){
             
               skiLiftsTampon.push(stations[j].skiLiftList);}
            }
            skiLifts = skiLiftsTampon[0];
            //OK
            return  skiLifts
          }
  },

  watch: {

    skiLifts1: function(skiLifts1) {
      this.skiLifts = skiLifts1;
      this.center = [skiLifts1[0].latitude,  skiLifts1[0].longitude];
      //OK
    }
  },

  mounted() {
    this.$store.dispatch('stations/getSkiLifts')
  }

  }
</script>

<style>
.map {
position: absolute;
width: 100%;
height: 100%;
overflow :hidden;
}
</style>