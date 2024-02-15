/*
 * APP_LED.h
 *
 *  Created on: Nov 17, 2023
 *      Author: moura
 */

#ifndef INC_APP_LED_H_
#define INC_APP_LED_H_

//----------------- INCLUDE -----------------
#include "main.h"

//----------------- DEFINE -----------------
#define APP_LED_USER_PORT 		LED2_GPIO_Port
#define APP_LED_USER_PIN 		LED2_Pin
#define APP_LED_PERIOD_TOGGLE	1000 //in ms

//----------------- TYPEDEF -----------------

//----------------- PROTOTYPE -----------------
void app_led_start_task();



#endif /* INC_APP_LED_H_ */
