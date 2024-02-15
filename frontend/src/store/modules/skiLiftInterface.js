import SkiLiftInterfaceService from "@/services/SkiLiftInterfaceService";

//Initial state
const state = {
	skiliftById: []
}

//Getters
const getters = {

	getSkiLiftInterfacesById_: state => {
		return state.skiliftById;
	}
}

//Actions
const actions = {
	getSkiLiftInterfacesById ({ commit }, payload) {
		console.log("================= id2 = ===============")
		console.log(payload.id2);
		SkiLiftInterfaceService.get(payload.id1, payload.id2).then( response => {

			commit('setSkiLiftInterfacesById', response.data);
		});
	}
}

//Mutations
const mutations = {
	setSkiLiftInterfacesById (state, skiliftInterfaces) {
     state.skiliftById=skiliftInterfaces;
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}