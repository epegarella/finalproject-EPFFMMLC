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
public class Snake extends Thread
{

    //Array of SIZE squares
    //Methods for directional movement
    private static final int SIZE = 20;
    private static final int MOVE_SPEED = 10;
    private static final int MOVE_TIME = 100;

    private ArrayList<Point> snake;
    private boolean isDead;
    private Point snakeHead;
    private int moveDirection;

    /**
     * Constructor for objects of class Snake
     */
    public Snake(Point location)
    {
        snake = new ArrayList<>();

        snake.add(location);

        snakeHead = snake.get(0);
        isDead = false;
    }

    public void run()
    {
        while (!isDead)
        {
            try{
                    sleep(MOVE_TIME);
                }
                catch (InterruptedException e)
                {
                }
            for(int i = 0 ; i < snake.size(); i++)
            {
                if(moveDirection == 1)
                {
                    snake.set(i, new Point(snake.get(i).x , snake.get(i).y - MOVE_SPEED));
                }
                else if(moveDirection == 2)
                {
                    snake.set(i, new Point(snake.get(i).x - MOVE_SPEED, snake.get(i).y));
                }
                else if(moveDirection == 3)
                {
                    snake.set(i, new Point(snake.get(i).x, snake.get(i).y + MOVE_SPEED));
                }
                else
                {
                    snake.set(i, new Point(snake.get(i).x + MOVE_SPEED, snake.get(i).y));
                }
            }

        }
    }

    //@Override
    //What do we need to implement/extend so that the override works correctly?
    public void paint(Graphics g)
    {
        if(!isDead)
        {
            for(Point p : snake)
            {
                g.setColor(Color.WHITE);
                g.fillRect(p.x, p.y, SIZE, SIZE);
            }
        }
    }

    public void grow(int growthFactor)
    {
        for(int i = 0; i < growthFactor; i++)
        {
            //If snake changes direction how are we sure where the new points should go?
            //But for right now let's go with:
            snake.add(new Point(snake.get(snake.size() -1).x + SIZE, snake.get(snake.size() -1).y + SIZE));
        }
    }

    public void move(int moveDirection)
    {
        this.moveDirection = moveDirection;
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
