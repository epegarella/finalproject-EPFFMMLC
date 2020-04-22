import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class Snake here.
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class Snake
{

    //Array of SIZE squares
    //Methods for directional movement
    private static final int SIZE = 20;

    private ArrayList<Point> snake;
    private boolean isDead;
    private Point snakeHead;
    private Point snakeTail;

    /**
     * Constructor for objects of class Snake
     */
    public Snake(Point location)
    {
        snake = new ArrayList<>();

        snake.add(location);

        snakeHead = snake.get(0);
        snakeTail = snake.get(snake.size() -1);
        isDead = false;
    }

    //@Override
    //What do we need to implement/extend so that the override works correctly?
    public void paint(Graphics g)
    {
        for(Point p : snake)
        {
            g.setColor(Color.WHITE);
            g.fillRect(p.x, p.y, SIZE, SIZE);
        }
    }

    public void grow(int growthFactor)
    {
        for(int i = 0; i < growthFactor; i++)
        {
            //If snake changes direction how are we sure where the new points should go?
            //But for right now let's go with:
            snake.add(new Point(snakeTail.x + SIZE, snakeTail.y + SIZE));
        }
    }

    public boolean isDead()
    {
        return isDead;
    }
    
    public void death()
    {
        isDead = true;
    }

}
