<template>
	<v-card>
		<v-card-title>
			<h2>Les sensorTypes</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="sensorTypes">
			
			</v-data-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListSensorTypes",
	data() {
		return {
			sensorTypes: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Type", sortable: true, value: "type"}
			]
		}
	},
	computed: {
		sensorTypes1() {
			return this.$store.getters['sensorTypes/getAllSensorTypes'];
		}
	},
	watch: {
		sensorTypes1: function(sensorTypes1) {
			this.sensorTypes = sensorTypes1;
		}
	},
	mounted() {
		this.$store.dispatch('sensorTypes/getSensorTypes')
	}
}
</script>