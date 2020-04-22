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
    public CreateFruit(boolean isEaten, Point location)
    {
        Random r = new Random();

        int rand = r.nextInt(15);

        if(rand <= 7)
        {
            new Banana(isEaten, location);
        }
        else if(rand <= 10)
        {
            new Cherry(isEaten, location);
        }
        else if(rand <= 13)
        {
            new Orange(isEaten, location);
        }
        else
        {
            new Blueberry(isEaten, location);
        }

    }
}

abstract class Fruit
{
    protected boolean isEaten;
    protected int points;
    protected Point location;
    protected int growthFactor;
    protected Color colFood;
    protected static final int SIZE = 20;

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
    
    //@Override
    //What do we need to implement/extend so that the override works correctly?
    public void paint(Graphics g)
    {
        g.setColor(colFood);
        g.fillRect(location.x, location.y, SIZE, SIZE);
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
        colFood = Color.YELLOW;
    }
    
}

class Cherry extends Fruit
{
    public Cherry(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 20;
        growthFactor = 3;
        colFood = Color.RED;
    }

}

class Orange extends Fruit
{
    public Orange(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 30;
        growthFactor = 4;
        colFood = Color.ORANGE;
    }

}

class Blueberry extends Fruit
{
    public Blueberry(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 50;
        growthFactor = 5;
        colFood = Color.CYAN;
    }
}
