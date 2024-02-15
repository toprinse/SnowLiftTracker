<template>
	<v-card>
		<v-card-title>
			<h2> {{ station.name }} : Liste des remontées </h2>
		</v-card-title>
		<v-card-text>
			<v-simple-table> 
					<thead>
						<tr>
							<th>Nom</th>
							<th>Latitude</th>
							<th>Longitude</th>
							<th>Open</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="item in stationInterfaces" :key="item.id" @click="GoToSkiLiftInterface(item.id)">
							<td>{{ item.name }}</td>
							<td>{{ item.latitude }}</td>
							<td>{{ item.longitude }}</td>
							<td :style="{ color: item.open ? 'green' : 'red' }">{{ item.open ? '✔' : '✘' }}</td>
						</tr>
					</tbody>
			</v-simple-table>
		</v-card-text>
	</v-card>
</template>



<script>

export default {
	name: "ListStationInterfaces",
	props: {
		id: String,
	},
	
	data() {
		return {
			station: [],
			stationInterfaces: [],
			headers: [
				{ text: "Id", sortable: true, value: "id"},
				{ text: "Nom", sortable: true, value: "name"},
				{ text: "Latitude", sortable: true, value: "latitude"},
				{ text: "Longitude", sortable: true, value: "longitude"},
				{ text: "Open", sortable: true, value: "open"}
			]
		}
	},

	methods: {
		//Go to station interface linked by the id
		GoToSkiLiftInterface (id) {
		console.log(id);
        this.$router.push(`/skiliftinterface/${id}`);
      }
	},

	computed: {
		station1() {
			return this.$store.getters['stations/getStationById']
		},
		stationInterfaces1() {
			return this.$store.getters['stationInterfaces/getStationInterfacesById_'];
		}
	},
	watch: {
		station1: function(station1) {
			this.station = station1;
		},
		stationInterfaces1: function(stationInterfaces1) {
			this.stationInterfaces = stationInterfaces1;
		}
	},
	mounted() {
		this.$store.dispatch('stationInterfaces/getStationInterfacesById', this.id)
		console.log(this.id);

		this.$store.dispatch('stations/getStationById', this.id)
		
	}
}
</script>

<style>
.greenOpen{
	background-color: green;
}
.redOpen{
	background-color: red;
}
</style>