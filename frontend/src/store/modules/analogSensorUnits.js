import AnalogSensorUnitService from "@/services/AnalogSensorUnitService";

//Initial state
const state = {
	allAnalogSensorUnits: []
}

//Getters
const getters = {
	getAllAnalogSensorUnits: state => {
		return state.allAnalogSensorUnits;
	}
}

//Actions
const actions = {
	getAnalogSensorUnits ({ commit }) {
		AnalogSensorUnitService.getAll().then( response => {
			console.log(response.data);
			commit('setAllAnalogSensorUnits',response.data);
		});
	},
	
	createAnalogSensorUnit ({ commit }, analogSensorUnit) {
		AnalogSensorUnitService.create(analogSensorUnit).then( response => {
			commit('newAnalogSensorUnit', response.data);
		}).catch( error => console.log(error));
	},
	
	updateAnalogSensorUnit ({ commit }, analogSensorUnit) {
		AnalogSensorUnitService.update(analogSensorUnit).then( response => {
			commit('updateAnalogSensorUnit', response.data);
		});
	},
	
	deleteAnalogSensorUnit ({ commit }, id) {
		AnalogSensorUnitService.delete(id).then( () => {
			commit('deleteAnalogSensorUnit', id);
		});
	}
}

//Mutations
const mutations = {
	setAllAnalogSensorUnits (state, analogSensorUnits) {
		state.allAnalogSensorUnits = analogSensorUnits;
	},
	
	newAnalogSensorUnit (state, analogSensorUnit) {
		state.allAnalogSensorUnits.push(analogSensorUnit);
	},
	
	updateAnalogSensorUnit (state, analogSensorUnit) {
		state.allAnalogSensorUnits.splice(state.allAnalogSensorUnits.findIndex(asu => asu.id == analogSensorUnit.id), 1, analogSensorUnit);
	},
	
	deleteAnalogSensorUnit (state, id) {
		state.allAnalogSensorUnits.splice(state.allAnalogSensorUnits.findIndex(asu => asu.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}