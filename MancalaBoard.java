import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * The Mancala Board class will create the game board, start, and play the classic
 * game, Mancala. The directions and implementation have been modified from the 
 * Mancala_Instructions.pdf on GitHub in our repository. 
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class MancalaBoard implements Runnable, ActionListener
{
    // Defined Constants
    private static final int BOARD_WIDTH = 1100;
    private static final int BOARD_HEIGHT = 500;
    private static final int INFO_HEIGHT = 220;
    private static final int BUTTON_SIZE = 10;
    private static final int END_TILL_SIZE = 100;
    private static final int NUM_BUTTON = 12;
    private static final Color BUTTON_COLOR = new Color(252,3,244);

    // Instance Variables
    private JPanel gamePanel;
    private JPanel board;
    private JLabel leftTill;
    private JLabel rightTill;
    private JLabel playerTurn;
    private int player1Till;
    private int player2Till;
    private JButton[] buttons;
    private static final int[] buttonValues = new int[12];
    private boolean player1Playing;

    private boolean gameEnded1;
    private boolean gameEnded2;

    private int marbleNum;
    private int buttonIndex;

    /**
     * Creates the game board for the mancala game and adds information
     * about how to play the game.
     */
    public void run()
    {
        JFrame frame = new JFrame("Mancala");

        frame.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player1Playing = true;

        board = new JPanel(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(BOARD_WIDTH,INFO_HEIGHT));
        board.add(infoPanel, BorderLayout.NORTH);

        gamePanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                setBackground(new Color(151,74,255));

            }
        };
        board.add(gamePanel);

        //Instruction Panel
        JLabel objective = new JLabel("The objective of the game is to collect the most pieces by the end of the game.");
        JLabel instructions = new JLabel("First Player 1 will click on one of the tills in their row. " +
                "Then one of those marbles will be added to each till in a counter-clockwise direction." ); 
        JLabel instructions2 = new JLabel("If you run into your own pocket the score will increase. "+
                "However, if you run into the other players, it will be skipped. ");

        JLabel winning = new JLabel("The game ends when all of the tills on one side of the board are empty. " +
                "The marbles in the opponents tills at the end get added to their score.");

        JLabel blank = new JLabel("---------------------------------------------------------------------------------------" +
                "------------------------------------------------------------------------------------");
        JLabel playerInstructions = new JLabel("Player one is on the bottom line of tills. "
                +"Player two is on the top line of tills.");
        JLabel blank2 = new JLabel("---------------------------------------------------------------------------------------" +
                "------------------------------------------------------------------------------------");

        playerTurn = new JLabel ("Player 1 Playing");

        Font instructionFont = new Font("Serif", Font.PLAIN, 18);
        objective.setFont(instructionFont);
        instructions.setFont(instructionFont);
        instructions2.setFont(instructionFont);
        winning.setFont(instructionFont);
        playerInstructions.setFont(instructionFont);
        playerTurn.setFont(instructionFont);
        blank.setFont(instructionFont);
        blank2.setFont(instructionFont);

        infoPanel.add(objective);
        infoPanel.add(instructions);
        infoPanel.add(instructions2);
        infoPanel.add(winning);
        infoPanel.add(blank);
        infoPanel.add(playerInstructions);
        infoPanel.add(blank2);
        infoPanel.add(playerTurn);

        //Button Set-up for Mancala "tills"
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
            buttons[i].addActionListener(this);

            buttons[i].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
            gameGrid.add(buttons[i]);
        }

        gameEnded1 = false;
        gameEnded2 = false;
        player1Till = 0;
        player2Till = 0;

        rightTill = new JLabel("Player 1: " + player1Till);
        leftTill = new JLabel("Player 2: " + player2Till);

        gamePanel.add(leftTill);
        gamePanel.add(gameGrid);
        gamePanel.add(rightTill);

        frame.add(board);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method implements the game play of a two-player Mancala Board
     * @param e the event invoking the mthod by clicking the button
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!gameEnded1 && !gameEnded2)
        {
            for (int i = 0; i < NUM_BUTTON; i++)
            {
                //This if statement will never get executed twice for one button push
                if(buttons[i] == e.getSource())
                {
                    marbleNum = buttonValues[i];
                    buttonValues[i] = 0;
                    if (player1Playing && (i > 5 && i < 12))
                    { 
                        buttonIndex = 1 + i;
                        while(marbleNum > 0)
                        {
                            player1Side();

                            if(marbleNum > 0)
                            {
                                player1Till ++;
                                marbleNum--;
                            }
                            buttonIndex = 5;
                            player2Side();
                            buttonIndex = 6;
                        }

                    }
                    else if (!player1Playing && (i >= 0 && i < 6))
                    {
                        buttonIndex = i - 1;
                        while(marbleNum > 0)
                        {
                            player2Side();

                            if(marbleNum > 0)
                            {
                                player2Till ++;
                                marbleNum--;
                            }

                            buttonIndex = 6;
                            player1Side();
                            buttonIndex = 5;
                        }

                    }
                }
            }
            for(int k = 0; k <  NUM_BUTTON; k++)
            {
                buttons[k].setText("Marbles: " + buttonValues[k]);
                rightTill.setText("Player 1: " + player1Till);
                leftTill.setText("Player 2: " + player2Till);
            }
        }
        player1Playing = !player1Playing;
        if(player1Playing)
        {
            playerTurn.setText("Player 1 Playing");
        }
        else
        {
            playerTurn.setText("Player 2 Playing");
        }
        gameEnded1 = true;
        int m = 6;
        while (m < 12)
        {
            if(buttonValues[m] != 0)
            {
                gameEnded1 = false;
            }
            m++;
        }

        m = 0;
        gameEnded2 = true;
        while (m < 6)
        {
            if(buttonValues[m] != 0)
            {
                gameEnded2 = false;
            }
            m++;
        }

        // Display which player won the game, or whose turn it is if the game is not over
        if(gameEnded1)
        {
            JOptionPane message = new JOptionPane();
            if (player1Till >= player2Till)
            {
                message.showMessageDialog(gamePanel, "Player 1 Wins!");
            }
            else
            {
                message.showMessageDialog(gamePanel, "Player 2 Wins!");
            }

        }
        else if(gameEnded2)
        {
            JOptionPane message = new JOptionPane();
            if (player1Till > player2Till)
            {
                message.showMessageDialog(gamePanel, "Player 1 Wins!");
            }
            else
            {
                message.showMessageDialog(gamePanel, "Player 2 Wins!");
            }

        }
    }

    /**
     *  This method moves the marbles on the bottom (Player 1's) side of the Mancala Board
     */
    public void player1Side()
    {
        while(marbleNum != 0 && buttonIndex <= 11)
        {
            buttonValues[buttonIndex] ++;
            marbleNum--;
            buttonIndex ++;
        }
    }

    /**
     *  This method moves the marbles on the top (Player 2's) side of the Mancala Board
     */
    public void player2Side()
    {
        while(marbleNum != 0 && buttonIndex >= 0)
        {
            buttonValues[buttonIndex] ++;
            marbleNum--;
            buttonIndex --;
        }
    }

    /**
     *  Main method to play the game
     */
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new MancalaBoard());
    }
}
