<template>
	<v-card>
		<v-card-title>
			<h2>Les numSensorStates</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="numSensorStates">
			
			</v-data-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListNumSensorStates",
	data() {
		return {
			numSensorStates: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "High", sortable: true, value: "high"},
				{ text: "Low", sortable: true, value: "low"}
			]
		}
	},
	computed: {
		numSensorStates1() {
			return this.$store.getters['numSensorStates/getAllNumSensorStates'];
		}
	},
	watch: {
		numSensorStates1: function(numSensorStates1) {
			this.numSensorStates = numSensorStates1;
		}
	},
	mounted() {
		this.$store.dispatch('numSensorStates/getNumSensorStates')
	}
}
</script>