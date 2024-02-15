import http from '../../http-common'

class AnalogSensorUnitService {
	
	getAll() {
		return http.get("/AnalogSensorUnit");
	}
	
	get(id) {
		return http.get(`/AnalogSensorUnit/${id}`);
	}
	
	create(data) {
		return http.post("/AnalogSensorUnit",data);
	}
	
	update(data) {
		return http.put("/AnalogSensorUnit",data);
	}
	
	delete(id) {
		return http.delete(`/AnalogSensorUnit/${id}`);
	}
	
}

export default new AnalogSensorUnitService();