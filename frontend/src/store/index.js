import Vue from 'vue'
import Vuex from 'vuex'

import stations from './modules/stations'
import analogSensorUnits from './modules/analogSensorUnits'
import measures from './modules/measures'
import numSensorStates from './modules/numSensorStates'
import sensors from './modules/sensors'
import sensorTypes from './modules/sensorTypes'
import skiLifts from './modules/skiLifts'
import stationInterfaces from './modules/stationInterface'
import users from './modules/users'
import skiLiftInterface from './modules/skiLiftInterface'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
    username: "",
    isAuthenticated: false,
    givenName: "",
    familyName: "",
    email: "",
    adminRole: false,
    idToken: "",
    accessToken: ""
    }
  },

  getters: {
  },

  mutations: {
    login ( state, payload ) {
    state.user.isAuthenticated = true;
    state.user.idToken = payload.idToken;
    state.user.accessToken = payload.accessToken;
    },
    logout ( state ) {
    state.user.user = "";
    state.user.isAuthenticated = false;
    state.user.givenName = "";
    state.user.familyName = "";
    state.user.email = "";
    state.user.adminRole = false;
    state.user.idToken = "";
    state.user.accessToken = "";
    },
    setName ( state, payload ) {
    state.user.username = payload.username;
    state.user.givenName = payload.givenName;
    state.user.familyName = payload.familyName;
    state.user.email = payload.email;
    state.user.adminRole = payload.adminRole;
    }
  },

  actions: {
  },

  modules: {
    stations,
    analogSensorUnits,
    measures,
    numSensorStates,
    sensors,
    sensorTypes,
    skiLifts,
    stationInterfaces,
    users,
    skiLiftInterface
  }
})
