import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends Actor
{
    public int speed = 3;
    /**
     * Act - do whatever the Wizard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        checkKeyMovement();
    }    
    
    public void checkKeyMovement() {
        if (Greenfoot.isKeyDown("w")) {
            moveUp();
        }
        else if (Greenfoot.isKeyDown("s")) {
            moveDown();
        }
        else if (Greenfoot.isKeyDown("a")) {
            moveLeft();
        }
        else if (Greenfoot.isKeyDown("d")) {
            moveRight();
        }
    }
    
    public void moveLeft() {
        setLocation(getX() - speed, getY());
    }
    
    public void moveRight() {
        setLocation(getX() + speed, getY());
    }
    
    public void moveUp() {
        setLocation(getX(), getY() - speed);
    }
    
    public void moveDown() {
        setLocation(getX(), getY() + speed);
    }
}
