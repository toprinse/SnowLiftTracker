<template>
	<v-card>
		<v-card-title>
			<h2>Les analogSensorUnits</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="analogSensorUnits">
			
			</v-data-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListAnalogSensorUnits",
	data() {
		return {
			analogSensorUnits: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Unit", sortable: true, value: "unit"}
			]
		}
	},
	computed: {
		analogSensorUnits1() {
			return this.$store.getters['analogSensorUnits/getAllAnalogSensorUnits'];
		}
	},
	watch: {
		analogSensorUnits1: function(analogSensorUnits1) {
			this.analogSensorUnits = analogSensorUnits1;
		}
	},
	mounted() {
		this.$store.dispatch('analogSensorUnits/getAnalogSensorUnits')
	}
}
</script>