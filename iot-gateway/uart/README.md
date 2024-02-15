# SnowLift Tracker Uart
## Change Log
| Version|    Date    | Edited by          | Description |
| :---   |   :----:   | :----              | :---         | 
| v1.0.0 | 10/12/2023 | Lucas Abad         | Creation |

## Table of Content

[[_TOC_]]

## Folder Structure 
- **`app/`**: Contains the source code for the application.
- **`lib/`**: Contains the library header file.
- **`src/`**: Contains the library source code. 
- **`make.sh`**: Utilitary script to build code in a 'bin' folder. To be deleted
- **`Makefile`**: to build the application.

## Makefile rules 
The `Makefile` expose the following rules :
* __dir__ : create the needed directories
>```bash
>build/
>├── bin
>├── lib
>└── obj # unused (to be removed)
>```
* **shared_library** : build the shared library `libsltuart.so`.
* **main_exec** : build the application `uartmain`.
* __all__ : build everything in a build folder (dir + shared_library + main_exec).
* __clean__ : remove the build folder.

## To run the app
We will skip all explanation about how **ld** (the linker) work, and why executing the code will not work. Just use the `LD_LIBRARY_PATH` flag.

```bash 
<path-to>/snowlifttracker/iot-gateway/Uart$ LD_LIBRARY_PATH=:<path-to>/snowlifttracker/iot-gateway/Uart/build/lib/ ./build/bin/uartmain 
Error - Unable to open UART # Not on a Raspberry
```

🗒 **Note** : `<path-to>` is the absolut path to `snowlifttracker`. You can use command `pwd` to not write the path by hand.

:warning: **Warning** : The `Makefile` is prone to changes. Update this little documentation if you modify something.
