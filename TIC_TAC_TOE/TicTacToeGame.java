package TIC_TAC_TOE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    // Create buttons for the 9 cells
    private JButton[] buttons = new JButton[9];
    private boolean playerX = true; // true = X's turn, false = O's turn

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // 3x3 grid

        // Initialize buttons and add them to the frame
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    // When a button is clicked
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // If the button is already clicked, do nothing
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Set text based on player turn
        if (playerX) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }

        // Check for winner
        if (checkWinner()) {
            String winner = playerX ? "X" : "O";
            JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        } else {
            playerX = !playerX; // Switch player turn
        }
    }

    // Check if there is a winner
    private boolean checkWinner() {
        // Winning combinations
        int[][] combos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] combo : combos) {
            if (!buttons[combo[0]].getText().equals("") &&
                buttons[combo[0]].getText().equals(buttons[combo[1]].getText()) &&
                buttons[combo[1]].getText().equals(buttons[combo[2]].getText())) {
                return true;
            }
        }
        return false;
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    // Reset the game
    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        playerX = true;
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}

