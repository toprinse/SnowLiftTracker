import SensorTypeService from "@/services/SensorTypeService";

//Initial state
const state = {
	allSensorTypes: []
}

//Getters
const getters = {
	getAllSensorTypes: state => {
		return state.allSensorTypes;
	}
}

//Actions
const actions = {
	getSensorTypes ({ commit }) {
		SensorTypeService.getAll().then( response => {
			console.log(response.data);
			commit('setAllSensorTypes',response.data);
		});
	},
	
	createSensorType ({ commit }, sensorType) {
		SensorTypeService.create(sensorType).then( response => {
			commit('newSensorType', response.data);
		}).catch( error => console.log(error));
	},
	
	updateSensorType ({ commit }, sensorType) {
		SensorTypeService.update(sensorType).then( response => {
			commit('updateSensorType', response.data);
		});
	},
	
	deleteSensorType ({ commit }, id) {
		SensorTypeService.delete(id).then( () => {
			commit('deleteSensorType', id);
		});
	}
}

//Mutations
const mutations = {
	setAllSensorTypes (state, sensorTypes) {
		state.allSensorTypes = sensorTypes;
	},
	
	newSensorState (state, sensorType) {
		state.allSensorTypes.push(sensorType);
	},
	
	updateSensorType (state, sensorType) {
		state.allSensorTypes.splice(state.allSensorTypes.findIndex(st => st.id == sensorType.id), 1, sensorType);
	},
	
	deleteSensorType (state, id) {
		state.allSensorTypes.splice(state.allSensorTypes.findIndex(st => st.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}