import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * The GameBoard class will create the game board and start the game. 
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class GameBoard extends KeyAdapter implements Runnable, ActionListener
{
    // Constants
    private static final int FRAME_SIZEX = 1000;
    private static final int FRAME_SIZEY = 700;
    private static final int INFO_SIZEX = 1000;
    private static final int INFO_SIZEY = 80;
    private static final int SNAKE_SIZE = 20;
    private static final int SLEEP_TIME = 500;

    // Instance Variables
    private JPanel panel;
    private JPanel gamePlayPanel;
    private boolean running;
    private int score;
    private JButton startButton;
    private JLabel totalScore;
    private JLabel key;
    private Random rand;
    private Fruit fruit;
    private Point fruitLocation;
    private Snake mrSnake;

    /**
     * Creates the game board for the snake game and adds information
     * about how to play the game and what each fruit is worth.
     */
    public void run()
    {

        JFrame frame = new JFrame("Snake Game");

        frame.setPreferredSize(new Dimension(FRAME_SIZEX,FRAME_SIZEY));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rand = new Random();

        Point snakeHead = randomPoint();
        mrSnake = new Snake(snakeHead); 

        panel = new JPanel(new BorderLayout());

        gamePlayPanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                setBackground(Color.BLACK);

                if(!mrSnake.isDead())
                {
                    mrSnake.paint(g);
                    if(fruit != null)
                    {
                        if(fruit.getIsEaten())
                        {
                            fruitLocation = randomPoint();
                            fruit = fruit.CreateFruit(false, fruitLocation, g);
                        }
                        else 
                        {
                            fruit.paint(g);
                        }
                    }
                    else
                    {
                        fruit = fruit.CreateFruit(false, fruitLocation, g);
                    }

                }
            }
        };
        JPanel infoArea = new JPanel();
        BoxLayout boxlayout = new BoxLayout(infoArea, BoxLayout.Y_AXIS);
        infoArea.setLayout(boxlayout);
        infoArea.setPreferredSize(new Dimension(INFO_SIZEX, INFO_SIZEY));
        
        JLabel instructions = new JLabel("Use WASD keys to move the snake to eat the fruit. Be careful not to hit the walls! " +
            "Each fruit is worth a different amount of points. ");
        key = new JLabel("Fruits are worth: \t Banana: 10 \t Cherry: 20  \t Orange: 30 \t Blueberry: 50");
        totalScore = new JLabel("Your score is: " + score);

        instructions.setFont(new Font("Serif", Font.PLAIN, 18));
        key.setFont(new Font("Serif", Font.PLAIN, 18));
        totalScore.setFont(new Font("Serif", Font.PLAIN, 18));

        startButton = new JButton();
        startButton.setText("Start");

        infoArea.add(instructions);
        infoArea.add(key);
        infoArea.add(totalScore);
        infoArea.add(startButton);
        startButton.addActionListener(this);

        panel.add(gamePlayPanel);
        panel.add(infoArea, BorderLayout.NORTH);
        frame.add(panel);

        frame.addKeyListener(this);
        panel.addKeyListener(this);
        gamePlayPanel.addKeyListener(this);
        startButton.addKeyListener(this);

        frame.transferFocusDownCycle();

        mrSnake.start();
        frame.pack();
        frame.setVisible(true);
        new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        sleep(33);
                    }
                    catch(InterruptedException e){
                        System.out.println(e);
                    }
                    gamePlay();
                    gamePlayPanel.repaint();

                }

            }
        }.

        start();

    }

    /**
     * Starts the game and ends the game if the snake hits the wall or itself.
     */
    public void gamePlay()
    {

        if(!mrSnake.isDead())
        {
            if(mrSnake.getSnakeHead().x < 0  || mrSnake.getSnakeHead().x > FRAME_SIZEX - SNAKE_SIZE)
            {
                mrSnake.death(true);
                   System.out.print("Snake DEAD X");
            }
            
            if(mrSnake.getSnakeHead().y < 0|| mrSnake.getSnakeHead().y > FRAME_SIZEY)
            {
                mrSnake.death(true);
                System.out.print("Snake DEAD Y");

            }
            
            if((mrSnake.getSnakeHead().x  + SNAKE_SIZE >= fruitLocation.x && mrSnake.getSnakeHead().x <= fruitLocation.x + Fruit.SIZE)
            && (mrSnake.getSnakeHead().y  + SNAKE_SIZE >= fruitLocation.y && mrSnake.getSnakeHead().y <= fruitLocation.y + Fruit.SIZE))
            {
                scoring();
                mrSnake.setGrowing(true, fruit.growthFactor());
                fruit.eatFruit();

            }
            
            mrSnake.hitting();
        }
    }

    /**
     * If a key is pressed this will determine what should be done with the snake.
     * If WASD is pressed then the snake will move in a certain direction.
     * 
     * @param e The KeyEvent object 
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            mrSnake.move(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A)
        {
            mrSnake.move(2);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S)
        {
            mrSnake.move(3);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            mrSnake.move(4);
        }
    }

    /**
     * Determines if the start button was pressed, and if
     * it was start the game.
     * 
     * @param e The ActionEvent object 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(startButton == e.getSource())
        {
            mrSnake.death(false);

            //Cant be where a snake is maybe set it to isEaten if its where the snake is
            fruitLocation = randomPoint();
            gamePlayPanel.repaint();
        }

    }

    /**
     * Updates the score after a fruit is eaten.
     */
    public void scoring()
    {
        if(!fruit.getIsEaten())
        {
            score += fruit.getPoints();
        }
        totalScore.setText("Your score is: " + score);
    }

    /**
     * Generates a random point for the snake to appear at the start of the game,
     * and generates a new random spot for each fruit that appears.
     * 
     * @return the upper left point to create the snake or the fruit
     */
    public Point randomPoint()
    {
        return new Point(rand.nextInt(FRAME_SIZEX - 2 *Fruit.SIZE), rand.nextInt(FRAME_SIZEY - INFO_SIZEY - 2*Fruit.SIZE));
    }
    
    // main method
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new GameBoard());
    }
}
