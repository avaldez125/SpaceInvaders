/**
* Andrew Valdez
* avaldez125@unm.edu
* GameManager.java
* This method contains all the necessary "working parts" of the game
* Contained here are various gamestates, initializing and displaying the UI,
* as well as movement for each type of game piece and the main game loop. 
*/

//ideally this will be the main initialization for the entire game


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.IOException;

/** @enum GAMESTATE 
  * This enum exists to track the current game status of the final executable file. 
  */
enum GAMESTATE { INITIAL, RUN, FREEZE, GAME_OVER, NEXT_STAGE }

public class GameManager {
    /** state GAMESTATE GameState. This variable represents the current status of the game.
	  * This variable will be used for various checks related to running the game.
	  */
    static GAMESTATE GameState;
    
    /** ArrayLists of Aliens and Projectiles (Missiles and Lasers).
      * These lists are used to draw and move Alien and Projectile game objects. 
	  */
    public static java.util.List<Alien> Aliens = new ArrayList<>();
    public static java.util.List<GameObject> Projectiles = new ArrayList<>();
    
	/** toRemove is an arraylist of game objects that no longer need to be tracked
	  * on the current board. A later function will remove these objects from memory. 
	  */
    public static java.util.List<Object2D> toRemove = new ArrayList<>();
    
    /** The Player Ship
	  * This object represents the player-controlled "ship" that can be used to fight
	  * hostile combatants in-game. The coordinates 500 and 600 represent the middle of the 
	  * game board where the player ship will begin. The coordinates "32, 32" represent the size, 
	  * in pixels, of the player ship's "hitbox". 
	  */
    public static Ship player = new Ship(500, 600, 32, 32);
    
    /** The amount of time between object actions. Specified in GameData.java */
    static int HIT_PAUSE = GameData.HIT_TICK;
    
    /** static boolean timerBool. Used to ignore player input if a timer is not running */
    static boolean timerBool = false;
    
    /** static int alienCount. Incremented each time an alien is created,
    * decremented each time an alien is killed. Used to trigger the next stage of the game. */
    static int alienCount;
    
    /** static int numberofLives. Initially used to keep track of lives,
    * still technically used in code but likely to be removed in a future version*/
    static int numberofLives = 3;
    
    /** static int movementCount. Used to determine how many times aliens move 
    * right or left before moving down. May move to Timertasks.java in a future version*/
    static int movementCount = 0;
    
    /** static GameBoard instance declaration
      * These were initialized here as well, but are no longer due to image support refactoring
	  */
    static GameBoard.paintPanel gameboard;
    static GameBoard.InfoPanel Info;
    
    /** public static void main
      * Primary method to begin game
      * @arg String args[]
      */
    public static void main(String args[]) throws Exception  {
		//Declaring primary game state
        GAMESTATE GameState;
		//Initializing game by setting GameState to "GAMESTATE.INITIAL"
        GameState = GAMESTATE.INITIAL;
        
		//Draw the gameboard and infopanel with an exception handler for testing purposes. 
        try{
            gameboard = new GameBoard.paintPanel();
            Info = new GameBoard.InfoPanel();
        }
        catch (IOException ex){
            System.out.println("test");
        }
        
        
        initialize();
        showBoard();
    }
    
    /** protected static void showBoard
      * contains all the necessary code to create and display the gameboard
	  */
    protected static void showBoard(){
		//Declare the title of the window
        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		//Declare a panel on the JFrame "frame" that we can write to
        JPanel panel = new JPanel();
        
		//A playerInput object to handle user key input events
        playerInput playerinput = new playerInput();
        
		//Declare a font for displaying text
        Font  font  = new Font(Font.SERIF,  Font.BOLD, 15);
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        gameboard.setPreferredSize(new Dimension(1000, 700));
        gameboard.addKeyListener(playerinput);
        gameboard.setFocusable(true);
        
        gameboard.grabFocus();
        
        Info.setPreferredSize(new Dimension(700, 19));
        
        Info.setFont(font);
                
        panel.add(Info);
        panel.add(gameboard);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    /** private static void initialize
      * Initializes the necessary objects and timers to play the game, then sets the
	  * state of the game to GAMESTATE.RUN
	  */
    private static void initialize(){
        
        populateAliens();
        
        Timertasks.timer.start();
        
        GameState = GAMESTATE.RUN;
    }
    
    /** public static void nextRound
      * initialize, but this once pauses the game board for 1.5 seconds in addition to 
      * other bookkeeping tasks
	  */
    public static void nextRound(){
        movementCount = 0;
        Aliens.clear();
        
        Timertasks.timer.stop();
        
        populateAliens();
        
        Timertasks.updateTicks();
        
        Timertasks.timer.setDelay(Timertasks.MOVE_TICK);
        
        
        GameState = GAMESTATE.RUN;
        
        Timertasks.timer.start();
        
    }
    
    /** private static void populateAliens
      * a for-loop that creates 4 rows of 9 aliens, 36 total
	  */
    private synchronized static void populateAliens(){
		//Clear the Aliens list just to make sure we don't have any double aliens
        Aliens.clear();
        
		//This for-loop establishes all 36 aliens at incremental distances from the top and bottom of the window
		//There's probably a more efficient way to handle this odd for-loop
        for(int x = 50; x < 675; x+= 75) {
            for(int y = 50; y < 250; y += 50) {
                Alien alien = new Alien(x, y, 32, 32);
                Aliens.add(alien);
                alienCount++;
                //Missile missile = alien.fire();
                //Projectiles.add(missile);
            }
        }
    }
    
    /** public synchronized static void cleanUp
      * Winner of the 2018 Least Fun to Debug Award! 
      * Removes any objects found out of bounds and updates
      * their iterators. 
	  */
    public synchronized static void cleanUp(){
        Aliens.removeAll(toRemove);
        Projectiles.removeAll(toRemove);
    }
    
    /** public static boolean timersOn
      * @returns private boolean timerBool
      * called by playerInput to determine if the game is paused
	  */
    public static boolean timersOn(){
        return timerBool;
    }
    
    /** public static void freeze
      * all the necessary bookkeeping for pausing the game. 
	  */
    public static void freeze(){
        Timertasks.timer.stop();
        
        JOptionPane.showMessageDialog(null, "Resume?", "Game Paused", JOptionPane.PLAIN_MESSAGE);
        
        Timertasks.timer.start();
        
        GameState = GAMESTATE.RUN;
    }
    
    public static void gameOver(){
        Timertasks.timer.stop();
        JOptionPane.showMessageDialog(null, "Game Over. Final Score: " + Info.getScore());
    }
}