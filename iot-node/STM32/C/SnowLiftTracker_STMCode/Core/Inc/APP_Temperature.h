/*
 * APP_Temperature.h
 *
 *  Created on: Nov 17, 2023
 *      Author: moura
 */

#ifndef INC_APP_TEMPERATURE_H_
#define INC_APP_TEMPERATURE_H_

//----------------- INCLUDE ----------------- 

#include "stdint.h"
#include "cmsis_os.h"

//----------------- DEFINE ----------------- 

#define APP_TEMPERATURE_TIME_BETWEEN_VALUE 10 // in second

#define APP_TEMPERATURE_ID_SENSOR	0x09

//----------------- TYPEDEF ----------------- 

//----------------- PROTOTYPE ----------------- 

uint32_t app_temperature_init(osMessageQueueId_t i_id_queue);
void app_temperature_task_start();

#endif /* INC_APP_TEMPERATURE_H_ */
