import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int speed = 1;
    private static int counter;
    static int enemyHC;
    
    public Enemy() {
        getImage().scale(128, 128);
        counter = 0;
        enemyHC = 0;
    }
    
    public void act() 
    {
        move();
        end();
    }    
    
    public void move() {
        counter++;
        if(counter % 2 == 0)
        {
            turnTowards((((MyWorld) getWorld()).wizard.getX()), ((MyWorld) getWorld()).wizard.getY());
            move(speed);
            setRotation(0);
        }
    }
    
    public void end()
    {
        Actor e = getOneIntersectingObject(Wizard.class);  
        
        if(e != null)
        {
            getWorld().removeObject(e);
            getWorld().removeObject(this);
            enemyHC++;
            if (this.enemyHC > 5) {
                getWorld().removeObject(this);
            }
        }
        
    }
}
