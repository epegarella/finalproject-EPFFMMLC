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
public class GameBoard extends Snake
{
    //Instance Variables
    private JPanel infoArea;
    private JPanel panel;
    
    private boolean running;
    private int score;
    
    private JButton startButton;
    
    private JLabel totalScore;
    private JLabel instructions;
    private JLabel key;

    /**
     * Constructor for objects of class GameBoard
     */
    public GameBoard()
    {
        
    }
    
    public void run()
    {
        
        JFrame frame = new JFrame("Snake Game");
        
        frame.setPreferredSize(new Dimension(600,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel(new BorderLayout());
        
        infoArea = new JPanel();
        //Set Dimensions
        
        panel.add(infoArea, BorderLayout.EAST);
        frame.add(panel);
        
        
        
        frame.pack();
        frame.setVisible(true);
    }

    
}
