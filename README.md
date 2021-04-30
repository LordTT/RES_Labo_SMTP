# Teaching-HEIGVD-RES-2021-Labo-SMTP

TODO: brief ici



## Prerequisites

* Java 11 or greater
* Maven

## Install instructions

First step is to create the config files from templates by running the following commands:

__Linux__

```bash
cd <app_root>/src/main/resources
cp config.properties.example config.properties
cp messages.json.example messages.json
cp victims.json.example victims.json
```

__Windows__

```bat
#TODO: commandes windows
```



Next you'll need to update the following files inside `src/main/resources`:

* `config.properties`: this file contains the configuration for the application
* `messages.json`: this file contains a list of messages to send
* `victims.json`: this file contains a list of victims for your pranks

Once you've configured the previous files with your how settings and data you can install the dependencies  and build and execute the application with the command `mvn compile exec:java`



## Setup a mock SMTP server with Docker

TODO: explication ici

## Description of implementation

TODO: class digramme ici