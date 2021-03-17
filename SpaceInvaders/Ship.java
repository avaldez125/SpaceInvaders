/**
* Andrew Valdez
* avaldez125@unm.edu
* Ship.java
*/

import java.awt.Rectangle;

/**Public class Ship 
* @extends GameObject
* @implements Shooter
*/
public class Ship extends GameObject
                    implements Shooter  {
    //Ship ship = new Ship(200, 155, 50, 40);
    //^ ship constructor called from TestDriver.java
    /**Public Ship Constructor
    * @arg int x, @arg int y, @arg int width, @arg int height*/
    public Ship(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    
    /**Public Laser fire()
    * Method implemented from Shooter
    * @returns a new Laser in the middle of this ship*/
    public Laser fire(){
        return new Laser((this.getX() + (this.getWidth() - GameData.LASER_WIDTH) / 2), 
                          this.getY() - GameData.LASER_HEIGHT,
                          GameData.LASER_WIDTH, 
                          GameData.LASER_HEIGHT);
    }
    
    
    /**Overridden Method String toString
    * @returns a custom string of this object, as per the spec of this project
    */
    @Override
    public String toString(){
        //Ship at (200, 155)
        return "Ship at (" + this.getX() + ", " + this.getY() + ")";
    }
}