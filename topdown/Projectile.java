import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    int mx;
    int my;
    
    int life = 200;
    int speed = 3;
    
    private void direction()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if(mouse!=null){
           mx = mouse.getX();
           my = mouse.getY();
        }
    }
    
    public void act() 
    {
        super.act();
        
        Actor actor = getOneIntersectingObject(Actor.class);
        
        turnTowards(mx, my);
        move(speed);
        life -= speed;
        
        if(life <= 0 || this.isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }    
    
}
