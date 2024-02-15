/*
 * APP_Presence.c
 *
 *  Created on: Dec 6, 2023
 *      Author: moura
 */


#include "APP_Presence.h"
#include "main.h"
#include "APP_UART.h"
#include <stdbool.h>
//----------------- DEFINE PART -----------------

//----------------- PRIVATE PROTOTYPE PART -----------------

void APP_Presence_start_read();
void APP_Presence_Task(void const * argument);

//----------------- VARIABLE PART -----------------

osThreadId_t g_app_PresenceTaskHandle;

const osThreadAttr_t gc_app_presence_taskPresence_attributes = {
  .name = "PresenceTask",
  .stack_size = 128 * 4,
  .priority = (osPriority_t) osPriorityNormal,
};

osMessageQueueId_t g_id_queue_presence;

//----------------- PRIVATE FUNCTION PART -----------------

void APP_Presence_start_read()
{

}
void APP_Presence_Task(void const * argument)
{
	GPIO_PinState l_state_button;
	app_uart_struct_sensor_t l_data_presence =
		{
				.type_capteur = APP_UART_TYPE_PRESENCE,
				.data = GPIO_PIN_SET,
				.id_capteur = APP_PRESENCE_ID_SENSOR,
		};
	GPIO_PinState l_old_state = GPIO_PIN_SET;
	do
	{
		l_data_presence.data = HAL_GPIO_ReadPin(GPIOC, GPIO_PIN_13);
		if( l_old_state == GPIO_PIN_SET)
		{
			if( (l_data_presence.data == GPIO_PIN_RESET))
			{
				osMessageQueuePut(g_id_queue_presence, &l_data_presence, sizeof(l_data_presence), 500);
			}
		}
		l_old_state = l_data_presence.data;
		vTaskDelay(APP_PRESENCE_TIME_BETWEEN_VALUE/portTICK_PERIOD_MS);
	}while(1);
}

//----------------- PUBLIC FUNCTION PART -----------------
void app_presence_init(osMessageQueueId_t i_id_queue)
{
	g_id_queue_presence = i_id_queue;
}
void app_presence_task_start()
{
	g_app_PresenceTaskHandle = osThreadNew(APP_Presence_Task, NULL, &gc_app_presence_taskPresence_attributes);
}

