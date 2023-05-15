package tictactoe.classes;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {

	Random random = new Random();
	private JFrame myFrame = new JFrame();
	private JPanel titlePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel textField = new JLabel();
//	private JButton buttons[] = new JButton[9];
	boolean playerTurn, isTie;
	private JButton buttons[][] = new JButton[3][3];
	private String xResult[][] = new String[3][3];
	private String oResult[][] = new String[3][3];
	private String[] choice = { "YES", "NO", "EXIT" };
	private int playerChoice = 3;

	public TicTacToe() {

		textField.setBackground(new Color(25, 25, 25));
		textField.setForeground(new Color(25, 255, 0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Tic - Tac - Toe");
		textField.setOpaque(true);

		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);
		titlePanel.add(textField);

		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton();
				buttonPanel.add(buttons[i][j]);
				buttons[i][j].setFont(new Font("MV Boil", Font.BOLD, 120));
				buttons[i][j].setFocusable(false);
				buttons[i][j].addActionListener(this);
			}
		}

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(800, 800);
		myFrame.getContentPane().setBackground(new Color(50, 50, 50));
		myFrame.setLayout(new BorderLayout());
		myFrame.add(titlePanel, BorderLayout.NORTH);
		myFrame.add(buttonPanel);

		myFrame.setVisible(true);

		setTurn();
		

	}

	public void setTurn() {
		if (random.nextInt(2) == 0) {
			playerTurn = true; // O play firstly
			textField.setText("O playing");
		} else {
			playerTurn = false; // X play firstly
			textField.setText("X playing");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (e.getSource() == buttons[i][j]) {
					if (!playerTurn && buttons[i][j].getText() == "") {
						buttons[i][j].setText("X");
						xResult[i][j] = buttons[i][j].getText();
						if (checkConditionWin(xResult, "X")) {
							JOptionPane.showMessageDialog(null, "X WIN");
							playerChoice = JOptionPane.showOptionDialog(null, "Do you wanna CONTINUE", null,
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice, 1);
						}
						playerTurn = true;
						textField.setText("O playing");
					} else if (playerTurn && buttons[i][j].getText() == "") {
						buttons[i][j].setText("O");
						oResult[i][j] = buttons[i][j].getText();
						if(checkConditionWin(oResult, "O")) {
							JOptionPane.showMessageDialog(null, "X WIN");
							playerChoice = JOptionPane.showOptionDialog(null, "Do you wanna CONTINUE", null,
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice, 1);
						}
						playerTurn = false;
						textField.setText("X playing");
					}
				}
			}
		}
		if(playerChoice == 0) {
			this.myFrame.dispose();;
			new TicTacToe();
		}else if(playerChoice == 1 || playerChoice == 2) {
			this.myFrame.dispose();
			System.exit(0);
		}
		
	}
	/*
	 * Check winning condition
	 * 
	 */

	// winning condition:
	private boolean checkConditionWin(String[][] result, String XO) {
		if (result[0][0] == XO && result[0][1] == XO && result[0][2] == XO
				|| result[1][0] == XO && result[1][1] == XO && result[1][2] == XO
				|| result[2][0] == XO && result[2][1] == XO && result[2][2] == XO
				|| result[0][0] == XO && result[1][0] == XO && result[2][0] == XO
				|| result[0][1] == XO && result[1][1] == XO && result[2][1] == XO
				|| result[0][2] == XO && result[1][2] == XO && result[2][2] == XO
				|| result[0][0] == XO && result[1][1] == XO && result[2][2] == XO
				|| result[0][2] == XO && result[1][1] == XO && result[2][0] == XO) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					this.buttons[i][j].setEnabled(false);
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
