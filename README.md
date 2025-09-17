# Plan&Prep application

## Cloning and configuring the project

Clone the project into desired folder using command:

`git clone https://github.com/veronika1996/mealplan-app.git`

Open the project and execute following commands to initialize the sumbodules:

`git submodule init`
`git submodule update`

Open the frontend repo:

`cd mealplan-app-front`

And run command to get the dependencies:

`npm install`

## Starting the project

Go to the root folder of the project

`cd <your_path_to_project>/mealplan-app`

There you can find a script runApp.sh which is written to run
all the services and frontend part of the application at once.

Run following command to get rights for executing the script:

`chmod +x runApp.sh`

Run following command to start the application:

`./runApp.sh`