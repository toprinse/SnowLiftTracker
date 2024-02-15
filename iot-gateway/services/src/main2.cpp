#include "Subscriber.h"
#include "QuarkusClient.h"
#include "JsonFormatter.h"

#include <iostream>
#include <thread>
#include <atomic> 
#include <iomanip>
#include <chrono>
#include <ctime>
#include <czmq.h>

#define PROXY_PUBLISHER_ADDRESS "ipc://backend"
#define TOPIC "raw"
#define QUARKUS_URL "http://192.168.0.10:8887/"
//#define QUARKUS_URL "http://192.168.0.107:8887/"
#define STATION_NAME "chamrousse"
#define SKILIFT_NAME "TC de la Croix"

using namespace std;

class QuarkusConnector {
  public:
    QuarkusConnector(){};

    ~QuarkusConnector(){
      stop();
    };

    void start(){
      int rc = 0;
      string response;

      rc = sub.Connect(PROXY_PUBLISHER_ADDRESS);
      if(rc == -1 ) return;
      rc = sub.Subscribe(TOPIC);
      if(rc == -1 ) return;

      // Logic before entering a loop: 
      // If the station exist we do nothing else we post it
      // Same for ski lift
      // * We should create a function that assert a good request in QuarkusClient

      // response = client.get(static_cast<std::string>(QUARKUS_URL)+"Station/c/{name}");
      // // check response
      // if(response.find(STATION_NAME) == std::string::npos){
      //   response = client.post(static_cast<std::string>(QUARKUS_URL)+"Station/", station_json);
      //   // check response
      // }

      // response = client.get(static_cast<std::string>(QUARKUS_URL)+"SkiLift/c/{name}");
      // // check reponse
      // if(response.find(SKILIFT_NAME) == std::string::npos){
      //   response = client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/skilift", skilift_json);
      //   // check response
      // }

      // For test:
      client.post(static_cast<std::string>(QUARKUS_URL)+"Station", station_json);
      client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/skilift", skilift_json);
      client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/analogsensor", an_sensor1_json);
      client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/analogsensor", an_sensor2_json);
      client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/analogsensor", an_sensor3_json);
      client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/numsensor", num_sensor_json);


      QuarkusConnectorThread = std::thread(&QuarkusConnector::runThread, this);
      if(QuarkusConnectorThread.joinable()){
        running = true;
      }
    }

    void runThread(){
      string data = "";
      int rc = 0;
while(!zsys_interrupted){
      rc = sub.ReceiveBlock(data);
      if(rc != 0) printf("[INFO] ReceiveBlock : %s\n", strerror(rc));
printf("received something ! \n");
      if(data.find("Presence") == std::string::npos){
string json = MeasureStringToJson(data);
printf("json data = %s\n", json.c_str()); 
        string rep = client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/analogmeasure", json);
printf("Posted an analog measure \n");
      }
      else{
        client.post(static_cast<std::string>(QUARKUS_URL)+"PostInterface/nummeasure", MeasureStringToJson(data));
      }
data.clear();
}
    }

    void stop() {
      if (QuarkusConnectorThread.joinable() && running) {
          QuarkusConnectorThread.join();
          running = false;
      }
      std::cout << "Quarkus connector thread stopped." << std::endl;
    }

    
  private:
    std::atomic<bool> running; // Atomic flag to control the UART thread
    std::thread QuarkusConnectorThread;

    QuarkusClient client;
    Subscriber sub={"Connector"};

    // const JSON declaration :

    // Station
    const std::string station_json = R"({
        "id": "0",
        "name": "Courchevel",
        "longitude": "45.40991987885902",
        "latitude": "6.631974610752491"
    })";
    // SkiLift
    const std::string skilift_json = R"({
      "id": "0",
      "name": "Grangette",
      "longitude": "45.42042878584228",
      "latitude": "6.6324483777399",
      "open": "true",
      "stationId": "101"
    })";
    // Presence  id 554
    const std::string num_sensor_json = R"({
      "id": "10",
      "reference": "BP-USER",
      "manufacturer": "ST",
      "skiLiftId": "251"
    })";
    // Humidity id 551
    const std::string an_sensor1_json = R"({ 
       "id": "8",
       "reference": "HTS221-H",
       "manufacturer": "ST",
       "skiLiftId": "251",
       "sensorTypeId": "1"
   })";
    // Pressure id 552
    const std::string an_sensor2_json = R"({
       "id": "5",
       "reference": "LPS22HB",
       "manufacturer": "ST",
       "skiLiftId": "251",
       "sensorTypeId": "101"
   })";
    // Temperature 553
    const std::string an_sensor3_json = R"({
       "id": "9",
       "reference": "HTS221-T",
       "manufacturer": "ST",
       "skiLiftId": "251",
       "sensorTypeId": "51"
   })";

    // String format "Sensor|Lift|StationId|Type|Value"
    std::string MeasureStringToJson(const std::string data){
      JsonFormatter formatter;
      char delimiter = '|';

      std::vector<std::string> fields = splitString(data, delimiter);

      // declare const char* because templated function doesn't support well other call like std::string::c_str() 
      // and we don't have time to fix this now
      const std::string& strdate = getCurrentDateTimeString();
      const char* date = strdate.c_str();
      const char* analogData = fields[4].c_str();
      const char* sensorId;
switch(std::stoi(fields[0])){
case 5:
	sensorId = "552";
	break;
case 8:
	sensorId = "551";
	break;
case 9: 
	sensorId = "553";
	break;
case 10:
	sensorId = "554";
	break;
}
      formatter.addField("timestamp", date);
      formatter.addField("id", "0");
      fields[3]=="Presence" ? 
      formatter.addField("numData", "true") : formatter.addField("analogData", analogData);
      formatter.addField("sensorId", sensorId);

      return formatter.toString();
    }

    std::string getCurrentDateTimeString() {
      // Get the current time point
      std::chrono::system_clock::time_point now = std::chrono::system_clock::now();

      // Convert the time point to std::time_t
      std::time_t currentTime = std::chrono::system_clock::to_time_t(now);

      // Convert std::time_t to std::tm (for formatting)
      std::tm* timeInfo = std::localtime(&currentTime);

      // Format the date and time
      std::ostringstream oss;
      oss << std::put_time(timeInfo, "%Y-%m-%d %H:%M:%S");

      return oss.str();
    }

    std::vector<std::string> splitString(const std::string& input, char delimiter) {
      std::vector<std::string> result;
      std::istringstream stream(input);
      std::string token;

      while (std::getline(stream, token, delimiter)) {
          result.push_back(token);
      }

      return result;
    }
};


int main() {
  QuarkusConnector quarkusConnector;

  quarkusConnector.start();

  quarkusConnector.stop();

  return 0;
}

