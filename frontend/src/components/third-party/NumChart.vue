<template>
  <div :style="{ backgroundColor: 'white' }">
    <Bar 
      id="my-chart-id"
      :options="chartOptions"
      :data="chartData"
    />
  </div>
</template>
  
<script>
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'NumChart',
  components: { Bar },
  props: {
    id_skilift: Number,
    id_sensor: Number,
  },
  data() {
    return {
      chartOptions: {
        responsive: true,
      },
    }
  },
  computed: {
    chartData(){
      var tmp = this.$store.getters['sensors/getAllSensorsNum'];
      const SENSOR_ID = this.id_sensor;
      const SKILIFT_ID = this.id_skilift;
      let xaxis = [] // data
      let yaxis = [] // timestamp

      // For every sensors with measures
      for(let j = 0; j < tmp.length; j++){
        // if there is a skilift and measures
        if(tmp[j].skiLift != null && !(tmp[j].numMeasureList.length == 0)){
          // if good skilift and good sensor 
          if((tmp[j].skiLift.id == SKILIFT_ID) && (tmp[j].id == SENSOR_ID)){
            // For every measures in the list in the sensor
            for(let i = 0; i < tmp[j].numMeasureList.length; i++ ){
              let index = xaxis.indexOf(tmp[j].numMeasureList[i].timestamp)
              // if the timestamp is not in the xaxis
              if(index == -1){
                xaxis.push(tmp[j].numMeasureList[i].timestamp)
                yaxis.push(+ tmp[j].numMeasureList[i].numData) // + unary operator to convert 'true' to '1' for example
              }
              else{
                yaxis[index] += 1
              }
            }
          }
        }
      }
      console.log("xaxix",xaxis);
      return {
        labels: xaxis,
          datasets: [ 
            { 
              label: "Measures",
              backgroundColor: '#f87979',
              data: yaxis
            } 
          ]
      }
    }

  },
  mounted() {
    this.$store.dispatch('sensors/getSensorsNum')
  },
  methods:{
    datefilter(date){
      return date 
    }
  }
}
</script>

<!-- https://vue-chartjs.org/examples/#vue-2-charts-vue-chartjs-v4 -->