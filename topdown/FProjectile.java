import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FProjectile extends Actor
{
    private int mx;
    private int my;
    
    private int dist = 300; //distance aka life = dist/speed
    private int speed = 3;
    
    public FProjectile(int x)
    {
        setRotation(x);
        getImage().scale(96, 96);
    }
    
    private void shift()
    {
        move(speed);
        dist -= speed;
    }

    public void act() 
    {
        super.act();
        
        shift();
        end();
    }    
    
    public void end()
    {
        Actor e = getOneIntersectingObject(Projectile.class);  
        Actor wizard = getOneIntersectingObject(Wizard.class);
        
        if(e != null)
        {
            getWorld().removeObject(e);
            getWorld().removeObject(this);
        }
        else if (wizard != null) {
            
            getWorld().removeObject(this);
        }
        else if(this.isAtEdge())
        { 
            getWorld().removeObject(this);
        }
    }
    
}
