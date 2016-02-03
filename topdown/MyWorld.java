import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private static final double scrollSpeed = 3.5;
    private static final GreenfootImage[] rooms = { new GreenfootImage("yo.png"),
                                                    new GreenfootImage("yo2.png"),
                                                    new GreenfootImage("yo3.png")};
    public static final int max = 3;
                                                    
    public static Wizard wizard;
    public int position = 0;
    public static int roomNum;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() {
        this(false);
    }
    
    public MyWorld(boolean change)
    {    
        super(500, 500, 1);
        
        if (!change) {
            roomNum = 0;
            prepare();
        }
        
        setBackground(rooms[roomNum]);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        wizard = new Wizard();
        addObject(wizard,273,178);
    }
}
