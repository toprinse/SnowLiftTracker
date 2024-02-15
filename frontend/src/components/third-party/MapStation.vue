<template>
        <l-map
        :center="center"
        :zoom="zoom"
        :minZoom=5
        :maxZoom=15
        :maxBounds="maxBounds"
        style="height: 100%; 
                width: 45%;
                position: fixed;
                border: 2mm ridge rgba(114, 140, 220, 0.6);" 
        
        class=""
        ref="map"
        @update:zoom="zoomUpdated"
        @update:center="centerUpdated"
        >
            <l-tile-layer
            :url="url"
            >
            </l-tile-layer>

            <l-marker v-for="station in stations" :key=station.id
             :lat-lng="[station.latitude, station.longitude]" 
             :icon="icon"
             @click="GoToStationInterface(station.id)"></l-marker>

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
        zoom: 5,   // Zoom initial
        stations: [],

        maxBounds:([    
        [42.71704703780538, -2.017872598941808], //Limite de la carte : SouthWest
        [50.96179502998038, 7.817087802116389]    //Limite de la carte : NorthEast
      ]),

      icon: L.icon({    //Structure icone "skieur"
        iconUrl: 'https://cdn-icons-png.flaticon.com/128/3163/3163769.png',
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
      GoToStationInterface (id) {
        this.$router.push(`/stationinterface/${id}`);
      }

    },

    computed:{
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
   
   <style>
    .map {
      position: absolute;
      width: 100%;
      height: 100%;
      overflow :hidden;
    }
   </style>