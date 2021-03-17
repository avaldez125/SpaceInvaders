/**
* Andrew Valdez
* avaldez125@unm.edu
* Alien.java
*/

import java.awt.Rectangle;

/**Public class Alien
* @extends GameObject
* @implements Shooter*/
public class Alien extends GameObject
                    implements Shooter  {
    
    /**Public Alien Constructor
    * @arg int x, @arg int y, @arg int width, @arg int height*/
    public Alien(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    
    /**public Missile fire()
    * Method implemented from Shooter
    * @returns a new Missile in the middle of, but not colliding with, this alien*/
    public Missile fire(){
        return new Missile(this.getX() + (this.getWidth() - GameData.MISSILE_WIDTH)/2, 
                           this.getY() + this.getHeight(),
                           GameData.MISSILE_WIDTH, 
                           GameData.MISSILE_HEIGHT);
    }
    
    /**Overridden Method String toString
    * @returns a custom string of this object, as per the spec of this project
    */
    @Override
    public String toString(){
        return "Alien at (" + this.getX() + ", " + this.getY() + ")";
    }
}