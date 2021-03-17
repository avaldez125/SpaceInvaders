/**
* Andrew Valdez
* avaldez125@unm.edu
* GameBoard.java
* This file contains all the methods and necessary variables for displaying the game
* There are some important values here, such as Score and Lives that will be needed by GameManager
* These can be accessed with InfoPanel.getScore() and InfoPanel.getMans()
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameBoard extends JPanel {
    //create a black, blank, game board
    
    
    public static class paintPanel extends JPanel {
        /**
        * paint panel constructor
        * sets preferred size 500x300
        * sets background color to Color.BLACK
        */
        static GameObject player;
        
        private String alienImagePath;
        
        private BufferedImage alienImage;
        
        private String missileImagePath;
        
        private BufferedImage missileImage;
        
        private String shipImagePath;
        
        private BufferedImage shipImage;
        
        
        public paintPanel() throws Exception {
            setPreferredSize(new Dimension(750, 500));
            setBackground(Color.BLACK);
            this.player = GameManager.player;
            
            try {
                alienImagePath = "AlienImage.png";
                alienImage = ImageIO.read(getClass().getResource(alienImagePath));
                missileImagePath = "MissileImage.png";
                missileImage = ImageIO.read(getClass().getResource(missileImagePath));
                shipImagePath = "ShipImage.png";
                shipImage = ImageIO.read(getClass().getResource(shipImagePath));
                
            }
            
            catch (IOException ex){
                System.out.println("Error. Image not found. If you see this, "
                    + "please contact Andrew (avaldez125@unm.edu)");
            }
        }
        
        public synchronized void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (Object2D object : GameManager.Aliens){
                    g.setColor(Color.BLACK);
                    g.drawImage(alienImage, object.getX(), object.getY(), Color.BLACK, this);
            }
                
            for (GameObject projectile : GameManager.Projectiles){
                if (projectile instanceof Missile){
                    g.setColor(Color.RED);
                    g.drawImage(missileImage, projectile.getX(), projectile.getY(), Color.RED, this);
                }
                
                else if (projectile instanceof Laser){
                    g.setColor(Color.GREEN);
                    g.fillOval(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());
                }
            }
            
            g.setColor(Color.GREEN);
            g.drawImage(shipImage, player.getX(), player.getY(), Color.RED, this);
            
        }
    }
    
    /**
    * public static class InfoPanel 
    * @extends JPanel
    */
    public static class InfoPanel extends JPanel {
        private static final int AlienScoreValue = GameData.DEFAULT_ALIEN_SCORE_VALUE;
        private static int Score;
        private static double ScoreMultiplier = 1.0;
        private static int Mans;
        private static String scoreText;
        private static String mansText;
        private static String scoreMultiplierText;
        /**
        * InfoPanel Constructor
        * @extends JPanel
        */
        public InfoPanel(){
        this.Score = 0;
        this.Mans = 3;
        this.scoreText = "SCORE: " + Score;
        this.mansText = "LIVES: " + Mans;
        this.scoreMultiplierText = String.format( "SCORE MULTIPLIER: %.2f", ScoreMultiplier) ;
        }
        
        
        /**
        * private void paintComponent
        * @arg Graphics g
        * paints the text string of this buttonPanel
        */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            g.setColor(Color.BLACK);
            g.drawString(scoreText, 400, 15);
            g.drawString(scoreMultiplierText, 50, 15);
            g.drawString(mansText, 750, 15);
        }
        
        /**
        * public boolean hasMans
        * @returns true if the player has at least one life left
        * otherwise, @returns false
        * Will be called by GameManager to determine if the player has lost all their lives, 
        * resulting in a game over
        */
        public boolean hasMans(){
            if (Mans > 0) {
                return true;
            }
            else
                return false;
        }
        
        /**
        * public void updateScore
        * This method will be called by GameManager if it detects that an Alien has collided
        * with the Ship's Laser.
        * Updates the Score Multiplier by 0.1, the score by AlienScoreValue (default 50) * ScoreMultiplier
        * and the scoreText of this InfoPanel
        */
        public synchronized void updateScore(){
            this.Score += ScoreMultiplier * AlienScoreValue;
            this.ScoreMultiplier *= 1.1;
            scoreMultiplierText = String.format( "SCORE MULTIPLIER: %.2f", ScoreMultiplier) ;
            scoreText = "SCORE: " + Score;
        }
        /**
        * public int getMans
        * @return the amount of lives the player has left
        */
        public synchronized int getMans(){
            return Mans;
        }
        /**
        * public int getScore
        * @return the player's score
        */
        public synchronized int getScore(){
            return this.Score;
        }
        
        /**
        * public void updateMans
        * Will be called by GameManager if it detects a collision between the player Ship and an enemy Missile
        * Decrements Player lives by 1
        * Sets the ScoreMultiplier to 0.5
        * Updates the mansText of this InfoPanel
        */
        public synchronized void updateMans(){
            ScoreMultiplier = 0.5;
            scoreMultiplierText = String.format( "SCORE MULTIPLIER: %.2f", ScoreMultiplier) ;
            this.Mans--;
            mansText = "LIVES: " + Mans;
        }
    }
}