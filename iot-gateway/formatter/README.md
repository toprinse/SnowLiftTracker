# SnowLift Tracker Formatter
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
- **`Makefile`**: to build the application.

## Makefile rules 
The `Makefile` expose the following rules :
* __dir__ : create the needed directories
>```bash
>build/
>├── bin
>└── lib
>```
* **shared_library** : build the shared library `libsltformatter.so`.
* **main_exec** : build the application `formattermain`.
* __all__ : build everything in a build folder (dir + shared_library + main_exec).
* __clean__ : remove the build folder.

## To run the app
We will skip all explanation about how **ld** (the linker) work, and why executing the code will not work. Just use the `LD_LIBRARY_PATH` flag.

```bash 
<path-to>/snowlifttracker/iot-gateway/formatter$ LD_LIBRARY_PATH=:<path-to>/snowlifttracker/iot-gateway/formatter/build/lib/ ./build/bin/formattermain 
```

🗒 **Note** : `<path-to>` is the absolut path to `snowlifttracker`. You can use command `pwd` to not write the path by hand.

:warning: **Warning** : The `Makefile` is prone to changes. Update this little documentation if you modify something.
