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

### 2.1. Add a new movie <br>
This option allows users to input and save new movie details into their collection, expanding their movie database.
When user chooses this option, app starts process of adding new movie by requesting appropriate data:<br>
- **Movie Title**: First it asks for Movie Title (That can be in any form), or user can type 'quit' to stop process of adding. It requests 'quit' with a small first letter to avoid situations when movie 
has exact same name. It user wants to add "quit" as a movie title, it has to start with a capital letter - "Quit".<br>
- **Director**: Second value - is a director of a movie, also can be written in any form (Quentin Tarantino, Tarantino, Quentin). But user has to be aware of misspelling, because system recognise 
Quentin Tarantino and Tarantino as different directors when generates movies statistics.<br>
- **Main Genre**: Third value - main movie genre. It can be written in any way (Action, War, Scifi, Science Fiction, etc.). But user has to be aware of misspelling.<br>
- **Release Year**: Fourth value - movie release year. It can be written only in digital format (2021, 2004, 1994). But user can't write year of movie that is less than release year of first movie ever 
(Officially first movie was "Roundhay Garden Scene" in 1888, [source](https://en.wikipedia.org/wiki/Roundhay_Garden_Scene#:~:text=Roundhay%20Garden%20Scene%20is%20a,Kingdom%20on%2016%20November%201888.)) <br>
- **User's Movie Status**: Choose the status of the movie from the following options:<br>
       1. Want to watch<br>
       2. Watching<br>
       3. Already watched<br>  
  Enter the number corresponding to the desired status.<br>
- **User's Rating**: Sixth value will be requested in the situation if user's movie status was 3. Already watched. This values asks for user's rating of the watched film. This value can be written only 
in the forms 3 (Integer value), 9.4 (Decimal with dot) or 9,4 (Decimal with comma). <br>
          
### 2.2. Display all movies
This option dispays to user all his current movies in the user's list and opens Movies menu to manage existing movies and movies display. <br>
 
### 2.3. Display statistics
This option opens Statistics menu to calculate and show statistics about existing user's movies. <br>

### 2.4. Exit
This option exit the program. <br>

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
   
