#include "APP_LED.h"
#include "cmsis_os.h"
#include <stdbool.h>
//----------------- DEFINE PART -----------------

//----------------- PRIVATE PROTOTYPE PART -----------------

void APP_LED_Task(void const * argument);

//----------------- VARIABLE PART -----------------

osThreadId_t g_app_LedTaskHandle;

const osThreadAttr_t gc_app_led_taskLed_attributes = {
  .name = "LedTask",
  .stack_size = 128 * 4,
  .priority = (osPriority_t) osPriorityNormal,
};

//----------------- PRIVATE FUNCTION PART -----------------

void APP_LED_Task(void const * argument)
{
	bool l_state = false;
	do
	{
		l_state = !l_state;
		HAL_GPIO_WritePin(APP_LED_USER_PORT, APP_LED_USER_PIN, l_state);
		vTaskDelay(APP_LED_PERIOD_TOGGLE/portTICK_PERIOD_MS);
	}while(1);
}

//----------------- PUBLIC FUNCTION PART -----------------
void app_led_start_task()
{
	g_app_LedTaskHandle = osThreadNew(APP_LED_Task, NULL, &gc_app_led_taskLed_attributes);
}

