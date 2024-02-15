/*
 * APP_Presence.h
 *
 *  Created on: Dec 6, 2023
 *      Author: moura
 */

#ifndef INC_APP_PRESENCE_H_
#define INC_APP_PRESENCE_H_


//----------------- INCLUDE -----------------

#include "stdint.h"
#include "cmsis_os.h"
#include "stm32l4xx_hal.h"
//----------------- DEFINE -----------------

#define APP_PRESENCE_TRIG_PORT 		GPIOB
#define APP_PRESENCE_TRIG_PIN 		GPIO_PIN_2

#define APP_PRESENCE_TIME_BETWEEN_VALUE 100 // in ms
#define APP_PRESENCE_ID_SENSOR	0x0A
//----------------- TYPEDEF -----------------



//----------------- PROTOTYPE -----------------

void app_presence_init(osMessageQueueId_t i_id_queue);
void app_presence_task_start();

#endif /* INC_APP_PRESENCE_H_ */
