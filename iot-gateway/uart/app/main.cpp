#include "uart.h"

using namespace std;

int main(int argc, char *argv[]){
	int status;
	Uart::uart_packet_t packet = {{0, 0, 0, 0, 0}, {0}, 0};
	Uart uart("RECEIVER");

	status = uart.Init();
	if(status == -1) return -1;

	while(1)
	{
		uart.Receive(packet);
		usleep(1000000);	
	}
	
	return 0;
}
