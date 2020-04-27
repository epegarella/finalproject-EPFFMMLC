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
    //Constants
    private static final int FRAME_SIZEX = 1000;
    private static final int FRAME_SIZEY = 700;
    private static final int INFO_SIZEX = 1000;
    private static final int INFO_SIZEY = 80;
    private static final int SNAKE_SIZE = 20;
    private static final int SLEEP_TIME = 500;

    //Instance Variables

    private JPanel infoArea;
    private JPanel panel;
    private JPanel gamePlayPanel;
    private boolean running;
    private int score;
    private JButton startButton;
    private JLabel totalScore;
    private JLabel instructions;
    private JLabel key;
    private Random rand;
    private Fruit fruit;
    private Point fruitLocation;
    //Either need to update the head or not have it as an instance variable
    //private Point snakeHead;
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

        Point snakeHead = new Point(rand.nextInt(FRAME_SIZEX - 2*SNAKE_SIZE), rand.nextInt(FRAME_SIZEY - INFO_SIZEY - 2*SNAKE_SIZE));
        mrSnake = new Snake(snakeHead); 

        panel = new JPanel(new BorderLayout());

        gamePlayPanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                setBackground(Color.BLACK);
                mrSnake.paint(g);

                if(!mrSnake.isDead())
                {
                    fruit.CreateFruit(false, fruitLocation, g);
                    System.out.println("Snake: " + mrSnake.getSnakeHead());
                    System.out.println("Fruit: " + fruitLocation);
                }

            }
        };
        infoArea = new JPanel();
        BoxLayout boxlayout = new BoxLayout(infoArea, BoxLayout.Y_AXIS);
        infoArea.setLayout(boxlayout);
        infoArea.setPreferredSize(new Dimension(INFO_SIZEX, INFO_SIZEY));

        instructions = new JLabel("Use WASD keys to move the snake to eat the fruit. Be careful not to hit the walls! " +
            "Each fruit is worth a different amount of points. ");
        key = new JLabel("Fruits are worth: \t Banana: 10 \t Cherry: 20  \t Orange: 30 \t Blueberry: 50");
        totalScore = new 
        JLabel("Your score is: " + score);

        instructions.setFont(new Font("Serif", Font.PLAIN, 18));
        key.setFont(new 
            Font("Serif", Font.PLAIN, 18));
        totalScore.setFont(new 
            Font("Serif", Font.PLAIN, 18));

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

        //This eventually going to be with the start button
        //gamePlay();

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

                    gamePlayPanel.repaint();
                    start();
                }

            }
        };

    }

    /**
     * Starts the game and ends the game if the snake hits the wall.
     */
    public void gamePlay()
    {
        // maybe see if we want to kill the snake if he hits himself too

        if(mrSnake.getSnakeHead().x < 0  || mrSnake.getSnakeHead().x > FRAME_SIZEX - SNAKE_SIZE )
        {
            mrSnake.death(true);
        }
        if(mrSnake.getSnakeHead().y < 0 || mrSnake.getSnakeHead().y > FRAME_SIZEX + SNAKE_SIZE)
        {
            mrSnake.death(true);
        }
        //while(!mrSnake.isDead())
        //{
        //gamePlayPanel.repaint();

        if(mrSnake.getSnakeHead() == fruitLocation)
        {
            fruit.eatFruit();

            //call scoring
            //grow snake

        }
        //}

    }

    /**
     * If a key is pressed this will determine what should be done with the snake.
     * If WASD is pressed then the snake will move in a certain direction.
     * 
     * @param e The KeyEvent object 
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

        if(e.getKeyChar() == KeyEvent.VK_W)
        {
            System.out.println("Beginning of W");
            mrSnake.move(1);
            System.out.println("End of W");
        }
        else if(e.getKeyChar() == 'a')
        {
            mrSnake.move(2);
        }
        else if(e.getKeyChar() == 's')
        {
            mrSnake.move(3);
        }
        else if(e.getKeyChar() == 'd')
        {
            mrSnake.move(4);
        }
        System.out.println("End of KeyPressed");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(startButton == e.getSource())
        {
            mrSnake.death(false);

            //Cant be where a snake is maybe set it to isEaten if its where the snake is
            fruitLocation = new Point(rand.nextInt(FRAME_SIZEX - 2 *Fruit.SIZE), rand.nextInt(FRAME_SIZEY - INFO_SIZEY - 2*Fruit.SIZE));
            gamePlayPanel.repaint();
        }
        gamePlay();
    }

    /**
     * Updates the score.
     */
    public void scoring()
    {

        totalScore.setText("Your score is: " + score);
    }

    //if snake eats fruit call snakes grow method
    //randomly generate fruit location
    //Snake movement where do we put that?

    // main method
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new GameBoard());
    }
}
