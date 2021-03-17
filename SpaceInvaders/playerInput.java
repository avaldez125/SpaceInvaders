/**
* Andrew Valdez
* avaldez125@unm.edu
* playerInput.java
*/

import java.awt.event.*;
import javax.swing.*;

/** public class playerInput @extends JPanel @implements KeyListener */
public class playerInput extends JPanel implements KeyListener{
    public void keyTyped(KeyEvent e) {
        //System.out.println("keyTyped: "+e);
        int keyCode = e.getKeyCode();
        
        if (GameManager.timersOn()){
            switch( keyCode ){
            case KeyEvent.VK_LEFT:
                //move left
                shipMovement.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                //move right
                shipMovement.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                //handle ship fire
                for (int i = 0; i <= GameManager.Projectiles.size() - 1; i++){
                    Object2D temp = GameManager.Projectiles.get(i);
                    if (temp instanceof Laser && !temp.isOutOfBounds()){
                        break;
                    }
                    
                    if (i == GameManager.Projectiles.size() - 1){
                        Laser laser = GameManager.player.fire();
                        GameManager.Projectiles.add(laser);
                    }
                    
                }
                break;
            case KeyEvent.VK_ESCAPE:
                GameManager.GameState = GAMESTATE.FREEZE;
                break;
                
            }
        }
    }
    public void keyPressed(KeyEvent e) {
        //speed up ship
        int keyCode = e.getKeyCode();
        
        if (GameManager.timersOn()){
            switch( keyCode ){
            case KeyEvent.VK_LEFT:
                //move left
                shipMovement.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                //move right
                shipMovement.moveRight();
                break;
            case KeyEvent.VK_SPACE:
                //handle ship fire
                for (int i = 0; i <= GameManager.Projectiles.size() - 1; i++){
                    Object2D temp = GameManager.Projectiles.get(i);
                    if (temp instanceof Laser && !temp.isOutOfBounds()){
                        break;
                    }
                    
                    if (i == GameManager.Projectiles.size() - 1){
                        Laser laser = GameManager.player.fire();
                        GameManager.Projectiles.add(laser);
                    }
                    
                }
                break;
            case KeyEvent.VK_ESCAPE:
                GameManager.GameState = GAMESTATE.FREEZE;
                break;
                
            }
        }
        
        
    }
    public void keyReleased(KeyEvent e) {
        //slow down ship
        int keyCode = e.getKeyCode();
        
        if (GameManager.timersOn()){
            switch( keyCode ){
                case KeyEvent.VK_LEFT:
                    //move left
                    shipMovement.stopMovement(0);
                case KeyEvent.VK_RIGHT:
                    shipMovement.stopMovement(1);
            }
        }
    }
}