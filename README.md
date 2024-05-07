# MovieConsoleApp
Java winter semester credit program.<br>
The Movie Application Manager that is supposted to manage user's movies. 

![Alt Text](Images/MovieApp_Main.gif)

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

1. **Welcome Menu**<br>
   When the user launches the application, the first screen they encounter is the Welcome Menu. This menu serves as the gateway to the application, offering two primary functions:
   - **Start Application**: This option allows the user to enter the main interface of the application where they can manage and interact with their movies.
   - **Quit**: This option provides a way for users to exit the application if it was opened accidently.
     <br>
 ![Alt Text](Images/Welcome_menu.png)

2. **Main Menu**<br>
 Once inside the application, users are presented with the Main Menu, which serves as the central hub for all primary interactions. From here, users can manage their movie collection with the following options:<br>
 ![Alt Text](Images/Main_menu.png)
   - 2.1. Add a new movie
          This option allows users to input and save new movie details into their collection, expanding their movie database.
          When user chooses this option, app starts process of adding new movie by requesting appropriate data:
          - First it asks for Movie Title (That can be in any form), or user can type 'quit' to stop process of adding. It requests 'quit' with a small first letter to avoid situations when movie has exact same 
          name. It user wants to add "quit" as a movie title, it has to start with a capital letter - "Quit". 
   - 2.2. Display all movies
   - 2.3. Display statistics

3. **Movies Menu**<br>
   
 ![Alt Text](Images/Movies_menu.png)
  - 3.1. Edit movie
  - 3.2. Filter movies displaying
  - 3.3. Order movies displaying
  - 3.4. Set display type
  - 3.5. Delete movie
   
4. **Statistics Menu**<br>

 ![Alt Text](Images/Statistics_menu.png)
  - 4.1. Total statistics
  - 4.2. Movies by genres
  - 4.3. Rating statistics
  - 4.4. Movies by directors
   
