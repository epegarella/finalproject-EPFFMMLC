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
    private static final int FRAME_SIZEY = 750;
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
    private CreateFruit fruit;
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

        Point snakeHead = new Point(rand.nextInt(FRAME_SIZEX - SNAKE_SIZE), rand.nextInt(FRAME_SIZEY - INFO_SIZEY - SNAKE_SIZE));
        mrSnake = new Snake(snakeHead); 

        panel = new JPanel(new BorderLayout());

        gamePlayPanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                setBackground(Color.BLACK);
                mrSnake.paint(g);
                
                //Struggling with fruit class hierarchy to get fruit methods in CreateFruit or something
                // if(!fruit.isEaten() && fruitLocation != null)
                // {
                    // fruit = new CreateFruit(false, fruitLocation, g);
                    
                // }

            }
        };
        infoArea = new JPanel();
        BoxLayout boxlayout = new BoxLayout(infoArea, BoxLayout.Y_AXIS);
        infoArea.setLayout(boxlayout);
        infoArea.setPreferredSize(new Dimension(INFO_SIZEX, INFO_SIZEY));

        instructions = new JLabel("Use WASD keys to move the snake to eat the fruit. Be careful not to hit the walls! " +
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

        //This eventually going to be with the start button
        //gamePlay();

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Starts the game and ends the game if the snake hits the wall.
     */
    public void gamePlay()
    {
        // maybe see if we want to kill the snake if he hits himself too

        // if(snakeHead.x < 0  || snakeHead.x > FRAME_SIZEX - SNAKE_SIZE )
        // {
        // mrSnake.death();
        // }
        // if(snakeHead.y < 0 || snakeHead.y > FRAME_SIZEX + SNAKE_SIZE)
        // {
        // mrSnake.death();
        // }
        while(!mrSnake.isDead())
        {
            try{
                Thread.sleep(SLEEP_TIME);
            }
            catch (InterruptedException e)
            {
            }

            gamePlayPanel.repaint();
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

        if(e.getKeyChar() == 'w')
        {
            mrSnake.move(1);
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
        gamePlay();
        //gamePlayPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(startButton == e.getSource())
        {
            mrSnake.death(false);
            mrSnake.start();

            //Cant be where a snake is maybe set it to isEaten if its where the snake is
            fruitLocation = new Point(rand.nextInt(FRAME_SIZEX - Fruit.SIZE), rand.nextInt(FRAME_SIZEY - INFO_SIZEY - Fruit.SIZE));

            gamePlayPanel.repaint();
        }
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
