<template>
	<v-card>
		<v-card-title>
			<h2>Les sensors</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="sensors">
			
			</v-data-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListSensors",
	data() {
		return {
			sensors: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Manufacturer", sortable: true, value: "manufacturer"},
				{ text: "Reference", sortable: true, value: "reference"}
			]
		}
	},
	computed: {
		sensors1() {
			return this.$store.getters['sensors/getAllSensors'];
		}
	},
	watch: {
		sensors1: function(sensors1) {
			this.sensors = sensors1;
		}
	},
	mounted() {
		this.$store.dispatch('sensors/getSensors')
	}
}
</script>