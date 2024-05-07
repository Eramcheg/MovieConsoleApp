# MovieConsoleApp
Java winter semester credit program.<br>
The Movie Application Manager that is supposted to manage user's movies. 

![Alt Text](Statics/MovieApp_Main.gif)

## Features
A classic console application for managing movies you watched or always wanted to watch, collecting statistics from your movie path and storing all data in application with easy interface.

## Installation
To start the program, follow the steps below:

 1. Clone the repository in terminal using next command:<br>
    ```git clone https://github.com/Eramcheg/MovieConsoleApp.git```
 2. Navigate to the directory with cloned reposity:<br>
    ```cd MovieConsoleApp```
 3. Compile the code with maven:<br>
    ```mvn clean compile```
 4. Run the code with maven:<br>
    ```mvn exec:java```

## Usage

1. **Welcome Menu**
   When the user launches the application, the first screen they encounter is the Welcome Menu. This menu serves as the gateway to the application, offering two primary functions:
   - **Start Application**: This option allows the user to enter the main interface of the application where they can manage and interact with their movies.
   - **Quit**: This option provides a way for users to exit the application if it was opened accidently.
   ![Alt Text](Statics/Welcome_menu.png)

3. **Main Menu**
   ![Alt Text](Statics/Welcome_menu.png)
   - 2.1. Add a new movie
   - 2.2. Display all movies
   - 2.3. Display statistics

4. **Movies Menu**
   
   ![Alt Text](Statics/Movies_menu.png)
  - 3.1. Edit movie
  - 3.2. Filter movies displaying
  - 3.3. Order movies displaying
  - 3.4. Set display type
  - 3.5. Delete movie
   
4. **Statistics Menu**
  ![Alt Text](Statics/Statistics_menu.png)
  - 4.1. Total statistics
  - 4.2. Movies by genres
  - 4.3. Rating statistics
  - 4.4. Movies by directors
   
