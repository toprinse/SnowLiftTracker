/*
 * APP_Pressure.c
 *
 *  Created on: Nov 18, 2023
 *      Author: moura
 */

//----------------- INCLUDE PART -----------------

#include "APP_Pressure.h"
#include "APP_UART.h"
#include "../../Drivers/IOT_Sensor_Lib/B-L475E-IOT01/stm32l475e_iot01_psensor.h"

//----------------- DEFINE PART -----------------



//----------------- PRIVATE PROTOTYPE PART -----------------

void APP_Pressure_Task(void const * argument);

//----------------- VARIABLE PART -----------------

osThreadId_t g_app_PressureTaskHandle;

osMessageQueueId_t g_id_queue_pressure;

const osThreadAttr_t gc_app_pressure_task_attributes = {
  .name = "PressureSensorTask",
  .stack_size = 128 * 4,
  .priority = (osPriority_t) osPriorityNormal,
};

//----------------- PRIVATE FUNCTION PART -----------------

void APP_Pressure_Task(void const * argument)
{
	app_uart_struct_sensor_t l_data_pressure =
	{
			.type_capteur = APP_UART_TYPE_PRESSURE,
			.data = 0,
			.id_capteur = APP_PRESSURE_ID_SENSOR,
	};
	do
	{
		l_data_pressure.data = BSP_PSENSOR_ReadPressure();
		osMessageQueuePut(g_id_queue_pressure, &l_data_pressure, sizeof(l_data_pressure), 500);
		vTaskDelay(APP_PRESSURE_TIME_BETWEEN_VALUE*1000/portTICK_PERIOD_MS);
	}while(1);
}

//----------------- PUBLIC FUNCTION PART -----------------

uint32_t app_pressure_init(osMessageQueueId_t i_id_queue)
{
	g_id_queue_pressure = i_id_queue;
	return BSP_PSENSOR_Init();
}
void app_pressure_task_start()
{
	g_app_PressureTaskHandle = osThreadNew(APP_Pressure_Task, NULL, &gc_app_pressure_task_attributes);
}

