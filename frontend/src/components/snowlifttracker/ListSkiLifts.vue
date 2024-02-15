<template>
	<v-card>
		<v-card-title>
			<h2>Les skiLifts</h2>
		</v-card-title>
		<v-card-text>
			<v-data-table :headers="headers" :items="skiLifts">
			
			</v-data-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListSkiLifts",
	data() {
		return {
			skiLifts: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Nom", sortable: true, value: "name"},
				{ text: "Latitude", sortable: true, value: "latitude"},
				{ text: "Longitude", sortable: true, value: "longitude"}
			]
		}
	},
	computed: {
		skiLifts1() {
			return this.$store.getters['skiLifts/getAllSkiLifts'];
		}
	},
	watch: {
		skiLifts1: function(skiLifts1) {
			this.skiLifts = skiLifts1;
		}
	},
	mounted() {
		this.$store.dispatch('skiLifts/getSkiLifts')
	}
}
</script>