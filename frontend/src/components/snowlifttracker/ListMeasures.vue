<template>
	<v-card>
		<v-card-title>
			<h2>Les measures</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="measures">
			
			</v-data-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListMeasures",
	data() {
		return {
			measures: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Timestamp", sortable: true, value: "timestamp"},
				{ text: "Analog Data", sortable: true, value: "analogData"},
				{ text: "Numeric Data", sortable: true, value: "numericData"}
			]
		}
	},
	computed: {
		measures1() {
			return this.$store.getters['measures/getAllMeasures'];
		}
	},
	watch: {
		measures1: function(measures1) {
			this.measures = measures1;
		}
	},
	mounted() {
		this.$store.dispatch('measures/getMeasures')
	}
}
</script>