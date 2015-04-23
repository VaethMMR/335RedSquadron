package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameView extends JPanel {
	private JTextArea consoleUI;

	// constructor
	public GameView() {
		this.setLayout(new BorderLayout());//setup our layout manager
		//this.setPreferredSize(new Dimension(530, 375));
		
		// Set up the console
		consoleUI = new JTextArea();
		consoleUI.setEditable(false);
		consoleUI.setFont(new Font("monospaced", Font.PLAIN, 12));
		add(consoleUI, BorderLayout.CENTER);
		
		// set up buttons
		//JButton giveTreat = new JButton("End Turn");

	}
	
	// getters and setters
	public JTextArea getConsole() {
		return consoleUI;
	}
	
	public void setConsole(String consoleOutput) {
		consoleUI.setText(consoleOutput);
	}

}
