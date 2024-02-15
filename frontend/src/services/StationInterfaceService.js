import http from '../../http-common'

class StationInterfaceService {

	/**
	 * Get every skilifts from a station choosen by `id` 
	 * @param {*} id : station id
	 * @returns 
	 */
	get(id) {
		return http.get(`/stationinterface/${id}/remontee`);
	}
}

export default new StationInterfaceService();