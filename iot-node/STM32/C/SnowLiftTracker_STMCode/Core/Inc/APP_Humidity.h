/*
 * APP_Humidity.h
 *
 *  Created on: Nov 18, 2023
 *      Author: moura
 */

#ifndef INC_APP_HUMIDITY_H_
#define INC_APP_HUMIDITY_H_

//----------------- INCLUDE ----------------- 

#include "stdint.h"
#include "cmsis_os.h"

//----------------- DEFINE ----------------- 

#define APP_HUMIDITY_TIME_BETWEEN_VALUE 10 // in second
#define APP_HUMIDITY_ID_SENSOR			0x08

//----------------- TYPEDEF ----------------- 

//----------------- PROTOTYPE ----------------- 

uint32_t app_humidity_init(osMessageQueueId_t i_id_queue);
void app_humidity_task_start();

#endif /* INC_APP_HUMIDITY_H_ */
