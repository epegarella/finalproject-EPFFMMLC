import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * The MancalaBoard class will create the game board and start the game.
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class MancalaBoard implements Runnable
{
    private static final int BOARD_WIDTH = 1000;
    private static final int BOARD_HEIGHT = 500;
    
    private static final int INFO_HEIGHT = 200;
    
    private static final Color buttonColor = new Color(201,174,97);
    
    private JPanel gamePanel;
    private JPanel board;
    
    private JButton[] buttons;
    private int[] buttonValues;
    
    public void run()
    {
        JFrame frame = new JFrame("Mancala Board");
        
        frame.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        board = new JPanel(new BorderLayout());
        
        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(BOARD_WIDTH,INFO_HEIGHT));
        board.add(infoPanel, BorderLayout.NORTH);
        
        gamePanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                setBackground(new Color(253,253,150));
                
                
            }
        };
        board.add(gamePanel);
        
        JPanel gameGrid = new JPanel(new GridLayout(2,6));
        buttons  = new JButton[12];
        for (int i = 0; i < 12; i++)
        {
            buttonValues[i] = 0;
            buttons[i] = new JButton("current marbles: " + buttonValues[i]);
            buttons[i].setBackground(buttonColor);
            
            gameGrid.add(buttons[i]);
        }
        
        frame.add(board);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new MancalaBoard());
    }
}
