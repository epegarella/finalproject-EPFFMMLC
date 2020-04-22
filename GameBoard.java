import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class GameBoard here.
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class GameBoard implements Runnable
{
    //Constants
    private static final int FRAME_SIZEX = 1000;
    private static final int FRAME_SIZEY = 750;
    private static final int INFO_SIZE = 200;
    private static final int SNAKE_SIZE = 20;

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
    
    private Point snakeHead;

    public void run()
    {

        JFrame frame = new JFrame("Snake Game");

        frame.setPreferredSize(new Dimension(FRAME_SIZEX,FRAME_SIZEY));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rand = new Random();

        snakeHead = new Point(rand.nextInt(FRAME_SIZEX - INFO_SIZE - SNAKE_SIZE), rand.nextInt(FRAME_SIZEY - SNAKE_SIZE));
        Snake mrSnake = new Snake(snakeHead); 
        
        panel = new JPanel(new BorderLayout());
        
        gamePlayPanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                setBackground(Color.BLACK);
                mrSnake.paint(g);
                System.out.print(snakeHead);

            }

        };
        
        

        infoArea = new JPanel();
        infoArea.setPreferredSize(new Dimension(INFO_SIZE, FRAME_SIZEY));
        //Set Dimensions

        panel.add(gamePlayPanel);
        panel.add(infoArea, BorderLayout.EAST);
        frame.add(panel);

        
        
        frame.pack();
        frame.setVisible(true);
    }

    //if snake eats fruit call snakes grow method
    //randomly generate fruit location
    //Snake movement where do we put that?

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new GameBoard());
    }

}
