# SnowLift Tacker STM32 C

For this project, we will be using STM32CubeIDE and developing in C. We have implemented a FreeRTOS-based architecture to parallelize tasks and make the sensors independent. To use the sensors, we're going to use a library native to STM32CubeIDE, the `STM32L4XX` library.  

## Code architecture
As explained above, we use FreeRTOS to break down the code into tasks.  Here is an explanation of what each task does: 

### LED Task
This task flashes LED_2. This provides information about the status of the board, because if LED_2 remains off, it could mean that our firmware is no longer working. 
You can set the flashing frequency using 
```C
#define APP_LED_PERIOD_TOGGLE	500
```
This value is the duration of a state (high or not) in milliseconds and is stored in the `APP_LED.h` file. 

### Temperature Task
This task retrieves the temperature at a regular time interval.

You can change the time interval at which you retrieve the temperature in the `APP_Temperature.h` file. To do this, modify the following line with the desired interval in seconds.
```C
#define APP_TEMPERATURE_TIME_BETWEEN_VALUE 10
```

To initialise the temperature sensor (HTS221), the I2C2 must be used. To do this, the `STM32L4XX` library provides a code for each sensor in the `B-L475E-IOT01` file. We then use :
```C
BSP_TSENSOR_Init();
```

In order to initialise the temperature sensor, we will then retrieve the temperature values using :
```C
BSP_TSENSOR_ReadTemp();
```
This function returns the current temperature (with an accuracy of ±0.5°C) in °C in float format.
For more information, refer to the `UART TASK` section.

### Humidity Task
This task retrieves the humidity at a regular time interval.

You can change the time interval at which you retrieve the temperature in the `APP_Humidity.h` file. To do this, modify the following line with the desired interval in seconds.
```C
#define APP_HUMIDTY_TIME_BETWEEN_VALUE 10
```

To initialise the temperature sensor (HTS221), the I2C2 must be used. To do this, the `STM32L4XX` library provides a code for each sensor in the `B-L475E-IOT01` file. We then use :
```C
BSP_HSENSOR_Init();
```

To initialise the humidity sensor, then retrieve the humidity values using :
```C
BSP_HSENSOR_ReadHumidity();
```
This function returns the current humidity (with an accuracy of ±3.5 %) in % in float format.

For more information, refer to the `UART TASK` section.

### Pressure Task
This task retrieves the atmospheric pressure at a regular time interval.

You can change the time interval at which you retrieve the temperature in the `APP_Pressure.h` file. To do this, modify the following line with the desired interval in seconds.
```C
#define APP_PRESSURE_TIME_BETWEEN_VALUE 10
```

To initialise the temperature sensor (LPS22HB), the I2C2 must be used. To do this, the `STM32L4XX` library provides a code for each sensor in the `B-L475E-IOT01` file. We then use :
```C
BSP_PSENSOR_Init();
```

In order to initialise the pressure sensor, we will then retrieve the values using :
```C
BSP_PSENSOR_ReadPressure();
```
This function returns the current pressure (with an accuracy of ±1 hPa) in % in integer format.

For more information, please refer to the `UART TASK` section.

### UART Task
This task is used to retrieve sensor values. To do this, we use the default STM32CubeIDE libraries.

We will retrieve the values using a queue and a structured type. The type is composed as follows:
```C
typedef struct
{
	float data;
	uint8_t type_capteur;
	uint8_t id_capteur;
}app_uart_struct_sensor_t;
```
`data` : Represents the value measured by the sensor.

`type_capteur` : A value defined by #define to identify which sensor has put a value in the queue.

`id_capteur` : A value defined by #define to identify each sensor in case we've multiple sensor.

We use the functions
```C
osMessageQueuePut()
osMessageQueueGet()
```

In order to transmit the data between the different tasks. Once the data has been retrieved, it is encoded using the following proprietary protocol.

#### Custom UART Protocol

| **SIGNIFICATION** | **START** | **ID_STATION** | **ID_LIFT** | **DATA_TYPE** | **ID_SENSOR** |     **DATA_SIZE**    | **END_HEADER** | **DATA[N]** | **...** | **DATA[0]** | **END_OF_FRAME** |
|:-----------------:|:---------:|:--------------:|:-----------:|:-------------:|:-------------:|:--------------------:|:--------------:|:-----------:|:-------:|:-----------:|:----------------:|
|     **VALUE**     |    0x01   |    Variable    |   Variable  | DATA_TYPE_DEF |    Variable   | Depends of data type |      0x02      |   DATA[n]   |   ...   |   DATA[0]   |       0x04       |

Here are the different possible values for DATA_TYPE :
```C
#define APP_UART_TYPE_TEMP				0x73
#define APP_UART_TYPE_HUMIDITY			0x74
#define APP_UART_TYPE_PRESSURE			0x75
#define APP_UART_TYPE_PRESENCE			0x76
```

And here's how the grid is organised for each type:
|      **DATA_TYPE**     | **HEXA_VALUE** | **DATA_FORMAT** | **SIZE_DATA** |
|:----------------------:|:--------------:|:---------------:|:-------------:|
|   APP_UART_TYPE_TEMP   |      0x73      |      FLOAT      |       4       |
| APP_UART_TYPE_HUMIDITY |      0x74      |      FLOAT      |       4       |
| APP_UART_TYPE_PRESSURE |      0x75      |      UINT16     |       2       |
| APP_UART_TYPE_PRESENCE |      0x76      |      UINT8      |       1       |

The `DATA_SIZE` field will therefore use the value "SIZE_DATA" and the escape character will be added if necessary.


Here are the different special values:
```C
#define APP_UART_START_CHAR 			0x01
#define APP_UART_END_FRAME_CHAR			0x02
#define APP_UART_END_HEADER_CHAR		0x04
```

The different ids (station, lift and sensor) will depend on the platform and will vary according to the station, the lift in the station and the id of the sensor in each lift.