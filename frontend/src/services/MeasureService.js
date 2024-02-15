import http from '../../http-common'

class MeasureService {
	
	getAll() {
		return http.get("/Measure");
	}

	getAllNum(bool) {
		return http.get(`/Measure/num?withSensor=${bool}`);
	}

	getAllAnalog(bool) {
		return http.get(`/Measure/analog?withSensor=${bool}`);
	}
	
	get(id) {
		return http.get(`/Measure/${id}`);
	}
	
	create(data) {
		return http.post("/Measure",data);
	}
	
	update(data) {
		return http.put("/Measure",data);
	}
	
	delete(id) {
		return http.delete(`/Measure/${id}`);
	}
	
}

export default new MeasureService();