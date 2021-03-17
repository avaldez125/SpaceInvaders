Andrew Valdez
avaldez125@unm.edu
SpaceInvaders

======================================================================
How to Play:
Left and Right arrow keys: Move ship
Spacebar: Fire laser
Escape: Pause game

Fight aliens and try to get the high score!
Rack up an unreasonable amount of points! Each alien you hit increases your point multiplier!
Infinite levels with ramping difficulty! 

======================================================================
Completed Features:
 - Displays Ship
 - Displays Aliens
 - Displays Laser
 - Displays Missile
 - Aliens move on a timer
 - Score implemented
 - Info Panel created and implemented (features lives, Score, and Score Multiplier)
 - Changed Java.util.timers to javax.swing.timers
 - Enemies Fire Missiles randomly
 - Enemy Missiles pause game temporarily and subtract player life
 - Ship moves in response to left or right arrow keys
 - Ship fires a laser in response to spacebar
 - Aliens have collision
 - Laser collision with aliens properly uses Score Multiplier
 - Ship acceleration/deceleration 
	 - Will rework at a later date
 - Alien fire is more dense with less enemies
 - Custom images for Aliens, lasers, missiles, and ship
 - Consolidate GUI into a ~1000 x ~700 window
	 - Scoring panel moved to top of window

======================================================================
Description of classes:
GameBoard
	Contains all the necessary functions for drawing the game board
		as well as the scoreboard

GameManager
	Creates the board
	Displays the board
	Populates aliens
	Handles collision cleanup
	
Timertasks
	**Contains primary game loop**
	Moves aliens 
	Moves projectiles
	Determines if two objects are colliding

playerInput
	Handles all the key input for the program
	Uses this key input to determine which function from shipMovement should be called

shipMovement
	Determines how much, and in what direction to move the ship
	Also stops the ship
	Has a momentum mechanic where the longer the key is held, the 
		faster the ship will move up to a maximum

======================================================================
To do:
Display pause button
Missiles collide with Lasers
Add options Quit and Resume to pause button
Add start new game option

======================================================================
Known bugs:
	User input sometimes persists after key press
	
	Low Priority:
		Lasers occasionally kill two aliens
