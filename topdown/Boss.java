import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Actor
{
    static int counter;
    static int hitCounter;
    
    public Boss() {
        getImage().scale(128, 128);
        counter = 0;
        hitCounter = 0;
    }
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        shoot();
    }    
    public void shoot() {
        counter++;
        
        if (counter < 30) {
            setImage("boss2.png");
            getImage().scale(128, 128);    
        }
        else {
            setImage("boss1.png");
            getImage().scale(128, 128);    
        }
        
        if (counter < 50) {
            return;
        }
        else {
            //ray trace
            int x =(int)(180*Math.atan2(((MyWorld) getWorld()).wizard.getY()
            - getY(), ((MyWorld) getWorld()).wizard.getX() - getX())/Math.PI);
            getWorld().addObject(new FProjectile(x), getX(), getY()); 
            counter = 0;
        }
        
    }
}
