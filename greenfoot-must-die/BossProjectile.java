import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossProjectile extends Actor
{
    private int mx;
    private int my;
    
    private int dist = 300; //distance aka life = dist/speed
    private int speed = 3;
    
    public BossProjectile(int x)
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
        checkCollision();
    }    
    
    public void checkCollision()
    {
        Actor e = getOneIntersectingObject(Projectile.class);  
        Actor wizard = getOneIntersectingObject(Wizard.class);
        
        if(e != null)
        {
            //getWorld().removeObject(e);
            //getWorld().removeObject(this);
        }
        if (wizard != null) {
            ((MyWorld) getWorld()).wizard.hitCounter++;
            if (((MyWorld) getWorld()).wizard.hitCounter > 60) {
                int wizPosX = ((MyWorld) getWorld()).wizard.getX();
                int wizPosY = ((MyWorld) getWorld()).wizard.getY();
            }
            //getWorld().removeObject(this);
        }
        else if(this.isAtEdge())
        { 
            getWorld().removeObject(this);
            //haha
        }
    }
    
}
