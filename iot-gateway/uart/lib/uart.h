#ifndef UART_H
#define UART_H

#include <stdio.h>
#include <unistd.h>                     //Used for UART
#include <fcntl.h>                      //Used for UART
#include <termios.h> 
#include <cstring>
#include <thread>
#include <mutex>
#include <atomic>

#define UART0_FILESTREAM -1
#define UART0_PORT "/dev/ttyS0"

using namespace std;

class Uart{
    
public:

    Uart(void)= delete;
    Uart(std::string identity);
    virtual ~Uart();
    Uart& operator=(Uart&) = delete;

    //Better data struct
    struct uart_packet_t {
        struct uart_header{
            uint8_t start;
            uint8_t type;
            uint8_t size;
	        uint8_t liftId;
	        uint8_t stationId;
            uint8_t sensorId;
            uint8_t end;
        }uart_header;
        union uart_data{ 
            uint16_t int_data;
            float float_data;
            uint8_t bool_data;
        }uart_data;
        uint8_t end;
    };

    int Init(void);
    int Close(void);
    int Send(void);
    int Receive(uart_packet_t &data);

private: 
    // functions
    int Decode(unsigned char* buf, uart_packet_t &data); //input to see
    int Encode(char* buf); //input to see

    // attributes
    int uart0_filestream;
    std::string identity;

    // inlines functions
    inline static int send_string(char* tx_string, const int &filestream){
        int size = -1;
        if (filestream != -1){
            size = write(filestream,tx_string,strlen(tx_string));
        }
        return size;
    }


    void print_formatted_data(Uart::uart_packet_t &packet){
        printf("\n============== DECODED DATA ====================\n");
        printf("HEADER: | Start : %02X | Station ID : %02X | Lift ID : %02X | Type: %02X | Sensor ID : %02X | Size: %02X| End: %02X |\n", packet.uart_header.start,packet.uart_header.stationId, packet.uart_header.liftId,packet.uart_header.type,packet.uart_header.sensorId,packet.uart_header.size,packet.uart_header.end);
        switch(packet.uart_header.size){
            case 0x04 :  // Float
            {
                printf("DATA: |Float value : %f |\n", packet.uart_data.float_data); 
                break;
            }
            case 0x02:  // Int
            {
                printf("DATA: |Int value : %i |\n", packet.uart_data.int_data); 
                break;
            }
            case 0x01:  // Bool
            {
                printf("DATA: |Bool value : %02X |\n", packet.uart_data.bool_data); 
                break;
            }
        }
        printf("END: | End : %02X |\n", packet.end);
        printf("============= ~DECODED DATA~ ===================\n");
    }
};

#endif
