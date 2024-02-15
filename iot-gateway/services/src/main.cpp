#include "Publisher.h"
#include "uart.h"

#include <iostream>
#include <thread>
#include <atomic> 
#include <czmq.h>

#define PROXY_SUBSCRIBER_ADDRESS "ipc://frontend"
#define TOPIC "raw"
#define ID 9999

using namespace std;

class UartListener {

public:
    UartListener(){};

    ~UartListener(){
      stop();
    };

    void start(){
      int rc = 0;
      rc = uart.Init();
      if(rc == -1 ) return;
      rc = pub.Bind(PROXY_SUBSCRIBER_ADDRESS, true);
      if(rc == -1 ) return;

      uartThread = std::thread(&UartListener::runUartThread, this);
      if(uartThread.joinable()){
        running = true;
      }
    }

    void runUartThread(){
      int rc = 0;
      Uart::uart_packet_t recv_packet = {{0, 0, 0, 0, 0}, {0}, 0};
while(!zsys_interrupted){
      do{
        rc = uart.Receive(recv_packet);
        usleep(1000000);
      }while(rc != 0);
printf("1\n");
      std::string data = PacketToString(recv_packet);
      printf("data : %s\n", data.c_str());
printf("To send on topic %s ...\n", TOPIC);
rc = pub.Publish(TOPIC, data); // wewill miss the first sent value
printf("rc = %i\n");
}
    }

    void stop() {
      if (uartThread.joinable() && running) {
          uartThread.join();
          running = false;
      }
      std::cout << "UART thread stopped." << std::endl;
    }

    
private:
    std::atomic<bool> running; // Atomic flag to control the UART thread
    std::thread uartThread;

    Uart uart={"Listener"};
    Publisher pub={"Listener"};
    
    // String format "Sensor|Lift|StationId|Type|Value"
    std::string PacketToString( Uart::uart_packet_t packet){
      std::string str;
      const std::string separator = "|";
      
      str = std::to_string(packet.uart_header.sensorId);
      str += separator;
      str += std::to_string(packet.uart_header.liftId);
      str += separator;
      str += std::to_string(ID);
      str += separator;
      switch(packet.uart_header.type){
        case 0x73:
          str += "Temperature";
          break;
        case 0x74:
          str += "Humidity";
          break;
        case 0x75:
          str += "Pressure";
          break;
        case 0x76:
          str += "Presence";
          break;
      }
      str += separator;
      switch(packet.uart_header.size){
        case 0x04 :  // Float
        {
          str += std::to_string(packet.uart_data.float_data);
          break;
        }
        case 0x02:  // Int
        {
          str += std::to_string(packet.uart_data.int_data);
          break;
        }
        case 0x01:  // Bool
        {
          str += std::to_string(packet.uart_data.bool_data);
          break;
        }
	    } 

      return str;
    }

};


int main() {
    UartListener uartListener;
    // Start the UART thread
    uartListener.start();
    // Stop the UART thread
    uartListener.stop();

  return 0;
}

