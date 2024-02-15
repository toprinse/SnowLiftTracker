# SnowLift Tracker Backend

## Change Log
| Version|    Date    | Edited by          | Description |
| :---   |   :----:   | :----              | :---         | 
| v1.0.0 | 19/11/2023 | LABD               | Creation |
| v1.1.0 | 21/11/2023 | LABD               | Add architecture overview + installation |

## Table of Content

[[_TOC_]]

## In this document
Welcome to the backend folder of our project! This directory houses the Java backend code that interfaces with the Quarkus framework to facilitate the secure handling of sensor data. Please follow the instructions below to understand, set up, and contribute to the backend codebase.

## Choice of Quarkus

Our project leverages the Quarkus framework for several reasons:

- **Lightweight and Fast:** Quarkus is designed for optimal performance and resource utilization, making it well-suited for microservices and cloud-native applications.

- **GraalVM Support:** Quarkus supports GraalVM, allowing us to compile our Java applications into native executables for improved startup times and reduced memory footprint.

- **Extensions Ecosystem:** Quarkus has a rich ecosystem of extensions that streamline the integration of various technologies, making it easier to develop and maintain our backend.

## Architecture Overview

Explain the high-level architecture of the backend, illustrating the key components and their interactions. Include diagrams if possible.

### Data model
We have this data model :
```plantuml
class UserSnow <<PanacheEntity>>{
+firstName: String
+lastName: String
+email: String
+login: String
+password: String
+permission: String
}

class Station <<PanacheEntity>>{
+name: String
+longitude: String
+latitude: String
}

class SkiLift <<PanacheEntity>>{
+name: String
+longitude: String
+latitude: String
+open: Boolean
}

abstract class Sensor <<PanacheEntity>>{
+manufacturer: String
+reference: String
}

class SensorType <<PanacheEntity>>{
+type: String
}

class AnalogSensor <<PanacheEntity>>

class NumSensor <<PanacheEntity>>

class AnalogSensorUnit <<PanacheEntity>>{
+unit: String
}

class NumSensorState <<PanacheEntity>>{
+low: Boolean
+high: Boolean
}

class AnalogMeasure <<PanacheEntity>>{
+analogData: Float
}

class NumMeasure <<PanacheEntity>>{
+numData: Boolean
}

abstract class Measure <<PanacheEntity>>{
+timestamp: LocalDateTime
}

UserSnow -left- Station
Station "1" -down- "0..*" SkiLift
SkiLift "1" -down- "0..*" Sensor
Sensor "0..*" -right- "1" SensorType
Sensor <|-down- AnalogSensor
Sensor <|-down- NumSensor
AnalogSensor "0..*" -left- "1" AnalogSensorUnit
NumSensor "0..*" -right- "1" NumSensorState
AnalogSensor "1" -down- "0..*" AnalogMeasure
NumSensor "1" -down- "0..*"NumMeasure
AnalogMeasure <|-down- Measure
NumMeasure <|-down- Measure
```

## Data Transfer Objects (DTOs)
In software development, Data Transfer Objects (DTOs) play a crucial role in facilitating the seamless exchange of information between different layers of an application. A DTO is a design pattern that allows for the efficient and structured transfer of data between components, often employed to bridge the gap between the business logic layer and the data access layer.

### Why DTOs ?
DTOs offer several advantages, including:

* __Separation of Concerns:__ DTOs help maintain a clear separation between the business logic and data access layers, promoting a modular and maintainable codebase.

* __Reducing Coupling:__ By providing a standardized way to transfer data, DTOs reduce coupling between different layers, allowing for easier modifications without impacting the entire system.

* __Data Transformation:__ DTOs facilitate the transformation of data between the format used in the database and the objects used in the application's business logic.

* __Minimizing Data Transfer:__ DTOs enable the transfer of only the essential data between layers, optimizing performance and reducing the amount of data transmitted over networks.

* __Versioning and Flexibility:__ DTOs can help manage versioning changes and adapt to alterations in data structures, offering flexibility as the application evolves.

### Bridging Quarkus and Vue.js with DTOs
In our project, the integration of Data Transfer Objects (DTOs) serves as a crucial bridge connecting the Quarkus backend to the Vue.js frontend. By strategically implementing this design pattern, our primary goal is to optimize the efficiency and maintainability of our codebase. This approach not only facilitates seamless communication between the backend and frontend but also lays the foundation for a modular architecture.


## Mapping
In software development, the mapping between Data Transfer Objects (DTOs) and Class Models is a critical aspect of designing scalable and maintainable applications. A DTO represents a simplified, often flattened, and tailored version of a class model, typically used to transfer data between different layers of an application or between the backend and frontend. The process of mapping involves establishing a relationship between the attributes of DTOs and class models, ensuring seamless communication while addressing the specific needs of each layer.
### Mapping key objectives
* __Abstraction and Simplification:__ DTOs serve as an abstraction layer, simplifying the data structure for specific use cases. Through mapping, developers can define how data from complex class models is transformed into more straightforward DTO representations.

* __Data Transfer Efficiency:__ The mapping process aims to optimize data transfer between components. By tailoring the content of DTOs to the requirements of a particular operation or layer, unnecessary data is excluded, resulting in more efficient communication.

* __Selective Attribute Exposure:__ Mapping allows for the selective exposure of attributes. While class models may contain a comprehensive set of properties, DTOs can be crafted to include only the essential information required for specific tasks, enhancing security and reducing data redundancy.

* __Adaptability to Change:__ Applications evolve over time, and so do data models. Mapping provides a mechanism to adapt to changes in class models without causing disruptions in other parts of the system. This flexibility is crucial for maintaining the application's agility and responsiveness to evolving requirements.

