/**
* Andrew Valdez
* avaldez125@unm.edu
* Missile.java
*/

import java.awt.Rectangle;

public class Missile extends GameObject{
    
    /**Public Missile Constructor
    * @arg int x, @arg int y, @arg int width, @arg int height*/
    public Missile(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    
    
    /**Overridden Method String toString
    * @returns a custom string of this object, as per the spec of this project
    */
    @Override
    public String toString(){
        return "Missile at (" + this.getX() + ", " + this.getY() + ")";
    }
}