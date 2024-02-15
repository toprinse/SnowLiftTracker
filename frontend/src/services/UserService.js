import http from '../../http-common'

class UserService {
	
	getAll() {
		return http.get("/UserSnow");
	}
	
	get(id) {
		return http.get(`/UserSnow/${id}`);
	}
	
	create(data) {
		return http.post("/UserSnow",data);
	}
	
	update(data) {
		return http.put("/UserSnow",data);
	}
	
	delete(id) {
		return http.delete(`/UserSnow/${id}`);
	}
	
}

export default new UserService();