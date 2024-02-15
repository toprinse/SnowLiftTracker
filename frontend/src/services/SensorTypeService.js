import http from '../../http-common'

class SensorTypeService {
	
	getAll() {
		return http.get("/SensorType");
	}
	
	get(id) {
		return http.get(`/SensorType/${id}`);
	}
	
	create(data) {
		return http.post("/SensorType",data);
	}
	
	update(data) {
		return http.put("/SensorType",data);
	}
	
	delete(id) {
		return http.delete(`/SensorType/${id}`);
	}
	
}

export default new SensorTypeService();