* __Integration Across Layers:__ Mapping between DTOs and class models facilitates smooth integration between different layers of an application. Whether transitioning data between the business logic layer and the data access layer or between backend and frontend components, mapping ensures compatibility and coherence.

##  REST API resources
REST API resources are the key entities or objects that your API exposes and interacts with. Resources represent the data or services that clients can access or manipulate through your API. In a RESTful architecture, resources are identified by unique URIs (Uniform Resource Identifiers), and interactions with these resources are typically performed using standard HTTP methods (GET, POST, PUT, DELETE).

### Resources key concepts

* __Resource URIs:__ Each resource is uniquely identified by a URI, which serves as its address on the web. For example, if you have a resource representing users, the URI might look like `https://api.example.com/users`.

* __HTTP Methods:__ RESTful APIs use standard HTTP methods to perform operations on resources:
    * __GET__: Retrieve the information of a resource.
    * __POST__: Create a new resource.
    * __PUT__ or __PATCH__: Update an existing resource.
    * __DELETE__: Remove a resource.

* __Representation:__ Resources can be represented in different formats, such as JSON or XML. The representation includes the data associated with the resource and any additional metadata. Clients can request specific representations based on their needs.

* __Statelessness:__ RESTful APIs are designed to be stateless, meaning each request from a client contains all the information needed to understand and process that request. The server doesn't store any information about the client's state between requests.

* __Resource Relationships:__ Resources can be related to each other. For example, a blog post resource might be associated with comments. The relationships between resources are often represented in the API, allowing clients to navigate between related entities.

* __CRUD Operations:__ RESTful APIs often follow CRUD (Create, Read, Update, Delete) operations for resource management. These operations correspond to the HTTP methods mentioned earlier. For example:
    * __GET /users:__ Retrieve a list of users.
    * __POST /users:__ Create a new user.
    * __GET /users/{id}:__ Retrieve a specific user.
    * __PUT /users/{id}__ or __PATCH /users/{id}:__ Update a specific user.
    * __DELETE /users/{id}:__ Delete a specific user.

## Project Structure
The backend code is organized as follows:

- **`src/`**: Contains the source code for the backend application.
  - **`main/`**: Main source code for the application.
    - **`docker/`**: docker files to run in containers (NOT MAINTAINED)
    - **`java/`**: Java source files.
        - **`dto`**: data transfer objects
        - **`mapper`**: mappers
        - **`model`**: data model
        - **`resource`** REST API resources
    - **`resources/`**: Configuration files and static resources.
    - **`utils/`**: Utilitary scripts
  - **`test/`**: Test source code.
- **`pom.xml`**: Maven project file for managing dependencies and build configurations.

## Getting started

### Setting up the project
1. Clone the snowlif tracher repository 
```bash
git clone https://gricad-gitlab.univ-grenoble-alpes.fr/cs550-applications-iot/2023-2024/cs550-groupe01/snowlifttracker.git
```
2. Checkout stable branch
```bash
cd snowlifttracker && git checkout <stable-branch>
```
3. change directory to backend
```bash
cd backend/
```

### Install dependecies

List the dependencies required to run the backend code. Include links to relevant documentation or installation instructions for each dependency.

### Working on the project
The backend server can be launched (in dev mode):

```bash
mvn clean quarkus:dev
```
This command should lauch a server on either port **8080** or **8887** access the dev ui at http://localhost:8080

🗒 **Note** : 
> quarkus default port is 8080, but to run with the frontend whose port is also 8080 we changed the port to 8887. To change port refer to [configuration](#Advanced-Configuration) section.

## Advanced Configuration
Quarkus configuration file is located at `src/main/resources/application.properties`. In this section when we refer to the `application.properties` file it is always the quarkus configuration file.

### Database
Our project support 2 types of databases :
* H2
* PostgreSQL

To switch between the database, the `application.properties` file need to be edited. And especially the default quarkus configuration.

#### H2
Change the previous configuration and update it :
```diff
- quarkus.datasource.db-kind=postgresql
+ quarkus.datasource.db-kind=h2
```

#### PostgreSQL
Change the previous configuration and update it :
```diff
- quarkus.datasource.db-kind=h2
+ quarkus.datasource.db-kind=postgresql
```

### Server port
Changing server port can is pretty easy with quarkus, just change existing config or add a config in the `application.properties` file.

```
quarkus.http.port=8887
```

🗒 **Note** :  This change the quarkus default user configuration. Quarkus comes with three profiles dev, test and prod. You may also need to change the dev profile configuration wit this line `%dev.quarkus.http.port=8887`.

### Other configs 
If more configuration are needed you can read this [documentation](https://quarkus.io/guides/security-oidc-configuration-properties-reference). 

## Testing
To test the REST API, there is two possibilities:
* Manual testing
* Automatic testing

### Manual testing 
A swagger plugins for Quarkus is installed, after launching Quarkus it's available at http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui. 

🗒 **Note** : Without passing by the link, but by using the dev ui we can access swagger-ui by a clic on the `Swagger UI` link which is located in the `SmallRye OpenAPI` nested window.

Now you can manually test every REST API resources.

❗ **Important** : If you need to add some elements to the database, just add the wanted elements in the `src/main/resources/import.sql` file.

### Automatic test
The project comes with a bunch of automatic tests, to run them :
```bash
mvn clean quarkus:test
```

:warning: **Warning** : 
>If a test is annoted with :
>```java
>@Disabled
>```
>This test will not be run.
