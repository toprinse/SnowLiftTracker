import SensorService from "@/services/SensorService";

//Initial state
const state = {
	allSensors: [],
	allSensorsAnalog: [],
	allSensorsNum: [],
	sensorAnalog: [],
	analogTypes: []
}

//Getters
const getters = {
	getAllSensors: state => {
		return state.allSensors;
	},

	getAllSensorsAnalog: state => {
		return state.allSensorsAnalog;
	},

	getAllSensorsNum: state => {
		return state.allSensorsNum;
	},
	
	getSensorAnalog_: state => {
		return state.sensorAnalog;
	},

	getAnalogTypes: state => {
		return state.analogTypes
	}
}

//Actions
const actions = {
	getSensors ({ commit }) {
		SensorService.getAll().then( response => {
			console.log(response.data);
			commit('setAllSensors',response.data);
		});
	},

	getSensorsAnalog ({ commit }) {
		SensorService.getAllAnalog().then( response => {
			//console.log(response.data);
			commit('setAllSensorsAnalog',response.data);
		});
	},

	getSensorsNum ({ commit }) {
		SensorService.getAllNum().then( response => {
			//console.log(response.data);
			commit('setAllSensorsNum',response.data);
		});
	},

	getSensorAnalog ({ commit }, id) {
		SensorService.getAnalogWithMeasure(id).then( response => {
			//console.log(response.data);
			commit('setSensorAnalog',response.data);
		});
	},

	getAnalogTypes_({ commit }) {
		SensorService.getAnalogTypes().then( response => {
			//console.log(response.data);
			commit('setAnalogTypes',response.data);
		});
	},
	
	createSensor ({ commit }, sensor) {
		SensorService.create(sensor).then( response => {
			commit('newSensor', response.data);
		}).catch( error => console.log(error));
	},
	
	updateSensor ({ commit }, sensor) {
		SensorService.update(sensor).then( response => {
			commit('updateSensor', response.data);
		});
	},
	
	deleteSensor ({ commit }, id) {
		SensorService.delete(id).then( () => {
			commit('deleteSensor', id);
		});
	}
}

//Mutations
const mutations = {
	setAllSensors (state, sensors) {
		state.allSensors = sensors;
	},

	setAllSensorsAnalog (state, sensors) {
		state.allSensorsAnalog = sensors;
	},

	setAllSensorsNum (state, sensors) {
		state.allSensorsNum = sensors;
	},

	setSensorAnalog (state, sensor) {
		state.sensorAnalog = sensor;
	},

	setAnalogTypes (state, sensors) {
		state.analogTypes = sensors;
	},
	
	newSensor (state, sensor) {
		state.allSensors.push(sensor);
	},
	
	updateSensor (state, sensor) {
		state.allSensors.splice(state.allSensors.findIndex(s => s.id == sensor.id), 1, sensor);
	},
	
	deleteSensor (state, id) {
		state.allSensors.splice(state.allSensors.findIndex(s => s.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}