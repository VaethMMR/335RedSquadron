package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.GamePlay;
import model.Hero;
import model.Unit;

public class GameView extends JPanel {
	private JTextArea consoleUI;
	private GamePlay theGame;
	JList playerUnits;
	DefaultListModel playerUnitsModel;

	// constructor
	public GameView(GamePlay theGame) {
		this.theGame = theGame;
		this.setLayout(new BorderLayout());//setup our layout manager
		
		// Set up the console
		consoleUI = new JTextArea();
		consoleUI.setEditable(false);
		consoleUI.setFont(new Font("monospaced", Font.PLAIN, 12));
		add(consoleUI, BorderLayout.NORTH);
		
		// Set up the actionPanel
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new GridLayout(2, 4));
		
		// create JList
		playerUnitsModel = new DefaultListModel();
		playerUnits = new JList(playerUnitsModel);
		playerUnits.setPreferredSize(new Dimension(150,200));
		// add the JList to the panel
		add(this.playerUnits, BorderLayout.WEST);
		
		// add buttons
		JButton move = new JButton("Move");
		actionPanel.add(move);
		
		JTextArea coordinateEntry = new JTextArea();
		actionPanel.add(coordinateEntry);
		
		JButton submitAction = new JButton("Submit");
		actionPanel.add(submitAction);
		
		JButton attack = new JButton("Attack");
		actionPanel.add(attack);
		
		JTextArea enemySelect = new JTextArea();
		actionPanel.add(enemySelect);
		
		JButton endTurn = new JButton("End Turn");
		actionPanel.add(endTurn);

		add(actionPanel, BorderLayout.CENTER);
		
		setupPlayerList(theGame.getPlayerTeam());
		
	}
	
	// getters and setters
	public JTextArea getConsole() {
		return consoleUI;
	}
	
	public void setConsole(String consoleOutput) {
		consoleUI.setText(consoleOutput);
	}
	
	// misc methods
	private void setupPlayerList(List<Unit> units) {		
		//first clear the list to make sure we don't duplicate Units
		playerUnitsModel.clear();
		//now add all the memories we want
		for(Unit i : units){
			String listItem= i.getClass().getSimpleName() + " " + i.getName();
			//now add the element
			playerUnitsModel.addElement(listItem);
		}
	}

}
