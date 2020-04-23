import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The CreateFruit class will create a random instance of a Fruit object. Ranging from Banana (most likely) to Blueberry (least likely).
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class CreateFruit
{
    /**
     * Constructor for objects of class CreateFruit.
     * 
     * @param isEaten tells whether or not that fruit object has been eaten by the snake
     * @param location the location of that fruit on the game board
     */
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

/**
 * The Fruit class will create fruit objects of different colors and point values. 
 * The different types of fruit will grow the snake different amounts.
 */
abstract class Fruit
{
    protected boolean isEaten;
    protected int points;
    protected Point location;
    protected int growthFactor;
    protected Color colFood;
    protected static final int SIZE = 20;

    /**
     * Constructor for objects of class Fruit.
     * 
     * @param isEaten tells whether or not that fruit object has been eaten by the snake
     * @param location the location of that fruit on the game board
     */
    public Fruit(boolean isEaten, Point location)
    {
        this.isEaten = isEaten;
        this.location = location;
    }

    /**
     * Determines if the fruit has been eaten.
     * 
     * @return if the fruit has been eaten
     */
    public boolean isEaten()
    {
        return isEaten;
    }

    /**
     * Gets the number of points that a fruit is worth.
     * 
     * @return the number of points
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * Gets the location of the fruit on the game board.
     * 
     * @return the location of the fruit 
     */
    public Point getLocation()
    {
        return location;
    }

    /**
     * Gets the growth factor of the fruit.
     * 
     * @return the growth factor
     */
    public int growthFactor()
    {
        return growthFactor;
    }
    
    //@Override
    //What do we need to implement/extend so that the override works correctly?
    /**
     * Paints the fruit on the game board in the correct color and a 
     * random location, which was generated in the GameBoard class.
     * 
     * @param g The Grapics object used to draw the fruit
     */
    public void paint(Graphics g)
    {
        g.setColor(colFood);
        g.fillRect(location.x, location.y, SIZE, SIZE);
    }
}

/**
 * This class constructs an instance of Fruit as a Banana.
 */
class Banana extends Fruit
{
    /**
     * Constructor for Banana class.
     * 
     * @param inherited from Fruit class
     */
    public Banana(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 10;
        growthFactor = 2;
        colFood = Color.YELLOW;
    }
}

/**
 * This class constructs an instance of Fruit as a Cherry.
 */
class Cherry extends Fruit
{
    /**
     * Constructor for Cherry class.
     * 
     * @param inherited from Fruit class
     */
    public Cherry(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 20;
        growthFactor = 3;
        colFood = Color.RED;
    }
}

/**
 * This class constructs an instance of Fruit as a Orange.
 */
class Orange extends Fruit
{
    /**
     * Constructor for Orange class.
     * 
     * @param inherited from Fruit class
     */
    public Orange(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 30;
        growthFactor = 4;
        colFood = Color.ORANGE;
    }
}

/**
 * This class constructs an instance of Fruit as a Blueberry.
 */
class Blueberry extends Fruit
{
    /**
     * Constructor for Blueberry class.
     * 
     * @param inherited from Fruit class
     */
    public Blueberry(boolean isEaten, Point location)
    {
        super(isEaten, location);
        points = 50;
        growthFactor = 5;
        colFood = Color.CYAN;
    }
}
