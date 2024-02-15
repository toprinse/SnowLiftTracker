#include "QuarkusClient.h"

int main() {
    QuarkusClient client;

    const std::string get_url = "http://192.168.0.10:8887/";
    const std::string post_url = "http://192.168.0.10:8887/";
    const std::string station_json = R"({
        "id": "0",
        "name": "Echirolles",
        "longitude": "38",
        "latitude": "38"
    })";
    const std::string skilift_json = R"({
      "id": "0",
      "name": "LEBAT8",
      "longitude": "12345",
      "latitude": "67890",
      "open": "true",
      "stationId": "1"
    })";
    const std::string an_sensor_json = R"({
       "id": "0",
       "reference": "KHADRANI123",
       "manufacturer": "MOHAMED",
       "skiLiftId": "1"
   })";
    const std::string num_sensor_json = R"({
      "id": "0",
      "reference": "KHADRANI456",
      "manufacturer": "NASSIM",
      "skiLiftId": "1"
    })";
    const std::string an_measure_json = R"({
      "timestamp": "2023-12-27 19:07:43",
      "id": "0",
      "analogData": "3.1418",
      "sensorId": "1"
    })";
    const std::string num_measure_json = R"({
      "timestamp": "2023-12-27 19:11:43",
      "id": "0",
      "numData": "true",
      "sensorId": "151"
    })";

    // NEED TO UNCOMMENT THIS STEP FOR GET REQUEST
    // Perform GET request
    // std::string get_response = client.get(get_url);
    // std::cout << "GET Response: " << get_response << std::endl;

    // Perform POST request
    std::cout << "POST Station: " << std::endl;
    std::string post_response = client.post(post_url+"Station", station_json);
    std::cout << "POST Station Response: " << post_response << std::endl;
   usleep(1000000);
    std::cout << "POST SkiLift: " << std::endl;
    post_response = client.post(post_url+"PostInterface/skilift", skilift_json);
    std::cout << "POST SkiLift Response: " << post_response << std::endl;
 usleep(1000000);
    std::cout << "POST An sensor: " << std::endl;
    post_response = client.post(post_url+"PostInterface/analogsensor", an_sensor_json);
    std::cout << "POST An sensor Response: " << post_response << std::endl;
 usleep(1000000);
    std::cout << "POST Num sensor: " << std::endl;
    post_response = client.post(post_url+"PostInterface/numsensor", num_sensor_json);
    std::cout << "POST Num sensor Response: " << post_response << std::endl;
usleep(1000000);
    std::cout << "POST An measure: " << std::endl;
    post_response = client.post(post_url+"PostInterface/analogmeasure", an_measure_json);
    std::cout << "POST An measure Response: " << post_response << std::endl;
usleep(1000000);
    std::cout << "POST Num measure: " << std::endl;
    post_response = client.post(post_url+"PostInterface/nummeasure", num_measure_json);
    std::cout << "POST Num measure Response: " << post_response << std::endl;

    return 0;
}

