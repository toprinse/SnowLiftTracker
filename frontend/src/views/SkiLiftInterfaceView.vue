<template>
  <v-container fluid>
    <!-- Header -->
    <v-row no-gutters>
        <v-col cols="4" class="mx-auto">
          <center><h1>Temperature</h1></center>
          <center><v-img
            class="zoom"
            width="100"
            :aspect-ratio="1"
            src="../assets/Thermomètre.png"
          ></v-img></center>
          <br>
        </v-col>
        <v-col cols="4" class="mx-auto">
          <center><h1>Humidity</h1></center>
          <center><v-img
            class="zoom"
            width="100"
            :aspect-ratio="1"
            src="../assets/Humidité.png"
          ></v-img></center>
          <br>
        </v-col>
        <v-col cols="4" class="mx-auto">
          <center><h1>Pressure</h1></center>
          <center><v-img
            class="zoom"
            width="40"
            :aspect-ratio="1"
            src="../assets/barometre.png"
          ></v-img></center>
          <br>
        </v-col>
    </v-row>
    <!-- ~Header~ -->

    <!-- Graphics -->
    <v-row no-gutters>
      <!-- Analogs -->
      <v-col class="mt-2" cols="4">
          <MultiLineChart :id_skilift=SkiLiftId :id_sensor=getSensorIdFromType(0)></MultiLineChart>
      </v-col>
      <v-col class="mt-2" cols="4">
          <MultiLineChart :id_skilift=SkiLiftId :id_sensor=getSensorIdFromType(1)></MultiLineChart>
      </v-col>
      <v-col class="mt-2" cols="4">
        <MultiLineChart :id_skilift=SkiLiftId :id_sensor=getSensorIdFromType(2)></MultiLineChart>
      </v-col>
    </v-row>

    <v-row no-gutters>
      <v-spacer></v-spacer>
      <center><h1>Presence</h1></center>  
      <v-spacer></v-spacer>
    </v-row>
    <v-row no-gutters>
        <v-spacer></v-spacer>
          <center><v-img
            class="zoom"
            width="100"
            :aspect-ratio="1"
            src="../assets/skieur.png"
          ></v-img></center>
          <center><v-img
            class="zoom"
            width="100"
            :aspect-ratio="1"
            src="../assets/skieur.png"
          ></v-img></center>
          <center><v-img
            class="zoom"
            width="100"
            :aspect-ratio="1"
            src="../assets/skieur.png"
          ></v-img></center>
          <center><v-img
            class="zoom"
            width="100"
            :aspect-ratio="1"
            src="../assets/ski-lift.png"
          ></v-img></center>
          <v-spacer></v-spacer>
    </v-row>
    <!-- Numeric -->
    <v-row no-gutters>
      <v-spacer></v-spacer>
      <v-col class="mt-2" cols="6" v-for="sensorID in getIdSensorNum" :key="sensorID.valueOf()">
        <NumChart :id_skilift=SkiLiftId :id_sensor="sensorID"></NumChart>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <!-- ~Graphics~ -->
  </v-container>
  </template>

<script>
// Third parties imports 
import MultiLineChart from '@/components/third-party/MultiLineChart.vue';
import NumChart from '@/components/third-party/NumChart.vue';

export default {
components: {
  MultiLineChart,
  NumChart
},

data() {
		return {

    }
  },

computed: {
  // Return skiLift id given in URL
  SkiLiftId () {
    return Number(this.$route.params.id)
  },

  // Return list of sensors id in skilift
  getIdSensorAnalog () {
    let tmp = this.$store.getters['sensors/getAllSensorsAnalog'];
    const SKILIFT_ID = this.$route.params.id;
    let id_sensor = [] // ID des sensors

    // For every sensors with measures
    for(let j = 0; j < tmp.length; j++){
      // if there is a skilift
      if(tmp[j].skiLift != null ){
        // if good skilift         
        if(tmp[j].skiLift.id == SKILIFT_ID) {
          // If good skilift than push in id_sensor
          id_sensor.push(tmp[j].id);
          }
        }
      }
      return id_sensor
  },

  // Return list of sensors id in skilift
  getIdSensorNum () {
      let tmp = this.$store.getters['sensors/getAllSensorsNum'];
      const SKILIFT_ID = this.$route.params.id;
      let id_sensor = [] // ID des sensors

      // For every sensors with measures
      for(let j = 0; j < tmp.length; j++){
        // if there is a skilift
        if(tmp[j].skiLift != null ){
          // if good skilift         
          if(tmp[j].skiLift.id == SKILIFT_ID) {
            // If good skilift than push in id_sensor
            id_sensor.push(tmp[j].id);
            }
          }
        }
        return id_sensor
    }

},
methods: {
  getSensorIdFromType (type) {
    let tmp = this.$store.getters['sensors/getAnalogTypes'];
    let sensorId = 0;
    let type_str = ['Temperature', 'Humidity', 'Pressure'];
    

    for(let i = 0; i < tmp.length; i++){
      //good sensor      
      if(tmp[i].sensorType.type == type_str[type]){
        sensorId = tmp[i].id;
      }
    }
    return sensorId;
  },
},
mounted() {
  this.$store.dispatch('sensors/getSensorsAnalog');
  this.$store.dispatch('sensors/getSensorsNum');
  this.$store.dispatch('sensors/getAnalogTypes_');
}
};
</script>

<style>
.zoom {
  padding: 50px;
  transition: transform .2s;
  margin: 0 auto;
}

.zoom:hover {
  -ms-transform: scale(1.5); /* IE 9 */
  -webkit-transform: scale(1.5); /* Safari 3-8 */
  transform: scale(1.5); 
}
</style>