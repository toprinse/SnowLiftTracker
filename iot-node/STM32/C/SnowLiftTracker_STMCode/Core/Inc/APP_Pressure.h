/*
 * APP_Humidity.h
 *
 *  Created on: Nov 18, 2023
 *      Author: moura
 */

#ifndef INC_APP_PRESSURE_H_
#define INC_APP_PRESSURE_H_

//----------------- INCLUDE ----------------- 

#include "stdint.h"
#include "cmsis_os.h"
//----------------- DEFINE ----------------- 

#define APP_PRESSURE_TIME_BETWEEN_VALUE 5 // in second
#define APP_PRESSURE_ID_SENSOR			0x05

//----------------- TYPEDEF ----------------- 

//----------------- PROTOTYPE ----------------- 

uint32_t app_pressure_init(osMessageQueueId_t i_id_queue);
void app_pressure_task_start();

#endif /* INC_APP_PRESSURE_H_ */
