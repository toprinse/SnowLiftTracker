/*
 * APP_UART.h
 *
 *  Created on: Nov 22, 2023
 *      Author: moura
 */

#ifndef INC_APP_UART_H_
#define INC_APP_UART_H_

//----------------- INCLUDE ----------------- 

#include <stdint.h>
#include "stm32l4xx_hal.h"
#include "cmsis_os.h"

//----------------- DEFINE -----------------

#define APP_UART_CHAR_ID_STATION		0x40
#define APP_UART_CHAR_ID_REMONTE		0x41

#define APP_UART_START_CHAR 			0x01
#define APP_UART_END_FRAME_CHAR			0x02
#define APP_UART_END_HEADER_CHAR		0x04
#define APP_UART_MAXSIZE_BUFFER		 	32

#define APP_UART_TYPE_TEMP				0x73
#define APP_UART_TYPE_HUMIDITY			0x74
#define APP_UART_TYPE_PRESSURE			0x75
#define APP_UART_TYPE_PRESENCE			0x76

#define APP_UART_MAX_SIZE_QUEUE			10


#define APP_UART_TIME_BETWEEN_DATA		100 //ms
//----------------- TYPEDEF ----------------- 

typedef struct
{
	float data;
	uint8_t type_capteur;
	uint8_t id_capteur;
}app_uart_struct_sensor_t;

//----------------- PROTOTYPE ----------------- 
void app_uart_init(UART_HandleTypeDef * i_uart_output, osMessageQueueId_t i_id_queue);
void app_uart_start_task();


#endif /* INC_APP_UART_H_ */
