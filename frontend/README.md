# SnowLift Tracker Frontend

## Change Log
| Version|    Date    | Edited by          | Description |
| :---   |   :----:   | :----              | :---         | 
| v1.0.0 | 30/11/2023 | NSSDEV             | Creation |


## Table of Content

[[_TOC_]]

## Project Structure
The frontend code is organized as follows:

- **'frontend/'**: 
    - **`src/`**: Contains the source code for the frontend application.
        - **`assets/`**: Contains images.
        - **`App.vue/`**: Main Vue file
        - **`components/`**: Contains Vue components
        - **`main.js`**: Main javascript file
        - **`plugins`** 
        - **`router`**: Contains index.js file, it's contains routes 
        - **`services`** Contains all javascript services
        - **`store/`**: Contains all javascript modules
        - **`views/`**: Contains the Vue web pages
- **`package.json`**: npm project file for managing dependencies and build configurations.


## Getting started

### Setting up the project
1. Clone the snowlift tracker repository 
```bash
git clone https://gricad-gitlab.univ-grenoble-alpes.fr/cs550-applications-iot/2023-2024/cs550-groupe01/snowlifttracker.git
```
2. Checkout stable branch
```bash
cd snowlifttracker && git checkout <stable-branch>
```
3. change directory to frontend
```bash
cd frontend/
```

### Install dependecies

To install all dependecies, write the command below

```bash
npm install
```

### Install and run the docker for keycloak

To install the docker, write the command below

```bash
sudo apt install docker.io
```

To run the docker, write the command below

```bash
sudo docker run -p 8886:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:19.0.3 start-dev
```
>🗒 **Note** : To run docker as non-sudo:
>
>1. Create a `docker` group if it doesn't exist.
>```bash
>sudo groupadd docker
>```
>2. Add your user profile to the `docker` group
>```
>sudo usermod -aG docker $User
>```
>3. Activate the changes group.
>```bash
>newgrp docker
>```
>4. Run a docker comand without sudo to check 
>```bash
>docker ps
>```
>For further information you can go read [docker documentation](https://docs.docker.com/engine/install/linux-postinstall/)

The docker will be available at the adress : **localhost:8886**

### Configuration in keycloak

In Keycloak we need to create a realm and import the client nammed `snowlifttracker-client` which contains the good configuration.
However, each time you launch keycloak, you need to re-create a realm, re-upload the client and reconfigure the two roles "admin" and "user".
You will also need to create users who will be assigned one of the two roles

**To create the snowlifttracker-realm :**

    -   Click on "Administration Console"
    -   username : "admin"
    -   password : "admin"
    -   Click on "Manage" (in the '☰' symbol)
    -   Click on "Create Realm"
    -   In "Realm name" write "snowlifttracker-realm"
    -   Create

**To import the snowlifttracker-client :**

    -   Select the "Snowlifttracker-realm" you have created
    -   Click on "Client"
    -   Click on "Import Client"
    -   Import the file "snowlifttracker-frontend.json"
    -   Save

**To create the two roles "admin" and "user"**

    -   In the "snowlifttracker-frontend" client
    -   Click on "Roles" tab
    -   Click on "Create Role"
    -   In "Role name" write "admin" and save
    -   Do the same operation for "user"


**To create user and assign them to a role**

    -   Click on the '☰' symbol and go to Users
    -   Click on "Create user"
    -   Fill every blanks
    -   Don't select any action and Create
    -   To assign a password go to "Credentials" tab
    -  Click on "Set password"
    -   Set a password and Put "Temporary" at off
    -   To assign the role to the user go to "Role mapping"
    -   Click on "assign role"
    -   "Filter by client"
    -   select "snowlifttracker-frontend admin" or "snowlifttracker-frontend user" (you may need to switch to the next list tab because there is 29 roles).



### Working on the project
The frontend server can be launched (in dev mode):

```bash
npm run serve
```
This command should lauch local website on port **8080**  access the website at http://localhost:8080

:warning: **Warning** : 
Launch Quarkus before running the website to avoid errors.

## Testing
To test the website there is the possibility to (to do ...)
