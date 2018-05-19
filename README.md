# SSD Snake and Ladder
### 2-4 players Snake and Ladder game

This is Snake and Ladder game that can play by 2-4 players.
The board's size is 8x8. Players take turns to roll 1 die at a time.

This program is written in Java.

![Start screen](https://github.com/printto/SSD2018_SnakeAndLadder/blob/master/Screenshots/1.png?raw=true)
![Main screen](https://github.com/printto/SSD2018_SnakeAndLadder/blob/master/Screenshots/2.png?raw=true)

## Gameplay
Each player starts with a token on the starting square. Players take turns rolling a single die to move their token by the number of squares indicated by the die roll. Tokens follow a fixed route marked on the gameboard from the bottom to the top of the playing area, passing once through every square. If, on completion of a move, a player's token lands on the lower-numbered end of a "ladder", the player moves the token up to the ladder's higher-numbered square. If the player lands on the higher-numbered square of a "snake" (or chute), the token must be moved down to the snake's lower-numbered square.

If a player rolls a 6, the player may, after moving, immediately take another turn. The player who is first to bring their token to the last square of the track is the winner.

Apart from typical Snake and Ladder, there are 2 special types of squares.
- Ice Freezing Square : If a player lands on this square, he/she skips one turn.
- Reverse Square : If a player lands on this square, he/she rolls a die and move backward according to the die's face.

## Installation
1. [Click here](https://github.com/printto/SSD2018_SnakeAndLadder/blob/master/SnakeAndLadder(Sound%20ver).zip?raw=true) to download the runnable program zip file.
2. Extract the downloaded file.
3. Start the program from SnakeAndLadder.jar

## Domain Model
![Domain Model](https://github.com/printto/SSD2018_SnakeAndLadder/blob/master/Diagrams/Domain%20Model.png?raw=true)

## UML Diagram
![UML Diagram](https://github.com/printto/SSD2018_SnakeAndLadder/blob/master/Diagrams/UML.jpg?raw=true)

## Group members
- Pappim Pipatkasrira
- Charin Tantrakul
- Pasut Kittiprapas

## References
- [Read more about Snake and Ladder](https://en.wikipedia.org/wiki/Snakes_and_Ladders)
- [Project assignment repository](https://github.com/KeeUka/SSD_2018_Final)
