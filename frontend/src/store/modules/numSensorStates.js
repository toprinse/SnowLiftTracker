import NumSensorStateService from "@/services/NumSensorStateService";

//Initial state
const state = {
	allNumSensorStates: []
}

//Getters
const getters = {
	getAllNumSensorStates: state => {
		return state.allNumSensorStates;
	}
}

//Actions
const actions = {
	getNumSensorStates ({ commit }) {
		NumSensorStateService.getAll().then( response => {
			console.log(response.data);
			commit('setAllNumSensorStates',response.data);
		});
	},
	
	createNumSensorState ({ commit }, numSensorState) {
		NumSensorStateService.create(numSensorState).then( response => {
			commit('newSensorState', response.data);
		}).catch( error => console.log(error));
	},
	
	updateNumSensorState ({ commit }, numSensorState) {
		NumSensorStateService.update(numSensorState).then( response => {
			commit('updateNumSensorState', response.data);
		});
	},
	
	deleteNumSensorState ({ commit }, id) {
		NumSensorStateService.delete(id).then( () => {
			commit('deleteNumSensorState', id);
		});
	}
}

//Mutations
const mutations = {
	setAllNumSensorStates (state, numSensorStates) {
		state.allNumSensorStates = numSensorStates;
	},
	
	newNumSensorState (state, numSensorState) {
		state.allNumSensorStates.push(numSensorState);
	},
	
	updateNumSensorState (state, numSensorState) {
		state.allNumSensorStates.splice(state.allNumSensorStates.findIndex(nst => nst.id == numSensorState.id), 1, numSensorState);
	},
	
	deleteNumSensorState (state, id) {
		state.allNumSensorStates.splice(state.allNumSensorStates.findIndex(nst => nst.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}