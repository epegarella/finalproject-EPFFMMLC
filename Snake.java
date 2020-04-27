import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * The Snake class will create a Snake object that will move around the board and eat fruit. The snake will
 * grow according to the different fruit that it eats and move according to the inputs from 
 * the keyboard in the GameBoard class.
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class Snake extends Thread
{

    //Array of SIZE squares
    //Methods for directional movement

    // Constants
    private static final int SIZE = 20;
    private static final int MOVE_SPEED = 10;
    private static final int MOVE_TIME = 100;

    // Instance Variables
    private LinkedList<Point> snake;
    private boolean isDead;
    private Point snakeHead;
    private int moveDirection;
    private boolean isMoving;
    private boolean isGrowing;
    private boolean isDying;

    /**
     * Constructor for objects of class Snake.
     * 
     * @param location the starting location of the snake's head
     */
    public Snake(Point location)
    {
        snake = new LinkedList<>();

        snake.add(location);

        snakeHead = snake.get(0);
        isDead = true;
        isMoving = false;
    }

    /**
     * Deals with the life and movement of the snake on the game board. 
     */
    public void run()
    {
        // Add a new square at the head and delete the square at the end
        //Make snake an linked list
        //eaten and growing add to head
        // dying chop off tail
        while(!isDead)
        {
            try{
                sleep(MOVE_TIME);
            }
            catch (InterruptedException e)
            {
            }
            while(isMoving)
            {
                for(int i = 0 ; i < snake.size(); i++)
                {
                    if(moveDirection == 1)
                    {
                        snake.add(0, new Point(snake.get(i).x , snake.get(i).y - MOVE_SPEED));
                        snake.removeLast();
                    }
                    else if(moveDirection == 2)
                    {
                        snake.add(0, new Point(snake.get(i).x - MOVE_SPEED, snake.get(i).y));
                        snake.removeLast();
                    }
                    else if(moveDirection == 3)
                    {
                        snake.add(0, new Point(snake.get(i).x, snake.get(i).y + MOVE_SPEED));
                        snake.removeLast();
                    }
                    else
                    {
                        snake.add(0, new Point(snake.get(i).x + MOVE_SPEED, snake.get(i).y));
                        snake.removeLast();
                    }
                }
            }
            while(isGrowing)
            {
                
                
                
            }
            while(isDying)
            {
                
            }
        }
    }

    //@Override
    //What do we need to implement/extend so that the override works correctly?
    /**
     * Paints the snake on the game board in the correct color 
     * and a random location, which is generated in the GameBoard class.
     * 
     * @param g The Grapics object used to draw the snake
     */
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

    /**
     * This will grow the snake the appropriate length.
     * 
     * @param growthFactor The amount the snake will grow depending on which fruit was eaten
     */
    public void grow(int growthFactor)
    {
        for(int i = 0; i < growthFactor; i++)
        {
            //If snake changes direction how are we sure where the new points should go?
            //But for right now let's go with:
            snake.add(new Point(snake.get(snake.size() -1).x + SIZE, snake.get(snake.size() -1).y + SIZE));
        }
    }

    /**
     * Gets the direction that the snake will move.
     * 
     * @param moveDirection The direction that the snake will move, which is determined by the key that is pressed.
     */
    public void move(int moveDirection)
    {
        this.moveDirection = moveDirection;
        isMoving = true;
    }

    /**
     * Determines if the snake is dead, which occurs when the snake hits one of the edges of the game board.
     *
     * @return The status of the snake's life
     */
    public boolean isDead()
    {
        return isDead;
    }

    public Point getSnakeHead()
    {
        return snake.getFirst();
    }
    
    /**
     * KILLS THE SNAKE OR BRINGS IT TO LIFE
     * 
     * @param isDead whether or not the snake is dead
     */
    public void death(boolean isDead)
    {
        this.isDead = isDead;
    }
}
