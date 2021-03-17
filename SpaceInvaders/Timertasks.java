/**
* Andrew Valdez
* avaldez125@unm.edu
* Timertasks.java
*/


import java.util.*;
import java.awt.event.*;

/** public class Timertasks 
  * This class is designed to track and handle all game logic based on a javax.swing.Timer. 
  * 
  */
public class Timertasks {
    static Random random = new Random();
    private static Alien attacker;
    private static int attackerLocation;
    
    static int MOVE_TICK = GameData.PRIMARY_TICK;
    private static double TICK_DAMPEN = GameData.TICK_DAMPEN;
    
    static int frequencyDivider = 0;
    
    public static void updateTicks(){
        MOVE_TICK *= TICK_DAMPEN;
    }
    
    /** static javax.swing.Timer timer
    * contains anonymous class ActionListener
    * moves every moveable object in the game on a timer determined by GameData.java
    * This is also where the vast majority of game logic happens*/
    static javax.swing.Timer timer = new javax.swing.Timer(MOVE_TICK, new ActionListener(){
        public synchronized void actionPerformed(ActionEvent e){
            
            GameManager.timerBool = true;
            
            GameManager.cleanUp();
            GameManager.gameboard.repaint();
            GameManager.Info.repaint();
            
            if (GameManager.GameState == GAMESTATE.FREEZE){
                GameManager.freeze();
            }
            
            if (GameManager.GameState == GAMESTATE.GAME_OVER){
                GameManager.gameOver();
            }
            
            if (GameManager.numberofLives != GameManager.Info.getMans()){
                
                
                Timertasks.timer.stop();
                GameManager.timerBool = false;
                Timertasks.timer.setInitialDelay(GameData.HIT_TICK);
                
                Timertasks.timer.start();
                
                GameManager.numberofLives = GameManager.Info.getMans();
                
                if (GameManager.numberofLives <= 0){
                    GameManager.GameState = GAMESTATE.GAME_OVER;
                }
                
            }
            
            if (GameManager.alienCount == 0){
                GameManager.nextRound();
            }
            
            if (frequencyDivider % 20 == 0){
                frequencyDivider = 0;
                
                
                if (GameManager.Aliens.size() > 0){    
                    attackerLocation = random.nextInt(GameManager.Aliens.size());
                    if (GameManager.Aliens.get(attackerLocation) instanceof Alien
                    && !GameManager.Aliens.get(attackerLocation).isOutOfBounds()){
                        attacker = GameManager.Aliens.get(attackerLocation);
                        Missile missile = attacker.fire();
                        GameManager.Projectiles.add(missile);
                    }
                }
            }
            
            if (frequencyDivider % 2 == 0){
            
                for (GameObject object : GameManager.Aliens){
                    if (GameManager.alienCount == 0){
                        break;
                    }
                
                    
                    if (object instanceof Alien && GameManager.movementCount < 50){
                        object.getBoundingRectangle().setLocation(object.getX() + 5, object.getY());
                            
                    }
                    
                    else if (object instanceof Alien && GameManager.movementCount == 50){
                        object.getBoundingRectangle().setLocation(object.getX(), object.getY() + 25);
                        
                    }
                    
                    else if (object instanceof Alien && GameManager.movementCount < 100){
                        object.getBoundingRectangle().setLocation(object.getX() - 5, object.getY());
                            
                    }
                        
                    else if (object instanceof Alien && GameManager.movementCount == 101){
                        object.getBoundingRectangle().setLocation(object.getX(), object.getY() + 25);
                    }
                        
                    if (GameManager.player.intersects(object)){
                        GameManager.Info.updateMans();
                        GameManager.toRemove.add(object);
                        GameManager.player.getBoundingRectangle().setLocation(500, 600);
                    }
                }
                
                if (GameManager.movementCount == 101){
                GameManager.movementCount = 0;
                }
                else {
                    GameManager.movementCount++;
                }
            }
            
            for (GameObject object : GameManager.Projectiles){
                    
                    if (object instanceof Laser){
                        object.getBoundingRectangle().setLocation(object.getX(), object.getY() - 50);
                        //GameManager.cleanUp();
                        for (Alien alien : GameManager.Aliens){
                            if (object.intersects(alien)){
                                GameManager.Info.updateScore();
                                GameManager.toRemove.add(alien);
                                GameManager.alienCount--;
                                GameManager.toRemove.add(object);
                            }
                        }
                        
                    }
                    
                    else if (object instanceof Missile){
                        object.getBoundingRectangle().setLocation(object.getX(), object.getY() + 8);
                    }
                    
                    if (GameManager.player.intersects(object)){
                        GameManager.Info.updateMans();
                        //GameManager.Info.repaint();
                        GameManager.toRemove.add(object);
                        GameManager.player.getBoundingRectangle().setLocation(500, 600);
                        shipMovement.stopMovement();
                        //GameManager.gameboard.repaint();
                    }
                    
                    //GameManager.gameboard.repaint();
                }
            
            frequencyDivider++;
            
        }
        
    });
        
}