import MeasureService from "@/services/MeasureService";

//Initial state
const state = {
	allMeasures: [],

	allMeasuresNum: [],

	allMeasuresAnalog: []
}

//Getters
const getters = {
	getAllMeasures: state => {
		return state.allMeasures;
	},

	getAllMeasuresNum: state => {
		return state.allMeasuresNum;
	},

	getAllMeasuresAnalog: state => {
		return state.allMeasuresAnalog;
	}

}

//Actions
const actions = {
	getMeasures ({ commit }) {
		MeasureService.getAll().then( response => {
			console.log(response.data);
			commit('setAllMeasures',response.data);
		});
	},
	//bool: With Sensor or not (true or false)
	getMeasuresNum ({ commit }, bool) {
		MeasureService.getAllNum(bool).then( response => {
			console.log(response.data);
			commit('setAllMeasuresNum',response.data);
		})
	},
	//bool: With Sensor or not (true or false)
	getMeasuresAnalog ({ commit }, bool) {
		MeasureService.getAllAnalog(bool).then( response => {
			console.log(response.data);
			commit('setAllMeasuresAnalog',response.data);
		})
	},
	
	createMeasure ({ commit }, measure) {
		MeasureService.create(measure).then( response => {
			commit('newMeasure', response.data);
		}).catch( error => console.log(error));
	},
	
	updateMeasure ({ commit }, measure) {
		MeasureService.update(measure).then( response => {
			commit('updateMeasure', response.data);
		});
	},
	
	deleteMeasure ({ commit }, id) {
		MeasureService.delete(id).then( () => {
			commit('deleteMeasure', id);
		});
	}
}

//Mutations
const mutations = {
	setAllMeasures (state, measures) {
		state.allMeasures = measures;
	},

	setAllMeasuresNum (state, measures) {
		state.allMeasuresNum = measures;
	},

	setAllMeasuresAnalog (state, measures) {
		state.allMeasuresAnalog = measures;
	},
	
	newMeasure (state, measure) {
		state.allMeasures.push(measure);
	},
	
	updateMeasure (state, measure) {
		state.allMeasures.splice(state.allMeasures.findIndex(m => m.id == measure.id), 1, measure);
	},
	
	deleteMeasure (state, id) {
		state.allMeasures.splice(state.allMeasures.findIndex(m => m.id == id), 1);
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}