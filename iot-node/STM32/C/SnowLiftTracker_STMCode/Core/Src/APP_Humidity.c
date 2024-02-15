/*
 * APP_Humidity.c
 *
 *  Created on: Nov 18, 2023
 *      Author: moura
 */

//----------------- INCLUDE PART -----------------

#include "APP_Humidity.h"
#include "APP_UART.h"
#include "../../Drivers/IOT_Sensor_Lib/B-L475E-IOT01/stm32l475e_iot01_hsensor.h"

//----------------- DEFINE PART -----------------



//----------------- PRIVATE PROTOTYPE PART -----------------

void APP_Humidity_Task(void const * argument);

//----------------- VARIABLE PART -----------------

osThreadId_t g_app_HumidityTaskHandle;

osMessageQueueId_t g_id_queue_hum;

const osThreadAttr_t gc_app_humidity_task_attributes = {
  .name = "HumiditySensorTask",
  .stack_size = 128 * 4,
  .priority = (osPriority_t) osPriorityNormal,
};

//----------------- PRIVATE FUNCTION PART -----------------

void APP_Humidity_Task(void const * argument)
{
	app_uart_struct_sensor_t l_data_hum =
	{
			.type_capteur = APP_UART_TYPE_HUMIDITY,
			.data = 0,
			.id_capteur = APP_HUMIDITY_ID_SENSOR,
	};
	do
	{
		l_data_hum.data = BSP_HSENSOR_ReadHumidity();
		osMessageQueuePut(g_id_queue_hum, &l_data_hum, sizeof(l_data_hum), 500);
		vTaskDelay(APP_HUMIDITY_TIME_BETWEEN_VALUE*1000/portTICK_PERIOD_MS);
	}while(1);
}

//----------------- PUBLIC FUNCTION PART -----------------

uint32_t app_humidity_init(osMessageQueueId_t i_id_queue)
{
	g_id_queue_hum = i_id_queue;
	return BSP_HSENSOR_Init();
}
void app_humidity_task_start()
{
	g_app_HumidityTaskHandle = osThreadNew(APP_Humidity_Task, NULL, &gc_app_humidity_task_attributes);
}

