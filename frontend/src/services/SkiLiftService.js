import http from '../../http-common'

class SkiLiftService {
	
	getAll() {
		return http.get("/SkiLift");
	}
	
	get(id) {
		return http.get(`/SkiLift/${id}`);
	}
	
	create(data) {
		return http.post("/SkiLift",data);
	}
	
	update(data) {
		return http.put("/SkiLift",data);
	}
	
	delete(id) {
		return http.delete(`/SkiLift/Recursive/${id}`);
	}

	//POST INTERFACE -> Create with station Id
	createWithStation(data){
		return http.post("PostInterface/skilift",data);
	}
	
}

export default new SkiLiftService();