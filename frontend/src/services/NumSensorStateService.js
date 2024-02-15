import http from '../../http-common'

class NumSensorStateService {
	
	getAll() {
		return http.get("/NumSensorState");
	}
	
	get(id) {
		return http.get(`/NumSensorState/${id}`);
	}
	
	create(data) {
		return http.post("/NumSensorState",data);
	}
	
	update(data) {
		return http.put("/NumSensorState",data);
	}
	
	delete(id) {
		return http.delete(`/NumSensorState/${id}`);
	}
	
}

export default new NumSensorStateService();