import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Write a description of class Fruit here.
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class CreateFruit
{
    //This is where we handle the fruit randomness and generate things i think
}

abstract class Fruit
{
    protected boolean isEaten;
    protected int points;
    protected Point location;
    protected int growthFactor;
    
    /**
     * Constructor for objects of class Fruit
     */
    public Fruit(boolean isEaten, Point location)
    {
        this.isEaten = isEaten;
        this.location = location;
    }
    
    public boolean isEaten()
    {
        return isEaten;
    }
    
    public int getPoints()
    {
        return points;
    }
    
    public Point getLocation()
    {
        return location;
    }
    
    public int growthFactor()
    {
        return growthFactor;
    }
}

//need to figure out what we want passed in
class Banana extends Fruit
{
    public Banana(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 10;
        growthFactor = 2;
    }
}

class Cherry extends Fruit
{
    public Cherry(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 20;
        growthFactor = 3;
    }
    
}
class Orange extends Fruit
{
    public Orange(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 30;
        growthFactor = 4;
    }
    
}
class Blueberry extends Fruit
{
    public Blueberry(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 50;
        growthFactor = 5;
    }
}
