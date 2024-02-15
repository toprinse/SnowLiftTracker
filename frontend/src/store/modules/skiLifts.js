import SkiLiftService from "@/services/SkiLiftService";

//Initial state
const state = {
	allSkiLifts: []
}

//Getters
const getters = {
	getAllSkiLifts: state => {
		return state.allSkiLifts;
	}
}

//Actions
const actions = {
	getSkiLifts ({ commit }) {
		SkiLiftService.getAll().then( response => {
			console.log(response.data);
			commit('setAllSkiLifts',response.data);
		});
	},
	
	createSkiLift ({ commit }, skiLift) {
		SkiLiftService.create(skiLift).then( response => {
			commit('newSkiLift', response.data);
		}).catch( error => console.log(error));
	},

	createSkiLiftInStation ({ commit }, skiLift) {
		SkiLiftService.createWithStation(skiLift).then( response => {
			commit('newSkiLift', response.data);
		}).catch( error => console.log(error));
	},
	updateSkiLift ({ commit }, skiLift) {
		SkiLiftService.update(skiLift).then( response => {
			commit('updateSkiLift', response.data);
		});
	},
	
	deleteSkiLift ({ commit }, id) {
		SkiLiftService.delete(id).then( () => {
			commit('deleteSkiLift', id);
		});
	}
}

//Mutations
const mutations = {
	setAllSkiLifts (state, skiLifts) {
		state.allSkiLifts = skiLifts;
	},
	
	newSkiLift (state, skiLift) {
		state.allSkiLifts.push(skiLift);
	},
	
	updateSkiLift (state, skiLift) {
		state.allSkiLifts.splice(state.allSkiLifts.findIndex(sl => sl.id == skiLift.id), 1, skiLift);
	},
	
	deleteSkiLift (state, id) {
		state.allSkiLifts.splice(state.allSkiLifts.findIndex(sl => sl.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}