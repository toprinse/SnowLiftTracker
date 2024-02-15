#include "uart.h"


void print_message(unsigned char data[])
{
    for (int i = 0; i<256 ;i++) printf("%02X ", data[i] );
}

Uart::Uart(string identity): uart0_filestream{UART0_FILESTREAM}{
	this->identity = identity;
}

Uart::~Uart(){
	Close();
}

int Uart::Init(void){
	struct termios options;

    uart0_filestream = open(UART0_PORT, O_RDWR | O_NOCTTY | O_NDELAY);
	if(uart0_filestream == -1)
	{
		printf("Error - Unable to open UART\n"); // add logger 
		return -1;
	}

    // Configure the UART
	tcgetattr(uart0_filestream, &options);
	options.c_cflag = B115200 | CS8 | CLOCAL | CREAD;         //<Set baud rate
	options.c_iflag = IGNPAR;
	options.c_oflag = 0;
	options.c_lflag = 0;
	tcflush(uart0_filestream, TCIFLUSH); // test result 
	tcsetattr(uart0_filestream, TCSANOW, &options); //test result 

	return 0;
}

int Uart::Close(void){

	//stop threads 

	if(uart0_filestream == -1 ){
		printf("Error - UART is not open\n");
		return -1;
	}
	int state = close(uart0_filestream);
	return state;
}

int Uart::Send(void){
	// to thread
	if (uart0_filestream != -1)
	{
		int count = send_string("hello", uart0_filestream); //write(uart0_filestream, &tx_buffer[0], (p_tx_buffer - &tx_buffer[0]));		//Filestream, bytes to write, number of bytes to write
		if (count < 0)
		{
			printf("UART TX error\n"); //logger
		}
		else
		{
			printf("GOOD TRANSMISSION"); //logger
		}
	}
	return 0;
}

int Uart::Receive(uart_packet_t &data){
	//to thread 
		int ret = -1;
		if(uart0_filestream != -1)
		{
			unsigned char rx_buff[256];
			for (int i =0; i<256; i++) rx_buff[i] = 0;

			int rx_length = read(uart0_filestream, rx_buff, sizeof(rx_buff));
			if(rx_length < 0)
			{
				printf("\rERROR ON UART : NO DATA FOUND");
			}
			else if(rx_length == 0)
			{
				printf("\rNO DATA WAITING ON UART");
			}
			else
			{
				printf("\n");
				print_message(rx_buff);
				printf("\n");
				Decode(rx_buff, data);
				print_formatted_data(data);  //to rm
				ret = 0;
			}
		}
	return ret;
}

int Uart::Decode(unsigned char* buf, uart_packet_t &packet){
	/* UART protocol: 
     *	-------------------------------------------------------------------------
	 *  | Start | Data Type | Data Size | End Header |     Data     | End Frame | 
	 *  -------------------------------------------------------------------------
	 *  |   0x01|     1 Byte|     1 Byte|        OxO4| 8 or 16 Bytes|       0x02| 
	 *  -------------------------------------------------------------------------
	 * 
	 * #define APP_UART_START_CHAR 			0x01
	 * #define APP_UART_END_FRAME_CHAR		0x02
	 * #define APP_UART_ESC_CHAR			0x03
	 * #define APP_UART_END_HEADER_CHAR		0x04
     * #define APP_UART_MAXSIZE_BUFFER	 	32
	 * 
     * #define APP_UART_TYPE_TEMP			0x73	Float     4
     * #define APP_UART_TYPE_HUMIDITY		0x74	Float     4
     * #define APP_UART_TYPE_PRESSURE		0x75	Uint16    2
	 * #define APP_UART_TYPE_PRESENCE       0x76	Uint8     1
	*/
	
	//copy packet header
	memcpy(&packet.uart_header.start, &buf[0], sizeof(uint8_t));
	memcpy(&packet.uart_header.stationId, &buf[1], sizeof(uint8_t));
	memcpy(&packet.uart_header.liftId, &buf[2], sizeof(uint8_t));
	memcpy(&packet.uart_header.type, &buf[3], sizeof(uint8_t));
	memcpy(&packet.uart_header.sensorId, &buf[4], sizeof(uint8_t));
	memcpy(&packet.uart_header.size, &buf[5], sizeof(uint8_t));
	memcpy(&packet.uart_header.end, &buf[6], sizeof(uint8_t));	

	// treatment logic
	switch(packet.uart_header.size){
		case 0x04 :  // Float
		{
			memcpy(&packet.uart_data.float_data, &buf[7], sizeof(float));
			memcpy(&packet.end, &buf[11], sizeof(uint8_t));
			break;
		}
		case 0x02:  // Int
		{
			//We need to byte swap because of the STM32 implem.
			// eg:
			// we receive: 0x03 0xd8
			// we memcpy to a buffer and it will be 0xd803 = 55299 or we expect 0x03d8 = 984
			packet.uart_data.int_data = ((uint16_t)buf[7] << 8) | (uint16_t)buf[8] & 0xff;
			memcpy(&packet.end, &buf[9], sizeof(uint8_t));
			break;
		}
		case 0x01:  // Bool
		{
			memcpy(&packet.uart_data.bool_data, &buf[7], sizeof(uint8_t));
			memcpy(&packet.end, &buf[8], sizeof(uint8_t));
			break;
		}
		default :
		{
			//log error 
			printf("\n[ERROR] the data received is not a known format\n");
			return -1;
		}
	} 
	return 0;
}
