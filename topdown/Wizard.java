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
    public static final int DIR_DOWN  = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_UP    = 2;
    public static final int DIR_LEFT  = 3;
    
    public static final int DIR_IDLE = -1;
    
    public int speed = 3;
    /* Directions:
     *     2
     *   3   1
     *     0
     */ 
    public int direction = DIR_DOWN;
    
    public ArrayList<Integer> inputs = new ArrayList<Integer>();
    
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
        String lastKey = Greenfoot.getKey();
        
        if (lastKey != null) {
            if (lastKey.equals("s")) {
                addDirection(DIR_DOWN);
            }
            else if (lastKey.equals("d")) {
                addDirection(DIR_RIGHT);
            }
            else if (lastKey.equals("w")) {
                addDirection(DIR_UP);
            }
            else if (lastKey.equals("a")) {
                addDirection(DIR_LEFT);
            }
        }
        
        if (!Greenfoot.isKeyDown("s")) {
            endDirection(DIR_DOWN);
        }
        
        if (!Greenfoot.isKeyDown("d")) {
            endDirection(DIR_RIGHT);
        }
        
        if (!Greenfoot.isKeyDown("w")) {
            endDirection(DIR_UP);
        }
        
        if (!Greenfoot.isKeyDown("a")) {
            endDirection(DIR_LEFT);
        }
        
        // Get current direction
        float dx = getHorizontalMovement() * speed;
        float dy = getVerticalMovement()   * speed;
        
        calculateFacingDirection(dx, dy);
        
        setLocation(getX() + (int) dx, getY() + (int) dy);
    }
    
    void calculateFacingDirection(float newDX, float newDY) {
       if (Math.abs(newDX) <= 0.1f && Math.abs(newDY) <= 0.1f) {
           // Idle
           changeDirection(DIR_IDLE);
           return;
       }
        
       if (Math.abs(newDX) >= Math.abs(newDY)) {
           // Moving horizontally
           changeDirection(newDX > 0 ? DIR_RIGHT : DIR_LEFT);
       }
       else {
           // Moving vertically
           changeDirection(newDY > 0 ? DIR_DOWN : DIR_UP);
       }
    }
    
    void endDirection(int dir) {
        int ndx = inputs.indexOf(dir);
        if (ndx != -1) {
            inputs.remove(ndx);
        }
    }
    
    void addDirection(int dir) {
        endDirection(dir);
        inputs.add(0, dir);
    }
    
    int getHorizontalMovement() {
        int horizontalMovement = 0;
        for (Integer i : inputs) {
            if (i == DIR_RIGHT) {
                horizontalMovement = 1; 
                break;
            }
            
            if (i == DIR_LEFT) {
                horizontalMovement = -1;
                break;
            }
        }
        
        return horizontalMovement;
    }
    
    int getVerticalMovement() {
        int verticalMovement = 0;
        for (Integer i : inputs) {
            if (i == DIR_UP) {
                verticalMovement = -1; 
                break;
            }
            
            if (i == DIR_DOWN) {
                verticalMovement = 1;
                break;
            }
        }
        
        return verticalMovement;
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
        if (direction == DIR_IDLE) {
            doIdleAnimation();
            return;
        }
        
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
    
    void doIdleAnimation() {
        
    }
    
    public Wizard() {
        GifImage wizardImage = new GifImage("wiz-color.gif");
        wizardFrames = wizardImage.getImages();
        
        for (GreenfootImage frame : wizardFrames) {
            frame.scale(72, 72);
        }
    }
}
