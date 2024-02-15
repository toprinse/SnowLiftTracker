import StationInterfaceService from "@/services/StationInterfaceService.js";

//Initial state
const state = {
	stationById: []
}

//Getters
const getters = {

	getStationInterfacesById_: state => {
		return state.stationById;
	}
}

//Actions
const actions = {
	getStationInterfacesById ({ commit }, id) {
		StationInterfaceService.get(id).then( response => {
			console.log(response.data);
			commit('setStationInterfacesById', response.data);
		});
	}
}

//Mutations
const mutations = {
	setStationInterfacesById (state, stationInterfaces) {
     state.stationById=stationInterfaces;
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}