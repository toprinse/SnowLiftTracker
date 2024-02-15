import http from '../../http-common'

class SkiLiftInterfaceService {

	/**
	 * Get every analogmeasure from a skilift choosen by `id` 
	 * @param {*} id : skilift id
     * @param {*} id2 : sensor id
	 * @returns 
	 */
	get(id,id2) {
		return http.get(`/skiliftinterface/${id}/analogsensor/${id2}/analogmeasure`);
	}
}

export default new SkiLiftInterfaceService();