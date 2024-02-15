import StationService from "@/services/StationService";

//Initial state
const state = {
	allStations: [],
	stationById: [],
	skilifts: []
}

//Getters
const getters = {
	getAllStations: state => {
		return state.allStations;
	},

	getStationById: state => {
		return state.stationById;
	},

	getStationSkiLift: state => {
		return state.skilifts;
	}

}

//Actions
const actions = {
	getStations ({ commit }) {
		StationService.getAll().then( response => {
			console.log(response.data);
			commit('setAllStations',response.data);
		});
	},

	getStationById ({ commit }, id) {
		StationService.get(id).then( response => {
			console.log(response.data);
			commit('setStationById',response.data);
		});
	},

	getSkiLifts({ commit }) {
		StationService.getSkiLifts().then( response => {
			console.log(response.data);
			commit('setSkiLifts',response.data);
		});
	},
	
	createStation ({ commit }, station) {
		StationService.create(station).then( response => {
			commit('newStation', response.data);
		}).catch( error => console.log(error));
	},
	
	updateStation ({ commit }, station) {
		StationService.update(station).then( response => {
			commit('updateStation', response.data);
		});
	},
	
	deleteStation ({ commit }, id) {
		StationService.delete(id).then( () => {
			commit('deleteStation', id);
		});
	}
}

//Mutations
const mutations = {
	setAllStations (state, stations) {
		state.allStations = stations;
	},

	setStationById (state, station) {
		state.stationById = station;
	},

	setSkiLifts (state, skilifts) {
		state.skilifts = skilifts;
	},
	
	newStation (state, station) {
		state.allStations.push(station);
	},
	
	updateStation (state, station) {
		state.allStations.splice(state.allStations.findIndex(s => s.id == station.id), 1, station);
	},
	
	deleteStation (state, id) {
		state.allStations.splice(state.allStations.findIndex(s => s.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}