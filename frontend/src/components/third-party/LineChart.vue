<template>
    <LineChartGenerator 
      id="my-chart-id"
      :options="chartOptions"
      :data="chartData"
    />
</template>
  
<script>
import { Line as LineChartGenerator } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, LineElement, CategoryScale, LinearScale, PointElement } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, LineElement, LinearScale, CategoryScale, PointElement)

export default {
  name: 'LineChart',
  components: { LineChartGenerator },
  data() {
    return {
      AnalogMeasure: [],
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      },
    }
  },
  computed: {
  chartData(){
    var tmp = this.$store.getters['measures/getAllAnalogMeasures']
    let xaxis = []
    let yaxis = []

    for(let i = 0; i < tmp.length; i++ ){
        xaxis.push(tmp[i].timestamp)
        yaxis.push(tmp[i].analogData) 
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
    this.$store.dispatch('measures/getAnalogMeasures')
  }
}
</script>

<!-- https://codesandbox.io/p/sandbox/github/apertureless/vue-chartjs/tree/v4/legacy/sandboxes/line?file=%2Fsrc%2Fcomponents%2FLine.vue%3A96%2C9-96%2C35 -->
