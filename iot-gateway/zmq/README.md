# SnowLift Tracker ZMQ
## Change Log
| Version|    Date    | Edited by          | Description |
| :---   |   :----:   | :----              | :---         | 
| v1.0.0 | 10/01/2024 | Lucas Abad         | Creation |

## Table of Content

[[_TOC_]]

## Folder Structure 
- **`app/`**: Contains the source code for the application.
- **`lib/`**: Contains the library header file.
- **`src/`**: Contains the library source code. 
    - **`proxy/`**: Contains the proxys source code. 
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
* **shared_library** : build the shared library `libsltipczmq.so`.
* **main_exec** : build the application `zmqmain`.
* **proxy_exec** : build the proxy `proxy`.
* __all__ : build everything in a build folder (dir + shared_library + main_exec).
* __clean__ : remove the build folder.

## To run the app & proxy
We will skip all explanation about how **ld** (the linker) work, and why executing the code will not work. Just use the `LD_LIBRARY_PATH` flag.

**To launch the app:**
```bash 
<path-to>/snowlifttracker/iot-gateway/zmq$ LD_LIBRARY_PATH=:<path-to>/snowlifttracker/iot-gateway/zmq/build/lib/ ./build/bin/zmqmain 
```

**To launch the proxy:**
```bash
<path-to>/snowlifttracker/iot-gateway/zmq$ ./build/bin/proxy
```
\* Here no need for `LD_LIBRARY_PATH`, because we use system libraries.

🗒 **Note** : `<path-to>` is the absolut path to `snowlifttracker`. You can use command `pwd` to not write the path by hand.

:warning: **Warning** : The `Makefile` is prone to changes. Update this little documentation if you modify something.
