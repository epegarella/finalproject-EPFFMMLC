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
    
    private JPanel gamePanel;
    private JPanel board;
    public void run()
    {
        JFrame frame = new JFrame("Mancala Board");
        
        frame.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        board = new JPanel(new BorderLayout());
        
        JPanel infoPanel = new JPanel();
        board.add(infoPanel, BorderLayout.NORTH);
        
        gamePanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                setBackground(Color.YELLOW);
            }
        };
        board.add(gamePanel);
        
        frame.add(board);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new MancalaBoard());
    }
}
