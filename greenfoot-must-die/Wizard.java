import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends Actor
{
    public int speed = 3;
    public static int fireCounter = 0;;
    public static int hitCounter;
    /**
     * Act - do whatever the Wizard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Wizard() {
        hitCounter = 0;
    }
    public void act() 
    {
        // Add your action code here.
        checkKeyMovement();
        checkRoomChange();
        shoot();
    }    
    
    public void checkRoomChange() {
        int xPos = 0; 
        if (getX() == 0) 
            xPos--;
        if (getX() == getWorld().getWidth() - 1) 
            xPos++;
        if (MyWorld.roomNum + xPos < 0 ||
            MyWorld.roomNum + xPos > MyWorld.max - 1)
            xPos = 0;
        if (xPos == 0)
            return;
        
        MyWorld.roomNum = (MyWorld.roomNum + MyWorld.max + xPos) % MyWorld.max;
        
        MyWorld world = new MyWorld(true);
        
        world.addObject(this, (getX() + xPos + 500) % 500, 
            (getY() + 500) % 500);
            
        Greenfoot.setWorld(world);
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
    
    public void shoot() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        fireCounter++;
        if(mouse != null) {
            //ray trace
            int x = (int)(180*Math.atan2(mouse.getY()-getY(),mouse.getX()-getX())/Math.PI);
            //based on left mouse click && turns off auto shooting
            if(mouse.getButton() == 1 && Greenfoot.mouseClicked(null)) { 
                if (fireCounter > 20) {
                    getWorld().addObject(new Projectile(x), getX(), getY());
                    fireCounter = 0;
                }
            }
        }
        
    }
}
