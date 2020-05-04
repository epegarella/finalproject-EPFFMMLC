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

    // Constants
    private static final int SIZE = 20;
    private static final int MOVE_SPEED = 20;
    private static final int MOVE_TIME = 250;

    // Instance Variables
    private LinkedList<Point> snake;
    private boolean isDead;
    private int moveDirection;
    private boolean isMoving;
    private boolean isGrowing;
    private boolean isDying;
    private int growth;

    /**
     * Constructor for objects of class Snake.
     * 
     * @param location the starting location of the snake's head
     */
    public Snake(Point location)
    {
        snake = new LinkedList<>();
        snake.add(location);
        isDead = true;
        isMoving = false;
    }

    /**
     * Deals with the life and movement of the snake on the game board. 
     */
    public void run()
    {

        //Snake not yet alive loop
        while(isDead)
        {
            try{
                sleep(MOVE_TIME);
            }
            catch (InterruptedException e)
            {
            }
        }

        //Snake is not dead loop
        while(!isDead)
        {
            try{
                sleep(MOVE_TIME);
            }
            catch (InterruptedException e)
            {
            }
            //If the snake is growing
            if(isGrowing)
            {
                for(int i = 0; i < growth; i ++)
                {
                    if(moveDirection == 1)
                    {
                        snake.add(0, new Point(snake.get(0).x , snake.get(0).y - MOVE_SPEED));

                    }
                    else if(moveDirection == 2)
                    {
                        snake.add(0, new Point(snake.get(0).x - MOVE_SPEED, snake.get(0).y));

                    }
                    else if(moveDirection == 3)
                    {
                        snake.add(0, new Point(snake.get(0).x, snake.get(0).y + MOVE_SPEED));

                    }
                    else
                    {
                        snake.add(0, new Point(snake.get(0).x + MOVE_SPEED, snake.get(0).y));

                    }
                }
                isGrowing = false;
            }
            else if(isMoving)
            {
                if(moveDirection == 1)
                {
                    snake.add(0, new Point(snake.get(0).x , snake.get(0).y - MOVE_SPEED));
                    snake.removeLast();
                }
                else if(moveDirection == 2)
                {
                    snake.add(0, new Point(snake.get(0).x - MOVE_SPEED, snake.get(0).y));
                    snake.removeLast();
                }
                else if(moveDirection == 3)
                {
                    snake.add(0, new Point(snake.get(0).x, snake.get(0).y + MOVE_SPEED));
                    snake.removeLast();
                }
                else
                {
                    snake.add(0, new Point(snake.get(0).x + MOVE_SPEED, snake.get(0).y));
                    snake.removeLast();
                }
            }
        }
    }

    /**
     * If the Snake hits itself the game will end; the snake head cannot touch another part of the snake.
     */
    public void hitting()
    {
        for(int i = 1; i < snake.size(); i ++)
        {
            //if(snake.get(i) != null)
            {
                if (getSnakeHead().equals(snake.get(i)))
                {
                    death(true);
                }
            }
        }

    }

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
     * Sets the instance variable isGrowing to the inputed value
     * 
     * @param isGrowing whether or not the snake is growing
     */
    public void setGrowing(boolean isGrowing, int growth)
    {
        this.isGrowing = isGrowing;
        this.growth = growth;
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

    /**
     * Gets the Point where the snakes head is
     *
     * @return The point of the snakes head
     */
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
