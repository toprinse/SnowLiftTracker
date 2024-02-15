import datetime
import os
import platform

def generate_doc_file(file_name, class_name):
    content = f"""\
/************************************************************************************
 *  ___       __   ________  ________  ________   ___  ________   ________          
 * |\  \     |\  \|\   __  \|\   __  \|\   ___  \|\  \|\   ___  \|\   ____\         
 * \ \  \    \ \  \ \  \|\  \ \  \|\  \ \  \\\ \  \ \  \ \  \\\ \  \ \  \___|        
 *  \ \  \  __\ \  \ \   __  \ \   _  _\ \  \\\ \  \ \  \ \  \\\ \  \ \  \  ___      
 *   \ \  \|\__\_\  \ \  \ \  \ \  \\\  \\\ \  \\\ \  \ \  \ \  \\\ \  \ \  \|\  \   
 *    \ \____________\ \__\ \__\ \__\\\ _\\\ \__\\\ \__\ \__\ \__\\\ \__\ \_______\\ 
 *     \|____________|\|__|\|__|\|__|\|__|\|__| \|__|\|__|\|__| \|__|\|_______|      
 *                                                                                  	   
 * This file is auto-generated.        
 * File name: {class_name}-REST-API-doc.txt                                     
 * File type: text file                                                      
 * Author: Lucas Abad                                                               
 * Creation date: {datetime.datetime.now().strftime("%m/%d/%Y-%H:%M:%S")}           
 * Description: This file contain the REST API documentation for the 
 * {class_name}Ressource.java file.                                                                 
 ************************************************************************************/

@GET
@Operation(summary = "Return every {class_name.lower()}")
@APIResponse(responseCode = "200",
             content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                schema = @Schema(implementation = DTOPlain{class_name}.class,
                                                 type= SchemaType.ARRAY)))
@APIResponse(responseCode = "204", description = "There is no {class_name}")
      
@GET
@Operation(summary = "return a {class_name.lower()} with given identifier")
@APIResponse(responseCode = "200",
             content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                schema = @Schema(implementation = DTOPlain{class_name}.class)))
@APIResponse(responseCode = "204", description = "There is no {class_name.lower()} with given identifier")

@POST
@Operation(summary = "Create a new {class_name.lower()}")
@APIResponse(responseCode = "201", 
             content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                schema = @Schema(implementation = DTOPlain{class_name}.class)))
@APIResponse(responseCode = "400", description = "Missing attribute(s)")

@PUT
@Operation(summary = "Update an existing {class_name.lower()}")
@APIResponse(responseCode = "200", 
             content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                schema = @Schema(implementation = DTOPlain{class_name}.class)))
@APIResponse(responseCode = "404", description = "There is no {class_name.lower()} with given identifier")

@DELETE
@Operation(summary = "Delete a {class_name.lower()}")
@APIResponse(responseCode = "204",
             content = @Content(mediaType = MediaType.APPLICATION_JSON, 
                                schema = @Schema(implementation = DTOPlain{class_name}.class)))
@APIResponse(responseCode = "404", description = "There is no {class_name.lower()} with given identifier")
"""

    with open(file_name, 'w') as file:
        file.write(content)

# Specify the classes names you want to use
my_classes = ["AnalogMeasure",
				"AnalogSensor",
				"AnalogSensorUnit",
				"Measure",
				"NumMeasure",
				"NumSensor",
				"NumSensorState",
				"Sensor",
				"SensorType",
				"SkiLift",
				"Sation",
				"UserSnow"]

BUILD_DIR = "build"
PATH = os.getcwd()
PATH_SEPARATOR = "/"
if platform.system() == "Windows":
    PATH_SEPARATOR = "\\"
    
BUILD_PATH = PATH+PATH_SEPARATOR+BUILD_DIR
if os.path.exists(BUILD_PATH):
	os.rmdir(BUILD_DIR)
      
os.mkdir(BUILD_DIR)
os.chdir(BUILD_DIR)

for my_class in my_classes:
	# Generate the test file with the specified class name
	file_name = f"{my_class}-REST-API-doc.txt"
	generate_doc_file(file_name, my_class)
	print(f"Java test file '{file_name}' generated successfully.")
