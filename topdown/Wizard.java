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
    /* Directions:
     *     2
     *   3   1
     *     0
     */ 
    public int direction = 0;
    
    // List of all the images in the wizard's animation
    private List<GreenfootImage> wizardFrames;
    // How far into a walk cycle are we? For instance, if we JUST
    //  changed direction, this will be 0.
    private int currFrameOffset = 0;
    
    // Tracks how far the wizard moves before we show the next frame
    private final float MOVEMENT_THRESHHOLD = 24;
    private float currMovement = MOVEMENT_THRESHHOLD;
    
    /**
     * Act - do whatever the Wizard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        checkKeyMovement();
        doAnimation();
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
        changeDirection(3);
    }
    
    public void moveRight() {
        setLocation(getX() + speed, getY());
        changeDirection(1);
    }
    
    public void moveUp() {
        setLocation(getX(), getY() - speed);
        changeDirection(2);
    }
    
    public void moveDown() {
        setLocation(getX(), getY() + speed);
        changeDirection(0);
    }
    
    public void changeDirection(int newDirection) {
        if (direction != newDirection) {
            currFrameOffset = 0;
            currMovement = 0;
            direction = newDirection;            
        }
    }
    
    public void shoot() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {
            //ray trace
            int x = (int)(180*Math.atan2(mouse.getY()-getY(),mouse.getX()-getX())/Math.PI);

            //based on left mouse click && turns off auto shooting
            if(mouse.getButton() == 1 && Greenfoot.mouseClicked(null)) {  
                getWorld().addObject(new Projectile(x), getX(), getY());
            }
        }
    }

    private int prevX = 0;
    private int prevY = 0;
    
    public void doAnimation() {
        int dx = getX() - prevX;
        int dy = getY() - prevY;
        
        prevX = getX();
        prevY = getY();
        
        float distanceTraveled = (float) Math.sqrt(dx*dx + dy*dy);
        currMovement -= distanceTraveled;
        
        System.out.println("Movement: " + distanceTraveled);
        
        if (currMovement <= 0) {
            // Time for a new frame
            currMovement = MOVEMENT_THRESHHOLD;
            currFrameOffset++;
            currFrameOffset %= 4;
            
            int frame = direction * 4 + currFrameOffset;
            
            setImage(wizardFrames.get(frame));
        }
    }
    
    public Wizard() {
        GifImage wizardImage = new GifImage("wiz-color.gif");
        wizardFrames = wizardImage.getImages();
        
        for (GreenfootImage frame : wizardFrames) {
            frame.scale(72, 72);
        }
    }
}
