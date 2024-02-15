/*
 * APP_UART.c
 *
 *  Created on: Nov 22, 2023
 *      Author: moura
 */

//----------------- INCLUDE PART -----------------

#include "APP_UART.h"
#include "cmsis_os.h"
#include <string.h>
#include <stdbool.h>
#include "../../Drivers/IOT_Sensor_Lib/B-L475E-IOT01/stm32l475e_iot01_tsensor.h"
#include <stdio.h>
//----------------- DEFINE PART -----------------



//----------------- PRIVATE PROTOTYPE PART -----------------

void APP_UART_Task(void const * argument);

/**
  * @brief  Encode data for the uart transmission with the custom protocol
  * @retval Size of the encoded buffer
  */
uint8_t APP_UART_EncodeData( app_uart_struct_sensor_t i_data, uint8_t io_buffer_output[]);

uint8_t APP_UART_convert_type( app_uart_struct_sensor_t i_data, uint8_t io_buffer_output[]);
//----------------- VARIABLE PART -----------------

UART_HandleTypeDef * gp_uart;

osThreadId_t UARTTaskHandle;

osMessageQueueId_t g_id_queue_uart;


const osThreadAttr_t gc_UARTTask_attributes = {
  .name = "UARTTask",
  .stack_size = 128 * 4,
  .priority = (osPriority_t) osPriorityNormal,
};



//----------------- PRIVATE FUNCTION PART -----------------

uint8_t APP_UART_convert_type( app_uart_struct_sensor_t i_data, uint8_t io_buffer_output[])
{
	uint8_t o_size;
	char data[sizeof(float)] = {0x39, 0xF0, 0xC7, 0x41};
	float test = 0;
	memcpy(&test, data, sizeof(test));
	switch(i_data.type_capteur)
	{
		case APP_UART_TYPE_TEMP:
		case APP_UART_TYPE_HUMIDITY:
		{
			o_size = sizeof(i_data.data);
			memcpy(io_buffer_output, &i_data.data, sizeof(i_data.data));
		}
		break;
		case APP_UART_TYPE_PRESSURE:
		{
			io_buffer_output[0] = ((uint16_t) i_data.data >> 8) & 0xFF;
			io_buffer_output[1] = (uint16_t) i_data.data & 0xFF;
			o_size = 2;
		}
		break;
		case APP_UART_TYPE_PRESENCE:
		{
			io_buffer_output[0] = 1;
			o_size = 1;
		}
		break;
		default:
		{
			HAL_UART_Transmit(gp_uart, "ERROR, one sensor isn't implemented yet but you tried to send data", sizeof("ERROR, one sensor isn't implemented yet but you tried to send data"), 50);
			o_size = 255;
		}
		break;
	}
	return o_size;
}

uint8_t APP_UART_EncodeData( app_uart_struct_sensor_t i_data, uint8_t io_buffer_output[])
{
	uint8_t o_size_buffer = 0;
	uint8_t l_size_data = 0;
	uint8_t l_floatconvert[sizeof(float)] = {0};
	l_size_data = APP_UART_convert_type(i_data, l_floatconvert);
	if( l_size_data == 255)
	{
		return l_size_data;
	}
	io_buffer_output[o_size_buffer++] = APP_UART_START_CHAR;
	io_buffer_output[o_size_buffer++] = APP_UART_CHAR_ID_STATION;
	io_buffer_output[o_size_buffer++] = APP_UART_CHAR_ID_REMONTE;
	io_buffer_output[o_size_buffer++] = i_data.type_capteur;
	io_buffer_output[o_size_buffer++] = i_data.id_capteur;
	io_buffer_output[o_size_buffer++] = l_size_data;
	io_buffer_output[o_size_buffer++]= APP_UART_END_HEADER_CHAR;
	for( uint8_t loop = 0; loop < l_size_data; loop++)
	{
		io_buffer_output[o_size_buffer++] = l_floatconvert[loop];
	}
	io_buffer_output[o_size_buffer++] = APP_UART_END_FRAME_CHAR;

	return o_size_buffer;
}

void APP_UART_Task(void const * argument)
{
	app_uart_struct_sensor_t l_data_uart;
	uint8_t i_size_buff = 0;
	uint8_t l_buffer_uart[APP_UART_MAXSIZE_BUFFER];
	do
	{
		memset(l_buffer_uart, 0, APP_UART_MAXSIZE_BUFFER);
		osMessageQueueGet(g_id_queue_uart, &l_data_uart, NULL, osWaitForever);
		i_size_buff = APP_UART_EncodeData(l_data_uart, l_buffer_uart);
		if( i_size_buff != 255)
		{
			HAL_UART_Transmit(gp_uart, l_buffer_uart, i_size_buff, 500);
		}
		else
		{
			HAL_UART_Transmit(gp_uart, "ERROR when encoding data, use debug", sizeof("ERROR when encoding data, use debug"), 50);
		}
		vTaskDelay(APP_UART_TIME_BETWEEN_DATA/portTICK_PERIOD_MS);
	}while(1);
}

//----------------- PUBLIC FUNCTION PART -----------------

void app_uart_init(UART_HandleTypeDef * i_uart_output, osMessageQueueId_t i_id_queue)
{
	gp_uart = i_uart_output;
	g_id_queue_uart = i_id_queue;
}

void app_uart_start_task()
{
	UARTTaskHandle = osThreadNew(APP_UART_Task, NULL, &gc_UARTTask_attributes);
}


