import UserService from "@/services/UserService";

//Initial state
const state = {
	allUsers: []
}

//Getters
const getters = {
	getAllUsers: state => {
		return state.allUsers;
	}
}

//Actions
const actions = {
	getUsers ({ commit }) {
		UserService.getAll().then( response => {
			console.log(response.data);
			commit('setAllUsers',response.data);
		});
	},
	
	createUser ({ commit }, user) {
		UserService.create(user).then( response => {
			commit('newUser', response.data);
		}).catch( error => console.log(error));
	},
	
	updateUser ({ commit }, user) {
		UserService.update(user).then( response => {
			commit('updateUser', response.data);
		});
	},
	
	deleteUser ({ commit }, id) {
		UserService.delete(id).then( () => {
			commit('deleteUser', id);
		});
	}
}

//Mutations
const mutations = {
	setAllUsers (state, users) {
		state.allUsers = users;
	},
	
	newUser (state, user) {
		state.allUsers.push(user);
	},
	
	updateUser (state, user) {
		state.allUsers.splice(state.allUsers.findIndex(u => u.id == user.id), 1, user);
	},
	
	deleteUser (state, id) {
		state.allUsers.splice(state.allUsers.findIndex(u => u.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}