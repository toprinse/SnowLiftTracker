/*
 * APP_Temperature.c
 *
 *  Created on: Nov 17, 2023
 *      Author: moura
 */

//----------------- INCLUDE PART -----------------

#include "APP_Temperature.h"
#include "APP_UART.h"
#include "../../Drivers/IOT_Sensor_Lib/B-L475E-IOT01/stm32l475e_iot01_tsensor.h"
//----------------- DEFINE PART -----------------


//----------------- PRIVATE PROTOTYPE PART -----------------

void APP_Temperature_Task(void const * argument);

//----------------- VARIABLE PART -----------------

osMessageQueueId_t g_id_queue_temp;

osThreadId_t g_app_TemperatureTaskHandle;

const osThreadAttr_t gc_app_temperature_task_attributes = {
  .name = "TemperatureSensorTask",
  .stack_size = 128 * 4,
  .priority = (osPriority_t) osPriorityNormal,
};

//----------------- PRIVATE FUNCTION PART -----------------

void APP_Temperature_Task(void const * argument)
{
	app_uart_struct_sensor_t l_data_temp =
	{
			.type_capteur = APP_UART_TYPE_TEMP,
			.data = 0,
			.id_capteur = APP_TEMPERATURE_ID_SENSOR,
	};
	do
	{
		l_data_temp.data = BSP_TSENSOR_ReadTemp();
		osMessageQueuePut(g_id_queue_temp, &l_data_temp, sizeof(l_data_temp), 500);
		vTaskDelay(APP_TEMPERATURE_TIME_BETWEEN_VALUE*1000/portTICK_PERIOD_MS);
	}while(1);
}

//----------------- PUBLIC FUNCTION PART -----------------

uint32_t app_temperature_init(osMessageQueueId_t i_id_queue)
{
	g_id_queue_temp = i_id_queue;
	return BSP_TSENSOR_Init();
}
void app_temperature_task_start()
{
	g_app_TemperatureTaskHandle = osThreadNew(APP_Temperature_Task, NULL, &gc_app_temperature_task_attributes);
}
