/**
* Andrew Valdez
* avaldez125@unm.edu
* Laser.java
*/

import java.awt.Rectangle;


/**Public class Laser
* @extends GameObject
*/
public class Laser extends GameObject{
    /**Public Laser Constructor
    * @arg int x, @arg int y, @arg int width, @arg int height*/
    public Laser(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    
    /** Overridden Method String toString
      * @returns a custom string of this object, as per the spec of this project
      */
    @Override
    public String toString(){
        return "Laser at (" + this.getX() + ", " + this.getY() + ")";
    }
}