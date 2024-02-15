import http from '../../http-common'

class StationService {
	
	getAll() {
		return http.get("/Station");
	}
	
	get(id) {
		return http.get(`/Station/${id}`);
	}

	getSkiLifts() {
		return http.get("/Station?withSkiLifts=true");
	}
	
	create(data) {
		return http.post("/Station",data);
	}
	
	update(data) {
		return http.put("/Station",data);
	}
	
	delete(id) {
		return http.delete(`/Station/Recursive/${id}`);
	}
	
}

export default new StationService();