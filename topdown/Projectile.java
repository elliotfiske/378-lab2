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
    
    private int dist = 400; //distance aka life = dist/speed
    private int speed = 3;
    private int delay = 50; //time between each fire
    private int timer = 0;
    
    public Projectile()
    {
        direction();
    }
    
    private void direction()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if(mouse!=null){
           mx = mouse.getX();
           my = mouse.getY();
           //possible implementaiton after human
           //setRotation((int)(180*Math.atan2(mouse.getY()-getY(),mouse.getX()-getX())/Math.PI));
        }
        
    }
    
    private void fire()
    {
        if("space".equals(Greenfoot.getKey()))//(Greenfoot.isKeyDown("space") && timer == delay)
        {
            getWorld().addObject(new Projectile(), 1, 1);
            timer = 0;
        }
        //else
        //     timer++;
    }
    
    private void move()
    {
        move(speed);
        dist -= speed;
    }
    
    private void remove()
    {
        if(dist <= 0 || this.isAtEdge())
        { 
            World world;
            world = getWorld();
            world.removeObject(this);
        }
    }
    
    public void act() 
    {
        super.act();
        fire();
        
        turnTowards(mx, my);
        move();
        
        remove();
    }    
    
}
