import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */



public class Projectile extends Actor
{
    private int mx;
    private int my;
    
    private int dist = 300; //distance aka life = dist/speed
    private int speed = 3;
    
    public Projectile(int x)
    {
        setRotation(x);
    }
    
    private void shift()
    {
        move(speed);
        dist -= speed;
    }
    
    private void remove()
    {
        if(dist <= 0 || this.isAtEdge())
        { 
            getWorld().removeObject(this);
        }
    }
    
    public void act() 
    {
        super.act();
        
        shift();
        
        remove();
    }    
    
}
