# Space Shooter Game

This is a simple 2D space shooter game implemented in Java. The game includes elements such as aliens, bullets, and a player ship. The game logic is managed by the `Stage` class.

## Features

- Aliens move horizontally on the stage and change direction when reaching the edges.
- The player ship can be moved left and right within the stage boundaries.
- The player can shoot bullets vertically to destroy aliens.
- Bullets collide with aliens and the player ship, triggering actions accordingly.
- The game ends when the player loses all lives.
- The player's score is tracked based on the number of aliens destroyed.

## Usage

1. Compile and run the Java program.
2. Use arrow keys (left and right) to move the player ship.
3. Press the spacebar to shoot bullets.
4. Avoid getting hit by alien bullets and try to destroy as many aliens as possible.
5. The game ends when your lives run out.

## Code Structure

- The main game logic is implemented in the `Stage` class.
- Aliens, bullets, and the player ship are managed through separate classes (`Alien`, `Bullet`, `Player`), which are referenced in the `Stage` class.

## Requirements

- Java Development Kit (JDK) installed.

## Acknowledgments

This project was inspired by classic space shooter games and serves as a basic implementation of the genre.
