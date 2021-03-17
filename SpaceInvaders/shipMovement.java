/**
* Andrew Valdez 
* avaldez125@unm.edu
* shipMovement.java
*/

import java.awt.*;
import java.lang.Math;

/** public class shipMovement */
public class shipMovement{
    static double time = 0;
    static final int maxSpeed = 25;
    static int move = 0;
    static final double acceleration = 1.5;
    private static double timeDampener = 0.4;
    private static Rectangle player;
    
    /** public static void moveLeft
    * Moves ship left
    */
    public static void moveLeft(){
        
        player = GameManager.player.getBoundingRectangle();
        
        if (legalMove(1)){
            time++;               
            move = determineMove();
            player.setLocation((int)(player.getX() - move), (int) player.getY());
        }
    }
    
    /** public static void moveRight
    * Moves ship right 
    */
    public static void moveRight(){
        
        player = GameManager.player.getBoundingRectangle();
        
        if (legalMove(0)){
            time++;                
            move = determineMove();
            player.setLocation((int)(player.getX() + move), (int) player.getY());
        }
        
    }
    
    /** public static void stopMovement
    * @arg direction
    * slowly* brings the ship to a stopMovement
    */
    public static void stopMovement(int direction){
        if (direction == 0){
            
            for (int i = 0; i < 25; i++){
                    move = determineMove();
                    time *= timeDampener;
                    player = GameManager.player.getBoundingRectangle();
                        if (legalMove(1)){
                        player.setLocation((int)(player.getX() - move), (int) player.getY());
                        }
            }
        }
        
        else if (direction == 1){
            for (int i = 0; i < 5; i++){
                move = determineMove();
                time *= timeDampener;
                player = GameManager.player.getBoundingRectangle();
                    if (legalMove(0)){
                        player.setLocation((int)(player.getX() + move), (int) player.getY());
                    }
            }
            
        }
    }
    
    /** private static boolean legalMove
    * @arg direction
    * determines if the move is legal*/
    private static boolean legalMove(int direction){
        if (direction == 0){
            if (player.getX() + maxSpeed >= GameData.GAME_BOARD_WIDTH - 50){
                return false;
            }
            else{
                return true;
            }
        }
        
        else if (direction == 1){
            if (player.getX() - maxSpeed <= 0){
                return false;
            }
            else{
                return true;
            }
        }
        
        else{
            return false;
        }
    }
    /** private static int determineMove
    * determines the move of the ship for this step
    * @returns the minimum of 2 * time * acceleration and maxSpeed, a constant*/
    private static int determineMove(){
        return (int) Math.min(2 * time * acceleration, maxSpeed);
    }
    
    /** public static void stopMovement
    * resets the amount of time a key is held, stopping momentum*/
    public static void stopMovement(){
        time = 1;
    }
}