<template>
  <div :style="{ backgroundColor: 'white' }">
    <LineChartGenerator 
      id="my-chart-id"
      :options="chartOptions"
      :data="chartData"
    />
  </div>
</template>

  
<script>
import { Line as LineChartGenerator } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, LineElement, CategoryScale, LinearScale, PointElement } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, LineElement, LinearScale, CategoryScale, PointElement)

export default {
  name: 'MultiLineChart',
  components: { LineChartGenerator },
  props: {
    id_skilift: Number,
    id_sensor: Number,
  },
  data() {
    return {
      AnalogMeasure: [],
      chartOptions: {
        responsive: true, 
      },
    }
  },
  computed: {
  chartData(){
    var tmp = this.$store.getters['sensors/getAllSensorsAnalog'];
    const SENSOR_ID = this.id_sensor;
    const SKILIFT_ID = this.id_skilift;
    let xaxis = [] // data
    let yaxis = [] // timestamp

    // For every sensors with measures
    for(let j = 0; j < tmp.length; j++){
      // if there is a skilift and measures
      if(tmp[j].skiLift != null && !(tmp[j].analogMeasureList.length == 0)){
        // if good skilift and good sensor 
        if((tmp[j].skiLift.id == SKILIFT_ID) && (tmp[j].id == SENSOR_ID)){
          // For every measures in the list in the sensor
          for(let i = 0; i < tmp[j].analogMeasureList.length; i++ ){
            xaxis.push(tmp[j].analogMeasureList[i].timestamp);
            yaxis.push(tmp[j].analogMeasureList[i].analogData);
          }
        }
      }
    }
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
    this.$store.dispatch('sensors/getSensorsAnalog')
  }
}
</script>

