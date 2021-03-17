/**
* Andrew Valdez
* avaldez125@unm.edu
* GameObject.java
*/

/*
TA's Advice:
Take a look at TestDriver.java to get an idea for what your constructors for ship and alien should look like
you can access the constants in gamedata simply with GameData.LASER_WIDTH, etc.
    Recall that fields in an interface are implicitly public static final
To math the testoutput.txt exactly, you'll need to override the toString() methods of your four concrete classes
*/

import java.awt.Rectangle;
import java.lang.Number;

public abstract class GameObject implements Object2D {
    
    //class variable bounds, which keeps track of the dimensions and intersection data for this object
    Rectangle bounds;
    
    /** GameObject Constructor
      * @arg int x, @arg int y, @arg int width, @arg int height
	  */
    public GameObject(int x, int y, int width, int height){
        this.bounds = new Rectangle(x, y, width, height);
    }
    
    /** @return x coordinate of upper left corner of object. */
    public int getX(){
        return (int) bounds.getX();
    }

    /** @return y coordinate of upper left corner of object. */
    public int getY(){
        return (int) bounds.getY();
    }

    /** @return object width. */
    public int getWidth(){
        return (int) bounds.getWidth();
    }

    /** @return object height. */
    public int getHeight(){
        return (int) bounds.getHeight();
    }

    /**
     * Get the bounding rectangle for the object.
     * @return Bounding rectangle.
     */
    public Rectangle getBoundingRectangle(){
        return bounds;
    }

    /**
     * Does this object intersect another? (Checking if the bounding
     * rectangles intersect will generally suffice.)
     * @param other The other object to check.
     * @return True if objects intersect.
     */
    public boolean intersects(Object2D other){
        return bounds.intersects(other.getBoundingRectangle());
    }

    /** 
     * Is any part of the object outside of the game board?
     * @return True if part of object is out of bounds.
     * else, @return false
     */
    public boolean isOutOfBounds(){
        if (bounds.getX() < 0
        ||  bounds.getY() < 0
        ||  bounds.getX() + bounds.getWidth() > GameData.GAME_BOARD_WIDTH
        ||  bounds.getY() + bounds.getHeight() > GameData.GAME_BOARD_HEIGHT){
            return true;
        }
        
        return false;
    }
}