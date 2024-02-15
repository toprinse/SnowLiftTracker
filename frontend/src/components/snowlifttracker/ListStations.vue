<template>
	<v-card>
		<v-card-title>
			<h2>Les Stations</h2>
		</v-card-title>
		<v-card-text>
			<v-simple-table> 
					<thead>
						<tr>
							<th>Nom</th>
							<th>Latitude</th>
							<th>Longitude</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="item in stations" :key="item.id" @click="GoToStationInterface(item.id)">
							<td>{{ item.name }}</td>
							<td>{{ item.latitude }}</td>
							<td>{{ item.longitude }}</td>
						</tr>
					</tbody>
			</v-simple-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListStations",
	data() {
		return {
			stations: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Nom", sortable: true, value: "name"},
				{ text: "Latitude", sortable: true, value: "latitude"},
				{ text: "Longitude", sortable: true, value: "longitude"}
			]
		}
	},
	methods: {
		//Go to station interface linked by the id
		GoToStationInterface (id) {
		console.log(id);
        this.$router.push(`/stationinterface/${id}`);
      }
	},
	computed: {
		stations1() {
			return this.$store.getters['stations/getAllStations'];
		}
	},
	watch: {
		stations1: function(stations1) {
			this.stations = stations1;
		}
	},
	mounted() {
		this.$store.dispatch('stations/getStations')
	}
}
</script>