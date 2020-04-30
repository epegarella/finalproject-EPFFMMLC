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
public class MancalaBoard implements Runnable, ActionListener
{
    private static final int BOARD_WIDTH = 1000;
    private static final int BOARD_HEIGHT = 500;

    private static final int INFO_HEIGHT = 200;
    private static final int BUTTON_SIZE = 10;

    private static final int END_TILL_SIZE = 100;

    private static final int NUM_BUTTON = 12;

    private static final Color BUTTON_COLOR = new Color(201,174,97);

    private JPanel gamePanel;
    private JPanel board;
    private JLabel leftTill;
    private JLabel rightTill;
    private int player1Till;
    private int player2Till;
    private JButton[] buttons;
    private static final int[] buttonValues = new int[12];

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

        //Play with panel design and layout managers
        JPanel gameGrid = new JPanel(new GridLayout(2,6));
        gameGrid.setPreferredSize(new Dimension(BOARD_WIDTH - 2 *END_TILL_SIZE , BOARD_HEIGHT - INFO_HEIGHT));
        buttons  = new JButton[12];
        for (int i = 0; i < NUM_BUTTON; i++)
        {
            buttonValues[i] = 4;
            buttons[i] = new JButton("Marbles: " + buttonValues[i]);

            buttons[i].setBackground(BUTTON_COLOR);
            buttons[i].setOpaque(true); 
            buttons[i].setBorderPainted(false);

            buttons[i].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
            gameGrid.add(buttons[i]);
        }

        player1Till = 0;
        player2Till = 0;

        rightTill = new JLabel(" Marbles: " + player1Till);
        leftTill = new JLabel("Marbles: " + player2Till);

        gamePanel.add(leftTill);
        gamePanel.add(gameGrid);
        gamePanel.add(rightTill);

        frame.add(board);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < NUM_BUTTON; i++)
        {
            //This if statement will never get executed twice for one button push
            if(buttons[i] == e.getSource())
            {
                for(int j = 0; j < buttonValues[i]; j++)
                {
                    //Player 2 moves probably come first
                    if(i+j+1 < 12)
                    {
                        buttonValues[i+j+1] ++;

                    }
                    else
                    {

                    }
                }
                buttonValues[i] = 0;
            }
            //Would reset too many times
            buttons[i].setText("Marbles: " + buttonValues[i]);
        }

    }
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new MancalaBoard());
    }
}
