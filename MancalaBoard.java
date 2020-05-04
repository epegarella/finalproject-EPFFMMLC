import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * The Mancala Board class will create the game board and start the game.
 *
 * @author Francesca Fealey, Elizabeth Pegarella, Lauren Carleton, Meghan Micheli
 * @version Spring 2020
 */
public class MancalaBoard implements Runnable, ActionListener
{
    // Constants
    private static final int BOARD_WIDTH = 1000;
    private static final int BOARD_HEIGHT = 500;
    private static final int INFO_HEIGHT = 220;
    private static final int BUTTON_SIZE = 10;
    private static final int END_TILL_SIZE = 100;
    private static final int NUM_BUTTON = 12;
    private static final Color BUTTON_COLOR = new Color(201,174,97);

    // Instance variables
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
                setBackground(new Color(253,253,150));

            }
        };
        board.add(gamePanel);

        //Instruction Panel
        JLabel objective = new JLabel("The objective of the game is to collect the most pieces by the end of the game.");
        JLabel instructions = new JLabel("First Player 1 will click on one of the tills in their row. " +
                "Then one of those marbles will be added to each till in a counter-clockwise direction." ); 
        JLabel instructions2 = new JLabel("If you run into your own pocket the score will increase. "+
                "However, if you run into the other players, it will be skipped. ");
        JLabel instructions3 = new JLabel("If the last piece drops in your score pocket you take another turn.");  
        JLabel instructions4 = new JLabel("If the last piece falls into an empty till on your side, " + 
                "then all of the marbles in your opponents opposite till get added to your score" );

        JLabel winning = new JLabel("The game ends when all of the tills on one side of the board are empty. " +
                "The marbles in the opponents tills at the end get added to their score.");

        JLabel blank = new JLabel("---------------------------------------------------------------------------------------" +
        "-----------------------------");
        JLabel playerInstructions = new JLabel("Player one is on the bottom line of tills. "
                +"Player two is on the top line of tills.");
        JLabel blank2 = new JLabel("---------------------------------------------------------------------------------------" +
        "-----------------------------");
                
        playerTurn = new JLabel ("Player 1's Turn");

        infoPanel.add(objective);
        infoPanel.add(instructions);
        infoPanel.add(instructions2);
        infoPanel.add(instructions3);
        infoPanel.add(instructions4);
        infoPanel.add(winning);
        infoPanel.add(blank);
        infoPanel.add(playerInstructions);
        infoPanel.add(blank2);
        infoPanel.add(playerTurn);

        //Button Set up
        JPanel gameGrid = new JPanel(new GridLayout(2,6));
        gameGrid.setPreferredSize(new Dimension(BOARD_WIDTH - 2 *END_TILL_SIZE , BOARD_HEIGHT - INFO_HEIGHT));
        buttons  = new JButton[12];
        for (int i = 0; i < NUM_BUTTON; i++)
        {
            buttonValues[i] = 1;
            buttons[i] = new JButton("Marbles: " + buttonValues[i]);

            buttons[i].setBackground(BUTTON_COLOR);
            buttons[i].setOpaque(true); 
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(this);

            buttons[i].setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
            gameGrid.add(buttons[i]);
        }
        buttonValues[2] = 15;
        buttons[2].setText("Marbles: " + buttonValues[2]);
        
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
     * Determines if the one of the marble tills was pressed, and if
     * one was move the marbles accordingly.
     * 
     * @param e The ActionEvent object 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        int marbleNum;
        int j = 0;
        int buttonIndex;
        if(!gameEnded1 && !gameEnded2)
        {
            for (int i = 0; i < NUM_BUTTON; i++)
            {
                //This if statement will never get executed twice for one button push
                if(buttons[i] == e.getSource())
                {
                    //Makes sure the player is trying to move their own marbles
                    marbleNum = buttonValues[i];

                    //Player1's Turn
                    if (player1Playing && (i > 5 && i < 12))
                    {             
                        buttonIndex = i + 1;
                        buttonValues[i] = 0;
                        while(marbleNum != 0)
                        {
                            //On player1's side of the board
                            if(i+j+1 < 12)
                            {
                                buttonValues[buttonIndex] ++;
                                marbleNum--;
                                buttonIndex ++;

                            }
                            //Increase player1's score by putting a marble in the score till
                            else if (i+j+1 == 12)
                            {
                                player1Till ++;
                                marbleNum--;
                            }
                            //On player2's side of the board
                            else if (i+j+1 < 19)
                            {
                                buttonIndex = 5;
                                //Loop through player2's side of the board until there are no marbles left
                                //Or we have gotten back to player 1's side
                                while (buttonIndex >= 0 && marbleNum != 0)
                                {
                                    buttonValues[buttonIndex] ++;
                                    marbleNum--;
                                    buttonIndex--;
                                }
                                buttonIndex = 6;
                                j = -1;
                            }
                            
                            
                            j++;
                        }

                        //If the last marble was placed in the till it is player1's turn again
                        //Otherwise it is player2's turn
                        if (i+j+1 == 13)
                        {
                            player1Playing = true;
                            playerTurn.setText("Player 1 Playing");
                        }
                        else
                        {
                            player1Playing = false;
                            playerTurn.setText("Player 2 Playing");
                        }

                        //Reinitialize j to 0
                        j = 0;
                    }

                    //Player2's turn
                    if(!player1Playing && (i >=0 && i < 6))
                    {
                        buttonIndex = i - 1;
                        buttonValues[i] = 0;
                        while(marbleNum != 0)
                        {
                            //Increase player's score by putting a marble in the score till
                            if(i-j-1 == -1)
                            {
                                player2Till++;
                                marbleNum--;
                            }
                            //Player1's side of the board
                            else if(i-j-1 < 0)
                            {
                                buttonIndex = 6;
                                //Loop through player1's side of the board until there are no marbles left
                                //Or we have gotten back to player 2's side
                                while (buttonIndex <= 11 && marbleNum != 0)
                                {
                                    buttonValues[buttonIndex] ++;
                                    marbleNum--;
                                    buttonIndex++;
                                    
                                }
                                buttonIndex = 5;
                            }
                            //Player2's side of the board
                            else if(i-j-1 < 5)
                            {
                                buttonValues[buttonIndex] ++;
                                marbleNum--;
                                buttonIndex --;
                            }
                            
                            
                            
                            j++;
                        }

                        //If the last marble was placed in the till it is player2's turn again
                        //Otherwise it is player1's turn
                        if (i - j == -1)
                        {
                            player1Playing = false;
                            playerTurn.setText("Player 2 Playing");
                        }
                        else
                        {
                            player1Playing = true;
                            playerTurn.setText("Player 1 Playing");
                        }

                        //reinitialize j to 0
                        j = 0;
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
        
        if(gameEnded1)
        {
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(gamePanel, "Player 1 Wins!");
            
        }
        else if(gameEnded2)
        {
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(gamePanel, "Player 2 Wins!");
            
        }
    }

    // main method
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new MancalaBoard());
    }
}
