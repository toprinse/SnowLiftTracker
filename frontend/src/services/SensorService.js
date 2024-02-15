import http from '../../http-common'

class SensorService {
	
	getAll() {
		return http.get("/Sensor");
	}

	get(id) {
		return http.get(`/Sensor/${id}`);
	}

	getAllAnalog() {
		return http.get("/Sensor/analog?withMeasure=true&withSkiLift=true&withType=true&withUnit=true");
	}

	getAllNum() {
		return http.get("/Sensor/num?withMeasure=true&withSkiLift=true&withState=true&withType=true");
	}
	
	getAnalogTypes(){
		return http.get("/Sensor/analog?withMeasure=false&withSkiLift=false&withType=true&withUnit=false");
	}
	//Should add bool variable to choose "WithMeasure" true or false
	getAnalogWithMeasure(id){
		return http.get(`/Sensor/analog/${id}?withMeasure=true`)
	}

	create(data) {
		return http.post("/Sensor",data);
	}
	
	update(data) {
		return http.put("/Sensor",data);
	}
	
	delete(id) {
		return http.delete(`/Sensor/${id}`);
	}
	
}

export default new SensorService();