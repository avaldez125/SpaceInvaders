/**
 * Holds some constants for the game in a single location for easy
 * access and modification.
 */
 
import java.awt.Rectangle;
 
public interface GameData {


    /** Width of a laser fired by player's ship. */
    int LASER_WIDTH = 2;
    /** Height of a laser fired by player's ship. */
    int LASER_HEIGHT = 50;
    /** How far a laser moves in a single step. */
    int LASER_SPEED = 10;

    /** Width of a missile fired by an alien. */
    int MISSILE_WIDTH = 5;
    /** Height of a missile fired by an alien. */
    int MISSILE_HEIGHT = 20;
    /** How far a missile moves in a single step. */
    int MISSILE_SPEED = 8;


    /** Width of game area. */
    int GAME_BOARD_WIDTH = 1000;
    /** Height of game area. */
    int GAME_BOARD_HEIGHT = 700;
    
    /** Initial Ship Position. */
    Rectangle INIT_SHIP_POSITION = new Rectangle(500, 600, 50, 40);
    
    /** The default amount of score added for defeating an alien*/
    int DEFAULT_ALIEN_SCORE_VALUE = 100;
    
    /** The amount of time between each Alien movement. 
	  * Currently set to 50 milliseconds:
	  * 	Game runs at 20 fps
	  */
    int PRIMARY_TICK = 50;
    
    /** The amount of time to pause the game when hit. */
    int HIT_TICK = 1500;
    
    /** The amount that the game is sped up each round. */
    double TICK_DAMPEN = 0.75;
}

